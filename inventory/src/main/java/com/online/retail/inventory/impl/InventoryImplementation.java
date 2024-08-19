package com.online.retail.inventory.impl;

import com.online.retail.inventory.model.Inventory;
import com.online.retail.inventory.repo.InventoryRepository;
import com.online.retail.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryImplementation implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory searchInventory(int id) {
        return inventoryRepository.findByProductId(id);
    }

    @Override
    public Inventory updateInventory(int id, Inventory inventory) {
        Inventory exisitingInventory = inventoryRepository.findByProductId(id);
        exisitingInventory.setQuantity(inventory.getQuantity());
        exisitingInventory.setProductId(inventory.getProductId());
        return inventoryRepository.save(inventory);
    }

    @Override
    public String deleteInventory(int id) {
        inventoryRepository.deleteById(id);
        return "Deleted";
    }
}
