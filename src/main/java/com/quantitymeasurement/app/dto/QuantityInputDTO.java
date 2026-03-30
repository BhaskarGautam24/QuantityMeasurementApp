package com.quantitymeasurement.app.dto;

public class QuantityInputDTO {
    private QuantityDTO thisQuantityDTO;
    private QuantityDTO thatQuantityDTO;
    private String type; // LENGTH, TEMPERATURE, VOLUME

    public QuantityDTO getThisQuantityDTO() { return thisQuantityDTO; }
    public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) { this.thisQuantityDTO = thisQuantityDTO; }
    public QuantityDTO getThatQuantityDTO() { return thatQuantityDTO; }
    public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) { this.thatQuantityDTO = thatQuantityDTO; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
