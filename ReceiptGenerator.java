
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ReceiptGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        File receipt = new File("C:\\Users\\osawe\\Desktop\\Bowie State University\\Computer Science I\\Assignments\\final-programming-project\\receipt\\output.txt");

        try (PrintWriter writer = new PrintWriter("C:\\Users\\osawe\\Desktop\\Bowie State University\\Computer Science I\\Assignments\\final-programming-project\\receipt\\output.txt")) {
            Desktop desktop = Desktop.getDesktop();
            writer.print("Testing Output Using PrintWriter");
            writer.close();
            try {
                desktop.open(receipt);
                System.out.println("Opening File...");
            } catch (IOException e) {
                System.err.println("Cannot open filee");
            }
         }
    }
}
