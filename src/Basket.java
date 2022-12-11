import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static com.sun.org.apache.xml.internal.utils.XMLCharacterRecognizer.isWhiteSpace;

public class Basket {
    private String[] products;
    private int[] prices;
    private String[] units;
    private int[] counts = {0, 0, 0, 0};

    public Basket(String[] products, int[] prices, String[] units) {
        this.products = products;
        this.prices = prices;
        this.units = units;
    }
    public int getCounts(int i) {
        return counts[i];
    }

    public void addToCart(int productNum, int amount) {
        int i = productNum - 1;
        counts[i] = counts[i] + amount;
    }

    public void printCart() {
        int productSumAll = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < products.length; i++) {
            if (counts[i] != 0) {
                int productSum = counts[i] * prices[i];
                productSumAll = productSumAll + productSum;
                System.out.println(products[i] + " - " + counts[i] + " (" + units[i] + "), в сумме: " + productSum + " руб.");
            }
        }
        System.out.println("Общая сумма покупки: " + productSumAll + " руб.");
    }
    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            byte[] bytes = new byte[counts.length];
            for (int e = 0; e < counts.length; e++) {
                bytes[e]= (byte) counts[e];
                out.print(bytes[e] + " ");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void loadFromTxtFile(File textFile) throws IOException {
        try (FileInputStream f = new FileInputStream(textFile)) {
            byte[] bytes = new byte[(char)textFile.length()];
            f.read(bytes);
            String inputFromFile = "";
            for (int i = 0; i < bytes.length; i++) {
                char s = (char) bytes[i];
                inputFromFile = inputFromFile + s; //
            }
            String[] parts = inputFromFile.split(" ");
            for (int i = 0; i < parts.length; i++) {
                counts[i] = Integer.parseInt(parts[i]);
                }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}