package ru.netology;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClientLog {
    private final ArrayList<Integer> clientOperations;

    public ClientLog() {
        clientOperations = new ArrayList<>();
    }

    public void log(Integer productNum, Integer amount) {
        clientOperations.add(productNum);
        clientOperations.add(amount);
    }

    public void exportAsCSV(File csvFile) {
        String operations = "productNum" + "," + "amount";
        for (int i = 0; i < clientOperations.size(); i++) {
            operations = operations + ",";
            operations = operations + clientOperations.get(i).toString();
        }
        String[] operationsString = operations.split(",");
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            writer.writeNext(operationsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getClientOperations(int i) {
        return clientOperations.get(i);
    }
}
