package com.quantitymeasurement.measurement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArithmeticResponseDTO {
    private String expression;   // e.g. "5 METERS + 200 CENTIMETERS = 7.0 METERS"
    private Double resultValue;
    private String resultUnit;
    private String operation;

    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }

    public Double getResultValue() { return resultValue; }
    public void setResultValue(Double resultValue) { this.resultValue = resultValue; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }
}
