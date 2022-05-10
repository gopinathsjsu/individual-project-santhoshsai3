package billingSystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

public class Billing {

    public static ArrayList < Inventory > INVENTORIES;

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	try {

            Path inventoryPath = Paths.get(args[0]);
            Path cardPath = Paths.get(args[1]);
            String orderPath ;
            boolean modified = false;
            if(args.length == 2){
                System.out.print("Enter the absolute path for the input CSV file: ");
                orderPath = Paths.get(sc.nextLine()).toString();                
            }
            else{
            	orderPath = Paths.get(args[2]).toString();
            }
            String fileExtension = "";
            int i = orderPath.toString().lastIndexOf('.');
            if(i > 0) {
            	fileExtension = orderPath.toString().substring(i+1);
            }
            if(fileExtension.equalsIgnoreCase("xlsx")) {
            	try {
            		
            		modified = true;
                	Workbook wb = new Workbook(orderPath);
                	orderPath = "temp.csv";
                	wb.save(orderPath.toString(),SaveFormat.AUTO);
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}
            }
            
            
            INVENTORIES = FileUtility.readInventoryCSV(inventoryPath.toString());
            Order order = FileUtility.readOrderCSV(orderPath);
            if(ShoppingCart.validateAndPlaceOrder(order, cardPath.toString())){
                System.out.println("Order has been successsfully placed!!.");
            }
            else{
                System.out.println("Error!! Please check input file.");
            }
            
        
    	} catch(Exception e) {
    		e.printStackTrace();
    	} finally {
			sc.close();
		}
    }
}