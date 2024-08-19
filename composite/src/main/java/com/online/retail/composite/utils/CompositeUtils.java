package com.online.retail.composite.utils;

import com.online.retail.composite.core.Inventory;
import com.online.retail.composite.core.Product;
import com.online.retail.composite.dto.ProductDto;

public class CompositeUtils {
    public static Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getProductDescription());
        product.setName(productDto.getProductName());
        product.setPrice(productDto.getProductPrice());
        return product;
    }

    public static Inventory getInventory(ProductDto productDto) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(productDto.getQuantity());
        return inventory;
    }
}
