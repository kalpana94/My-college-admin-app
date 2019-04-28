package com.example.collegeapp.model;

import java.io.Serializable;

public class Contact implements Serializable {
    public String address;
    public String website;
    public String email;
    public String  phone;

    public Contact() {

    }

    public Contact(String address, String website, String email, String phone) {
        this.address = address;
        this.website = website;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "address='" + address + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
