package billingSystem.utils;

import static billingSystem.Billing.INVENTORIES;

import billingSystem.Inventory;

public class Utility {

    public static Integer getItemQuantity(String item) {
        for (Inventory inventory: INVENTORIES) {
            if (inventory.getItem().equals(item)) return inventory.getQuantity();
        }
        return 0;
    }

    public static Double getItemPrice(String item, Integer quantity) {
        for (Inventory inventory: INVENTORIES) {
            if (inventory.getItem().equals(item)) return inventory.getPrice() * quantity;
        }
        return 0.0;
    }
}