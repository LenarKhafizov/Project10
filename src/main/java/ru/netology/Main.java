package ru.netology;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\t Домашнее задание по теме: " + "\"Потоки ввода-вывода. Работа с файлами. Сериализация.\"");
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Яблоки"};
        int[] prices = {55, 79, 95, 110};
        String[] units ={"буханка", "литр", "кг", "кг"};
        Basket basket = new Basket(products, prices, units);
        ClientLog clientLog = new ClientLog();

        File csvFile = new File("log.csv");
        if (!csvFile.exists()) csvFile.createNewFile();

        File jsonFile = new File("basket.json");
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        } else {
            basket.loadFromJsonFile(jsonFile);
        }
        System.out.println("Список товаров, доступных для покупки:");
        for (int i = 0; i < products.length; i++) {
            int j = i + 1;
            System.out.println(j + ". " + products[i] + " - " + prices[i] + " рублей/" + units[i]+ ".");
        }
        System.out.println();
        while (true) {
            System.out.println("Выберите товар, введите его номер и количество либо введите 'end' для завершения:");
            String inputString = scanner.nextLine();
            if ("end".equals(inputString)) {
                break;
            }
            String[] parts = inputString.split(" ");
            int productNumber = parseInt(parts[0]);
            int productCount = parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            clientLog.log(productNumber,productCount);
        }
        basket.printCart();
        basket.saveJson(jsonFile);
        clientLog.exportAsCSV(csvFile);
    }
}