
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReceiptGenerator {

    static final double tax = 6; // declare constant for tax - 6% tax 
    static final double discount = 5; // declare constant for discount - 5% off

    public static void createReceipt(String itemName, int quantity, double unitPrice, boolean useDiscounts) { // declare new method createReceipt with parameters itemName, quantity, and unitPrice
        File receipt = new File("output.txt"); // create file to write to for receipt

        double ftax = tax / 100; // calculate tax
        double subtotal = unitPrice * quantity; // calculate subtotal

        if (useDiscounts) {
           // TODO: Add discounts
        }
        
        double total = subtotal + subtotal * ftax; // add tax to subtotal and calculate total price

        try (PrintWriter writer = new PrintWriter("output.txt")) { // create try-catch to write to file using PrintWriter
            writer.println("========== RECEIPT =========="); // write receipt title and line break for formatting
            writer.println(); // make space
            writer.println("Item: " + itemName); // write item name to file
            writer.println("Quantity: " + quantity); // write item quantity to file
            writer.println(); // make space
            writer.println("============================="); // make line break for formatting
            writer.println(); // make space
            writer.println("Subtotal: " + subtotal); // write subtotal to file
            writer.println("Total: " + total); // write total to file
            writer.close(); // close writer

            Desktop desktop = Desktop.getDesktop(); // get user's desktop to open receipt file
            try { // create try-catch to open receipt file
                desktop.open(receipt); // open receipt file
                System.out.println("Opening File...");
            } catch (IOException e) {
                System.err.println("Cannot open file");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error - Cannot Write File");
        }
    }

    public static void main(String[] args) { // declare main method
        String itemName = ""; // declare String itemName
        int quantity = 0; // declare String quantity
        double unitPrice = 0; // declare double UnitPrice
        
        boolean validInput = false;
        boolean useDiscounts = false;

        while (!validInput) {
            try (Scanner reader = new Scanner(new FileReader("input.txt"))) { // create try-catch to read information from file
                itemName = reader.nextLine(); // set itemName to first line of file
                quantity = reader.nextInt(); // set quantity to second line of file
                unitPrice = reader.nextDouble(); // set unitPrice to third line of file
                useDiscounts = reader.nextBoolean();
                if ("".equals(itemName)) {
                    System.err.println("Invalid Item Name");
                    break;
                } else if (quantity <= 0) {
                    System.err.println("Invalid Quantity");
                    break;
                } else if (unitPrice <= 0) {
                    System.err.println("Invalid Unit Price");
                    break;
                }
                validInput = true;
                reader.close(); // close reader

            } catch (Exception ex) {
                System.err.println("Error - Cannot Read File");
            }
        }

        createReceipt(itemName, quantity, unitPrice, useDiscounts); // call createReceipt method and pass arguments itemName, quantity, and unitPrice
    }
}
