package ru.netology;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import java.io.*;
import java.text.ParseException;

public class Basket implements Serializable {

    private static final long serialVersionUID = 1L;
    final private String[] products;
    final private int[] prices;
    final private String[] units;
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

    public void saveJson(File jsonFile) throws IOException {
        JSONObject basketJson = new JSONObject();
        basketJson.put("product1", products[0]);
        basketJson.put("product2", products[1]);
        basketJson.put("product3", products[2]);
        basketJson.put("product4", products[3]);
        basketJson.put("price1", prices[0]);
        basketJson.put("price2", prices[1]);
        basketJson.put("price3", prices[2]);
        basketJson.put("price4", prices[3]);
        basketJson.put("unit1", units[0]);
        basketJson.put("unit2", units[1]);
        basketJson.put("unit3", units[2]);
        basketJson.put("unit4", units[3]);
        basketJson.put("count1", counts[0]);
        basketJson.put("count2", counts[1]);
        basketJson.put("count3", counts[2]);
        basketJson.put("count4", counts[3]);
        try (FileWriter file = new FileWriter(jsonFile)) {
            file.write(basketJson.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromJsonFile(File jsonFile) throws IOException {

    }
}