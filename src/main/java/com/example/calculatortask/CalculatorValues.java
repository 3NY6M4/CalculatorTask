package com.example.calculatortask;

public class CalculatorValues {

    private String operation;
    private Double value;

    public CalculatorValues(String operation, Double value) {
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public Double getValue() {
        return value;
    }
}
