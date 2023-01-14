package ru.netology;

import java.io.*;
import java.util.Scanner;
import com.google.gson.Gson;

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
    private Basket() {
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
    public void saveTxt(File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(products.length);
            for (String product : products) {
                out.println(product);
            }
            for (int price : prices) {
                out.println(price);
            }
            for (String unit : units) {
                out.println(unit);
            }
            for (int count : counts) {
                out.println(count);
            }
        }
    }
    public void saveJson(File jsonFile) throws IOException {
        try (PrintWriter out = new PrintWriter(jsonFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            out.println(json);
        }
    }
    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (Scanner scanner = new Scanner(textFile)) {
            Basket basket = new Basket();
            int size = Integer.parseInt(scanner.nextLine());
            basket.products = new String[size];
            basket.prices = new int[size];
            basket.units = new String[size];
            basket.counts = new int[size];

            for (int i = 0; i < basket.products.length; i++) {
                basket.products[i] = scanner.nextLine();
            }
            for (int i = 0; i < basket.products.length; i++) {
                basket.prices[i] = Integer.parseInt(scanner.nextLine());
            }
            for (int i = 0; i < basket.products.length; i++) {
                basket.units[i] = scanner.nextLine();
            }
            for (int i = 0; i < basket.products.length; i++) {
                basket.counts[i] = Integer.parseInt(scanner.nextLine());
            }
            return basket;
        }
    }
    public static Basket loadFromJsonFile(File jsonFile) throws IOException {
        try (Scanner scanner = new Scanner(jsonFile)) {
            Gson gson = new Gson();
            String json = scanner.nextLine();
            Basket basket = gson.fromJson(json, Basket.class);
            return basket;
        }
    }
}