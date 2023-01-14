package ru.netology;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Integer.*;
public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        System.out.println("\t Домашнее задание по теме: " + "\"Stream API. Потоки, повторные вызовы, основные методы.\"");
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Яблоки"};
        int[] prices = {55, 79, 95, 110};
        String[] units ={"буханка", "литр", "кг", "кг"};

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("shop.xml");

        XPath xPath =  XPathFactory.newInstance().newXPath();

        boolean doLoad = Boolean.parseBoolean(xPath
                .compile("/config/load/enabled")
                .evaluate(doc));
        String loadFileName = xPath
                .compile("/config/load/fileName")
                .evaluate(doc);
        String loadFormat = xPath
                .compile("/config/load/format")
                .evaluate(doc);
        boolean doSave = Boolean.parseBoolean(xPath
                .compile("/config/save/enabled")
                .evaluate(doc));
        String saveFileName = xPath
                .compile("/config/save/fileName")
                .evaluate(doc);
        String saveFormat = xPath
                .compile("/config/save/format")
                .evaluate(doc);
        boolean doLog = Boolean.parseBoolean(xPath
                .compile("/config/log/enabled")
                .evaluate(doc));
        String logFileName = xPath
                .compile("/config/log/fileName")
                .evaluate(doc);

        Basket basket;
        if (doLoad) {
            File loadFile = new File(loadFileName);
            if (!loadFile.exists()) {
                basket = new Basket(products, prices, units);
            }
            else {
                switch (loadFormat) {
                    case "json":
                        basket = Basket.loadFromJsonFile(loadFile);
                        break;
                    case "txt":
                        basket = Basket.loadFromTxtFile(loadFile);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + loadFormat);
                }
            }
        } else {
            basket = new Basket(products, prices, units);
        }
        ClientLog clientLog = new ClientLog();

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

        if (doSave) {
            File saveFile = new File(saveFileName);
            if (!saveFile.exists()) saveFile.createNewFile();
            switch (saveFormat) {
                case "json": basket.saveJson(saveFile); break;
                case "txt":  basket.saveTxt(saveFile); break;
            }
        }
        if (doLog) {
            File logFile = new File(logFileName);
            if (!logFile.exists()) logFile.createNewFile();
            clientLog.exportAsCSV(logFile);
        }
    }
}