import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\t Домашнее задание по теме: " + "\"Потоки ввода-вывода. Работа с файлами. Сериализация.\"");
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Яблоки"};
        int[] prices = {55, 79, 95, 110};
        String[] units ={"буханка", "литр", "кг", "кг"};
        Basket basket = new Basket(products, prices, units);
        File textFile = new File("basket.txt");
        if (!textFile.exists()) {
            textFile.createNewFile();
        } else {
            basket.loadFromTxtFile(textFile);
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
            int productNumber = Integer.parseInt(parts[0]);
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
        }
        basket.printCart();
        basket.saveTxt(textFile);
    }
}