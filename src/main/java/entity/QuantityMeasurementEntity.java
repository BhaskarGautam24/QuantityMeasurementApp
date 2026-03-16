package entity;

public class QuantityMeasurementEntity {

    private String operation;
    private String thisQuantity;
    private String thatQuantity;
    private String result;

    public QuantityMeasurementEntity(String operation,
                                     String thisQuantity,
                                     String thatQuantity,
                                     String result) {

        this.operation = operation;
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public String getThisQuantity() {
        return thisQuantity;
    }

    public String getThatQuantity() {
        return thatQuantity;
    }

    public String getResult() {
        return result;
    }
}