package entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private String operation;
    private String thisQuantity;
    private String thatQuantity;
    private String result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation, String thisQuantity,
                                     String thatQuantity, String result) {
        this.operation = operation;
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operation, String thisQuantity,
                                     String thatQuantity, String errorMessage, boolean error) {
        this.operation = operation;
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public boolean isError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}