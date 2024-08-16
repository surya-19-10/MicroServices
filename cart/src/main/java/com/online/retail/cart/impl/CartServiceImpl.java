package com.online.retail.cart.impl;

import com.online.retail.cart.model.Cart;
import com.online.retail.cart.repo.CartRepository;
import com.online.retail.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public String deleteCart(int id) {
        cartRepository.deleteById(id);
        return "Deleted";
    }
}
