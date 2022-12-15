import java.io.*;


public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public void saveBin(File binFile) {
        try (FileOutputStream fos = new FileOutputStream(binFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(this);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadFromBinFile(File binFile) {
        try (FileInputStream fis = new FileInputStream(binFile);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            Basket basketLoad = (Basket) ois.readObject();
            this.products = basketLoad.products;
            this.prices = basketLoad.prices;
            this.units = basketLoad.units;
            counts = basketLoad.counts;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.printf(ex.getMessage());
       }
    }
}