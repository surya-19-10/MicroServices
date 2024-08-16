package com.online.retail.products.impl;

import com.online.retail.products.model.Product;
import com.online.retail.products.repo.ProductRepository;
import com.online.retail.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product searchProduct(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setDescription(product.getDescription());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return "Deleted";
    }
}
