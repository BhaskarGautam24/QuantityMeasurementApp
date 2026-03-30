package com.quantitymeasurement.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private String resultString;
    private Double resultValue;
    private String unit;

    public String getResultString() { return resultString; }
    public void setResultString(String resultString) { this.resultString = resultString; }
    public Double getResultValue() { return resultValue; }
    public void setResultValue(Double resultValue) { this.resultValue = resultValue; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}
