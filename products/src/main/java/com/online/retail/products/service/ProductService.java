package com.online.retail.products.service;

import com.online.retail.products.model.Product;

public interface ProductService {
    Product addProduct(Product product);

    Product searchProduct(int id);

    Product updateProduct(int id, Product product);

    String deleteProduct(int id);
}
