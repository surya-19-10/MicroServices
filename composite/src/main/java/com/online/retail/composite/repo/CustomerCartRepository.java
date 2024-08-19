package com.online.retail.composite.repo;

import com.online.retail.composite.model.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerCartRepository extends JpaRepository<CustomerCart, Integer> {
}
