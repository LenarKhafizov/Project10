import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("\t Домашнее задание по теме: " + "\"Потоки ввода-вывода. Работа с файлами. Сериализация.\"");
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Гречневая крупа", "Яблоки"};
        int[] prices = {55, 79, 95, 110};
        String[] units ={"буханка", "литр", "кг", "кг"};
        int[] counts = {0, 0, 0, 0};
        int productSumAll = 0;
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
            int i = productNumber-1;
            counts[i] = counts[i] + productCount;
        }
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