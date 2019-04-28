package com.example.collegeapp.model;

public class Admission {
    public String guidelines;
    public String registrationform;
    public String feestructure;

    public Admission() {
    }

    public Admission(String guidelines, String registrationform, String feestructure) {
        this.guidelines = guidelines;
        this.registrationform = registrationform;
        this.feestructure = feestructure;
    }

    @Override
    public String toString() {
        return "Admission{" +
                "guidelines='" + guidelines + '\'' +
                ", registrationform='" + registrationform + '\'' +
                ", feestructure='" + feestructure + '\'' +
                '}';
    }
}
