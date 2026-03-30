package com.quantitymeasurement.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;
    private String operand1;
    private String operand2;
    private String result;

    @Column(name = "user_email")
    private String userEmail;

    public Long getId() { return id; }

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
}
