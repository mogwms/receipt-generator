
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class ReceiptGenerator {
    static final double tax = 6;
    
    public static void createReceipt(String itemName, int quantity, double unitPrice) {
        File output = new File("output.txt");

        double ftax = tax / 100;
        double subtotal = unitPrice * quantity;
        double total = subtotal + subtotal * ftax;

        try (PrintWriter writer = new PrintWriter("output.txt")) {
            Desktop desktop = Desktop.getDesktop();
            writer.print(itemName);
            writer.print("\n" + subtotal);
            writer.print("\n" + total);
            writer.close();
            try {
                desktop.open(output);
                System.out.println("Opening File...");
            } catch (IOException e) {
                System.err.println("Cannot open file");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error - Cannot Write File");
        }
    }
    public static void main(String[] args) {
        String itemName;
        int quantity;
        double unitPrice;

        try (Scanner reader = new Scanner(new FileReader("receipt.txt"))) {
            itemName = reader.nextLine();
            quantity = reader.nextInt();
            unitPrice = reader.nextDouble();
            
            createReceipt(itemName, quantity, unitPrice);

            reader.close();
        } catch (Exception ex) {
            System.err.println("Error - Cannot Read File");
        }
    }
}
