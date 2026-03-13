package dto;

public class QuantityDTO {

    private double value;
    private String unit;
    private String measurementType;

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    // Length Units
    public enum LengthUnit {

        FEET("FEET", "Length"),
        INCHES("INCHES", "Length");

        private String unitName;
        private String measurementType;

        LengthUnit(String unitName, String measurementType) {
            this.unitName = unitName;
            this.measurementType = measurementType;
        }

        public String getUnitName() {
            return unitName;
        }

        public String getMeasurementType() {
            return measurementType;
        }
    }

}