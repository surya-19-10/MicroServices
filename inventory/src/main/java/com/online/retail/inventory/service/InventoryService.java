package com.online.retail.inventory.service;

import com.online.retail.inventory.model.Inventory;

public interface InventoryService {
    Inventory addInventory(Inventory inventory);

    Inventory searchInventory(int id);

    Inventory updateInventory(int id, Inventory inventory);

    String deleteInventory(int id);
}
