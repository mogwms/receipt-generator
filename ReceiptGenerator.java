/*
 * Program Name: Scan2.java
 * Author(s): Malcolm Williams, Ethan Vaughn, David Kuria
 * Date: 12/1/2025
 * Course: Computer Science I
 * Instructor: Suraj Maraboina
 *
 * Description:
 * -------------------------------------------------------------
 * This program simulates a point-of-sale receipt generator. The program reads from a file 
 * (purchases.txt),assigns the values from the file into different variables and checks for validity, and then
 * and passes the values as arguments into a method to create & calculate the final receipt. The 
 * program demonstrates the use of different Java classes, such as FileReader, PrintWriter, and
 * File.
 * 
 * The receipt will print the name of the item(s), the quantity of the item(s) and the unit price of the item(s).
 * They will also print the subtotal and total prices.
 * 
 * 
 * The program uses conditional statements to check the validity of inputs, making sure negative
 * numbers or no characters will not be accepted.
 * -------------------------------------------------------------
 */
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

    public static void createReceipt(String itemName, int quantity, double unitPrice, boolean useDiscounts) { 
        File receipt = new File("output.txt"); // create file to write to for receipt

        double ftax = tax / 100; // calculate tax
        double subtotal = unitPrice * quantity; // calculate subtotal

        if (useDiscounts) {
            double fdiscount = discount / 100; // divide discount by 100 to convert to decimal
            subtotal = subtotal - (fdiscount * subtotal); // calculate discounted price
        }

        double total = subtotal + subtotal * ftax; // add tax to subtotal and calculate total price

        try (PrintWriter writer = new PrintWriter("output.txt")) { // create try-catch to write to file using PrintWriter
            writer.println("========== RECEIPT =========="); // write receipt title and line break for formatting
            writer.println(); // make space
            writer.println("ORDER: "); // write item title
            for (int i = 0; i < quantity; i++) { // create i loop to print each item for quantity
                writer.printf(itemName + "                $%.2f%n", unitPrice); // write item quantity to file
            }
            writer.println(); // make space
            writer.println("============================="); // make line break for formatting
            writer.println(); // make space
            writer.printf("SUBTOTAL: $%.2f%n", subtotal); // write subtotal to file
            writer.printf("TOTAL: $%.2f%n", total); // write total to file
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

        boolean validInput = false; // declare boolean validInput
        boolean useDiscounts = false; // declare boolean useDiscounts

        try (Scanner reader = new Scanner(new FileReader("purchases.txt"))) { // create try-catch to read information from file
                String order = reader.next(); // read order from file
                String[] items = order.split(","); // split string to get item name, quantity, and unit price
                itemName = items[0]; // set itemName to first line of file
                quantity = Integer.parseInt(items[1]); // set quantity to second line of file
                unitPrice = Double.parseDouble(items[2]); // set unitPrice to third line of file

                if ("".equals(itemName)) { // create conditional statement to check if itemName is empty (invalid)
                    System.err.println("Error - Invalid Item Name");
                    return;
                } else if (quantity <= 0)  { // check if quantity is less than or equal to zero (invalid)
                    System.err.println("Error - Invalid Quantity"); 
                    return;
                } else if (unitPrice <= 0) { // check if unit price is less than or equal to zero (invalid)
                    System.err.println("Error - Invalid Unit Price");
                    return;
                }

                useDiscounts = Boolean.parseBoolean(items[3]);
                
                if (useDiscounts) {
                    System.out.println("Applying Discount To Order");
                }

                validInput = true; // set validInput to true and break the loop
                reader.close(); // close reader

            } catch (Exception ex) { // catch exception
                System.err.println("Error - " + ex.getMessage());
            }

       if (validInput) {
         createReceipt(itemName, quantity, unitPrice, useDiscounts); // call createReceipt method and pass arguments itemName, quantity, and unitPrice
       }
    }
}/*
 * -------------------------------------------------------------
 *                 TEST CASES & OUTPUT VERIFICATION
 * -------------------------------------------------------------
 *
 * TEST CASE 1 – RECEIPT CREATED
 * Input:
 *   Notebook,3,2.50,false
 * Output:
 * 
 * ========== RECEIPT ==========
 *
 * ORDER: 
 * Notebook                $2.50
 * Notebook                $2.50
 * Notebook                $2.50
 *
 * =============================
 *
 * SUBTOTAL: $7.50
 * TOTAL: $7.95
 *
 * TEST CASE 2 – RECEIPT CREATED
 * Input:
 *    Eggs,2,2.14,false
 * Output:
 * 
 * ========== RECEIPT ==========
 *
 * ORDER: 
 * Eggs                $2.15
 * Eggs                $2.15
 *
 * =============================
 *
 * SUBTOTAL: $4.29
 * TOTAL: $4.55
 *
 * TEST CASE 3 – RECEIPT CREATED
 * Input:
 *    Ham,1,7.50,true
 * Output:
 * ========== RECEIPT ==========
 *
 * ORDER: 
 * Ham                $7.50
 *
 * =============================
 *
 * SUBTOTAL: $7.13
 * TOTAL: $7.55
 *
 * -------------------------------------------------------------
 * END OF FILE
 * -------------------------------------------------------------
 */
