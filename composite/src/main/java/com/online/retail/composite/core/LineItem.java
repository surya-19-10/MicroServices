package com.online.retail.composite.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    private int itemId;
    private int productId;
    private String productName;
    private int quantity;
    private double price;
}
