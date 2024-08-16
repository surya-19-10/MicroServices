package com.online.retail.cart.service;

import com.online.retail.cart.model.Cart;

public interface CartService {
    Cart saveCart(Cart cart);

    Cart getCart(int id);

    String deleteCart(int id);
}
