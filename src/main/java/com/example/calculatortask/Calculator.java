package com.example.calculatortask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Calculator {

    public static void main(String[] args) throws Exception {

//  INSTRUCTIONS

        File file = new File("calculator.txt");

        createFile(file.getPath());

        List<CalculatorValues> calculatorValues = new ArrayList<>();

        try (Scanner scan = new Scanner(file)) {
          while (scan.hasNextLine()){
            String line = scan.nextLine();
            String[] value = line.split(" ");
//            System.out.println(Arrays.toString(value));
            Double numberValue = Double.parseDouble(value[1]);
            calculatorValues.add(new CalculatorValues(value[0], numberValue));
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Result: " + calculateResult(calculatorValues) + "\n");
    }

//  METHODS

    private static boolean checkFileExist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nazwe pliku: \n");

        Path path = Paths.get(scanner.nextLine());

        return Files.exists(path);
    }

    private static void createFile(String file) throws IOException {

        Path path = Paths.get(file);
        if (!Files.exists(path)) {
            System.out.println("Plik o podanej nazwie nie istnieje: " + checkFileExist() + "\n");
            Files.createFile(path);
        } else {
            System.out.println("Plik o podanej nazwie istnieje: " + checkFileExist() + "\n");
        }
    }
    private static Double calculateResult(List<CalculatorValues> result) {

        Double sum = 0.0;

        Optional<CalculatorValues> applyValue = result.stream()
                .filter(r -> r.getOperation().equals("apply"))
                .findAny();
        if (applyValue.isPresent()) {
            sum = applyValue.get().getValue();
        }

        for (CalculatorValues c : result) {

            sum = switched(c, sum);
        }
        return  sum;
    }

    private static Double switched(CalculatorValues calculatorValue, Double sum) {
        switch (calculatorValue.getOperation()) {
            case "add":
                return sum + calculatorValue.getValue();
            case "subtract":
                return sum - calculatorValue.getValue();
            case "multiply":
                return sum * calculatorValue.getValue();
            case "divide":
                if (calculatorValue.getOperation().equals("divide") && calculatorValue.getValue() != 0) {
                    return sum / calculatorValue.getValue();
                } else {
                    System.out.println("Błędy obliczeniowe, nie dzielimy przez 0!" + "\n");
                }
        }
        return sum;
    }
}
