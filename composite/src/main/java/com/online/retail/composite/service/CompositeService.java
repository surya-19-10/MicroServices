package com.online.retail.composite.service;

import com.online.retail.composite.core.Cart;
import com.online.retail.composite.core.Customer;
import com.online.retail.composite.core.LineItem;
import com.online.retail.composite.dto.CustomerOrderDto;
import com.online.retail.composite.dto.ProductDto;
import com.online.retail.composite.model.CustomerCart;

import java.util.List;

public interface CompositeService {
    ProductDto addProduct(ProductDto productDto);

    CustomerCart addCustomer(Customer customer);

    Cart addProductsToCart(int customerId, Cart cartItems);

    List<LineItem> placeOrder(int customerId);

    CustomerOrderDto viewOrders(int customerId);
}
