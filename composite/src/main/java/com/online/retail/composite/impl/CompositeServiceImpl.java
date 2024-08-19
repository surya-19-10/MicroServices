package com.online.retail.composite.impl;

import com.online.retail.composite.clients.*;
import com.online.retail.composite.core.*;
import com.online.retail.composite.dto.CustomerOrderDto;
import com.online.retail.composite.dto.ProductDto;
import com.online.retail.composite.model.CustomerCart;
import com.online.retail.composite.model.CustomerOrder;
import com.online.retail.composite.repo.CustomerCartRepository;
import com.online.retail.composite.repo.CustomerOrderRepository;
import com.online.retail.composite.service.CompositeService;
import com.online.retail.composite.utils.CompositeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompositeServiceImpl implements CompositeService {
    @Autowired
    private ProductClient productClient;
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private CartClient cartClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CustomerCartRepository customerCartRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = CompositeUtils.getProduct(productDto);
        Inventory inventory = CompositeUtils.getInventory(productDto);
        ResponseEntity<Product> newProduct = productClient.addProduct(product);
        if(newProduct.getStatusCode()== HttpStatus.CREATED) {
            inventory.setProductId(newProduct.getBody().getId());
        }
        inventoryClient.addInventory(inventory);
        return productDto;
    }

    @Override
    public CustomerCart addCustomer(Customer customer) {
        ResponseEntity<Customer> savedCustomer = customerClient.addCustomer(customer);
        ResponseEntity<Cart> newCart = cartClient.saveCart(new Cart());
        if(savedCustomer.getStatusCode()==HttpStatus.CREATED && newCart.getStatusCode()==HttpStatus.CREATED) {
            CustomerCart customerCart = new CustomerCart();
            customerCart.setCustomerId(savedCustomer.getBody().getCustomerId());
            customerCart.setCartId(newCart.getBody().getCartId());
            return customerCartRepository.save(customerCart);
        }
        return null;
    }

    @Override
    public Cart addProductsToCart(int customerId, Cart cart) {
        Optional<CustomerCart> customerCart = customerCartRepository.findById(customerId);
        if(customerCart.isPresent()) {
            List<LineItem> validProducts = cart.getLineItems().stream().filter(cartItems -> validateAndPrepareItems(cartItems)).collect(Collectors.toList());
            cart.setCartId(customerCart.get().getCartId());
            cart.setLineItems(validProducts);
            return cartClient.updateCart(cart.getCartId(), cart).getBody();
        }
        return null;
    }

    @Override
    @Transactional
    public List<LineItem> placeOrder(int customerId) {
        Optional<CustomerCart> customerCart = customerCartRepository.findById(customerId);
        if(customerCart.isPresent()) {
            ResponseEntity<Cart> cart = cartClient.getCart(customerCart.get().getCartId());
            if(cart.getStatusCode()==HttpStatus.OK && cart.hasBody()) {
                List<LineItem> cartLineItems = cart.getBody().getLineItems().stream().map(li -> {
                    LineItem lineItem = new LineItem();
                    lineItem.setProductId(li.getProductId());
                    lineItem.setProductName(li.getProductName());
                    lineItem.setPrice(li.getPrice());
                    lineItem.setQuantity(li.getQuantity());
                    return lineItem;
                }).collect(Collectors.toList());
                cartClient.deleteCart(cart.getBody().getCartId());

                Order order = new Order();
                order.setLineItems(cartLineItems);
                ResponseEntity<Order> newOrder = orderClient.addOrder(order);
                if(newOrder.hasBody() && newOrder.getStatusCode()==HttpStatus.CREATED) {
                    int orderId = newOrder.getBody().getOrderId();
                    customerOrderRepository.save(new CustomerOrder(orderId, customerId));
                }

                Map<Integer, Integer> orderedProductQuantityMap = order.getLineItems().stream()
                        .collect(Collectors.toMap(LineItem::getProductId, LineItem::getQuantity));

                orderedProductQuantityMap.entrySet().stream().forEach(li ->{
                    Inventory inventory = inventoryClient.searchInventory(li.getKey()).getBody();
                    int quantity = inventory.getQuantity()-li.getValue();
                    inventory.setQuantity(quantity>0?quantity:0);
                    inventoryClient.updateInventory(li.getKey(), inventory);
                });
                return order.getLineItems();
            }
        }
        return null;
    }

    @Override
    public CustomerOrderDto viewOrders(int customerId) {
        List<Integer> orderIds = customerOrderRepository.getOrdersOfCustomer(customerId);
        if(orderIds.size()>0) {
            Customer customer = customerClient.getCustomer(customerId).getBody();
            List<Order> orders = orderIds.stream().map(orderClient::getOrder)
                    .filter(order -> order.getStatusCode()==HttpStatus.OK)
                    .map(ResponseEntity::getBody).collect(Collectors.toList());
            return new CustomerOrderDto(customer, orders);
        }
        return null;
    }

    private boolean validateAndPrepareItems(LineItem item) {
        ResponseEntity<Product> productResponseEntity = productClient.searchProduct(item.getProductId());
        if(productResponseEntity.getStatusCode()==HttpStatus.OK && productResponseEntity.hasBody()){
            if(productResponseEntity.getBody().getName().equalsIgnoreCase(item.getProductName())) {
                item.setPrice(productResponseEntity.getBody().getPrice());
                return true;
            }
        }
        return false;
    }
}
