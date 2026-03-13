package com.quantitymeasurement;

public class Quantity<U extends InterfaceMeasurable> {

    private double value;
    private U unit;

    private static final double EPSILON = 1e-6;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }
    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, unit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }
    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, unit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }
    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Incompatible unit categories");

        if (!Double.isFinite(value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }
    private enum ArithmeticOperation {

        ADD {
            @Override
            double compute(double a, double b) {
                return a + b;
            }
        },

        SUBTRACT {
            @Override
            double compute(double a, double b) {
                return a - b;
            }
        },

        DIVIDE {
            @Override
            double compute(double a, double b) {

                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");

                return a / b;
            }
        };

        abstract double compute(double a, double b);
    }
}