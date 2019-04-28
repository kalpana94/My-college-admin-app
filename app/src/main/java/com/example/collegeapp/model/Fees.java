package com.example.collegeapp.model;

import java.io.Serializable;

public class Fees implements Serializable {
    public  String newstu;
    public  String transferstu;

    public Fees() {
    }

    public Fees(String newstu, String transferstu) {
        this.newstu = newstu;
        this.transferstu = transferstu;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "newstu='" + newstu + '\'' +
                ", transferstu='" + transferstu + '\'' +
                '}';
    }
}
