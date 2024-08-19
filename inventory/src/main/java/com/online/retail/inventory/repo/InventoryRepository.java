package com.online.retail.inventory.repo;

import com.online.retail.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Query(value = "select * from inventory where product_id=:id", nativeQuery = true)
    Inventory findByProductId(@Param("id") int id);
}
