package com.example.collegeapp.model;

import java.io.Serializable;
import java.security.PublicKey;

public class Guidelines implements Serializable {
    public  String guideln;

    public Guidelines() {
    }

    public Guidelines(String guideln) {
        this.guideln = guideln;
    }

    @Override
    public String toString() {
        return "Guidelines{" +
                "guideln='" + guideln + '\'' +
                '}';
    }
}
