package com.online.retail.composite.repo;

import com.online.retail.composite.core.Customer;
import com.online.retail.composite.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
    @Query(value = "select order_id from customer_order where customer_id=:id", nativeQuery = true)
    List<Integer> getOrdersOfCustomer(@Param("id") int customerId);
}
