package com.quantitymeasurement.measurement.dto;

public class ArithmeticInputDTO {
    private String type;         // LENGTH, TEMPERATURE, VOLUME
    private String operation;    // ADD, SUBTRACT, MULTIPLY, DIVIDE
    private QuantityDTO quantity1;
    private QuantityDTO quantity2;
    private String resultUnit;   // unit to express result in

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public QuantityDTO getQuantity1() { return quantity1; }
    public void setQuantity1(QuantityDTO quantity1) { this.quantity1 = quantity1; }

    public QuantityDTO getQuantity2() { return quantity2; }
    public void setQuantity2(QuantityDTO quantity2) { this.quantity2 = quantity2; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }
}
