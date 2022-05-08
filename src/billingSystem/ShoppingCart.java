package billingSystem;

import static billingSystem.config.Config.*;
import static billingSystem.utils.Utility.getItemPrice;
import static billingSystem.utils.Utility.getItemQuantity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;

public class ShoppingCart {

    public static boolean validateAndPlaceOrder(Order order, String cardPath) {
        double totalAmount = 0.0;
        String errMessage = "";
        String successMessage = "Item,Quantity,Price,Total Cost\n";
        HashSet < String > cards = new HashSet < > ();
        boolean isTotalPrinted = false;
        if (order.getEssentialsCount() <= MAX_ESSENTIALS_ITEM_COUNT &&
            order.getLuxuryCount() <= MAX_LUXURY_ITEM_COUNT &&
            order.getMiscCount() <= MAX_MISC_ITEM_COUNT) {
            for (Item item: order.getOrderItems()) {
                if (item.getQuantity() > getItemQuantity(item.getItem())) {
                    errMessage = item.getItem() + " " + item.getQuantity() + "\n";
                } else {
                    if(isTotalPrinted){
                        successMessage +=
                        item.getItem() +
                        "," +
                        item.getQuantity() +
                        "," +
                        getItemPrice(item.getItem(), item.getQuantity()) +
                        "\n";
                    }
                    else{
                        successMessage +=
                        item.getItem() +
                        "," +
                        item.getQuantity() +
                        "," +
                        getItemPrice(item.getItem(), item.getQuantity()) +
                        ","+
                        "**"+
                        "\n";
                        isTotalPrinted = true;
                    }
                    
                    totalAmount += getItemPrice(item.getItem(), item.getQuantity());
                }
                cards.add(item.getCardNumber());
            }
            if (errMessage.isEmpty()) {
                updateCards(cards, cardPath);
                String replaceString = successMessage.replace("**",String.valueOf(totalAmount));
                writeToOutputFile(
                    SUCCESS_FILE_NAME,
                    (replaceString).getBytes(StandardCharsets.UTF_8));
                return true;
            } else {
                writeToOutputFile(
                    ERROR_FILE_NAME,
                    ("Please correct quantities for items " + errMessage).getBytes(StandardCharsets.UTF_8));
                return false;
            }
        } else {
            writeToOutputFile(
                ERROR_FILE_NAME,
                ("Please correct quantities for items. Limit for Misc is " +
                    MAX_MISC_ITEM_COUNT +
                    ", Luxury is " +
                    MAX_LUXURY_ITEM_COUNT +
                    " and Essential is " +
                    MAX_ESSENTIALS_ITEM_COUNT)
                .getBytes(StandardCharsets.UTF_8));
            return false;
        }
    }

    private static void writeToOutputFile(String fileName, byte[] bytes) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileOutputStream oFile = new FileOutputStream(file, false);
            oFile.write(bytes);
            oFile.close();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private static void updateCards(HashSet < String > cards, String cardPath) {
        ArrayList < String > savedCards = FileUtility.readCardCSV(cardPath);
        for (String card: savedCards) cards.add(card);
        writeToOutputFile(cardPath, String.join("\n", cards).getBytes(StandardCharsets.UTF_8));
    }
}