package com.quantitymeasurement.entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String operand1;
    private String operand2;
    private String result;

    public QuantityMeasurementEntity(String operation, String operand1, String operand2, String result) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getResult() {
        return result;
    }
}