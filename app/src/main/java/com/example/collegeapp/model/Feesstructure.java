package com.example.collegeapp.model;

public class Feesstructure {
    public  String feeInfo;
    public  String paymentmethod;

    public Feesstructure() {
    }

    public Feesstructure(String feeInfo, String paymentmethod) {
        this.feeInfo = feeInfo;
        this.paymentmethod = paymentmethod;
    }

    @Override
    public String toString() {
        return "Feesstructure{" +
                "feeInfo='" + feeInfo + '\'' +
                ", paymentmethod='" + paymentmethod + '\'' +
                '}';
    }
}
