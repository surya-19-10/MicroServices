package com.online.retail.cart.repo;

import com.online.retail.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from line_item where cart_id=:cartId", nativeQuery = true)
    void deleteExistItems(@Param("cartId")int cartId);
}
