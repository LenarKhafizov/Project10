public class Basket {
    private String[] products;
    private int[] prices;
    private String[] units;
    private int[] counts ={0, 0, 0, 0};

    public Basket (String[] products, int[] prices, String[] units) {
        this.products = products;
        this.prices = prices;
        this.units = units;
    }
    public void addToCart (int productNum, int amount) {
        int i = productNum-1;
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
}
