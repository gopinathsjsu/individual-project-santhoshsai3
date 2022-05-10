package billingSystem;


import java.io.BufferedReader;
import java.util.ArrayList;

import billingSystem.Billing.*;

import java.io.*;


public class FileUtility {

    static Integer miscCount = 0;
    static Integer luxuryCount = 0;
    static Integer essentialsCount = 0;

    public static Order readOrderCSV(String filePath) {
        ArrayList < Item > items = new ArrayList < > ();
        String row;
        String card = "";
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if(row.startsWith("Evaluation")) {
                	continue;
                }
                if (data.length >= 2 &&
                    validateItem(data[0].toLowerCase(), Integer.parseInt(data[1])) &&
                    validateQuantity(data[1])) {
                    if(data.length == 3 && card.isEmpty()&&  validateCardNumber(data[2])){
                        card = data[2];
                    }
                    if(!card.isEmpty()){
                        items.add(new Item(data[0].toLowerCase(), Integer.parseInt(data[1]), card));
                    }
                    else{
                         System.out.println("Invalid input csv data");
                    }
                } else {
                    System.out.println("Invalid input csv data");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Order(items, miscCount, luxuryCount, essentialsCount);
    }

    public static ArrayList < Inventory > readInventoryCSV(String filePath) {
        ArrayList < Inventory > inventories = new ArrayList < > ();
        String row;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                String[] data = row.split(",");
                if (data.length == 4 && validateQuantity(data[2]) && validatePrice(data[3])) {
                    inventories.add(
                        new Inventory(
                             data[1], data[0].toLowerCase(), Integer.parseInt(data[2]), Double.parseDouble(data[3])));
                } else {
                    System.out.println("Invalid inventory dataset data");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return inventories;
    }

    public static ArrayList < String > readCardCSV(String filePath) {
        ArrayList < String > cards = new ArrayList < > ();
        String row;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            reader.readLine();
            while ((row = reader.readLine()) != null) {
                cards.add(row);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return cards;
    }

    private static boolean validateCardNumber(String cardNumber) {
        String card = cardNumber.replaceAll(" ", "");
        return true;
    }

    private static boolean validateItem(String itemName, Integer quantity) {
        for (Inventory inventory: Billing.INVENTORIES) {
            if (inventory.getItem().equals(itemName)) {
                validateCategory(inventory.getCategory(), quantity);
                return true;
            }
        }
        return false;
    }

    private static boolean validateQuantity(String quantity) {
        return quantity.matches("-?\\d+");
    }

    private static boolean validatePrice(String quantity) {
        return quantity.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private static boolean validateCategory(String category, int quantity) {
        if (category.equals("Misc")) miscCount += quantity;
        if (category.equals("Essentials")) essentialsCount += quantity;
        else if (category.equals("Luxury")) luxuryCount += quantity;
        return category.matches("Misc|Essential|Luxury");
    }
}