package com.quantitymeasurement.measurement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * DTO for inter-service communication with user-service history endpoints.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryDTO {
    private String operation;
    private String operand1;
    private String operand2;
    private String result;
    private String userEmail;
    private String resultString;
    private Double resultValue;
    private String unit;

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public String getOperand1() { return operand1; }
    public void setOperand1(String operand1) { this.operand1 = operand1; }

    public String getOperand2() { return operand2; }
    public void setOperand2(String operand2) { this.operand2 = operand2; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getResultString() { return resultString; }
    public void setResultString(String resultString) { this.resultString = resultString; }

    public Double getResultValue() { return resultValue; }
    public void setResultValue(Double resultValue) { this.resultValue = resultValue; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
