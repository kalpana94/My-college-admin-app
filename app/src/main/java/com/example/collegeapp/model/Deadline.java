package com.example.collegeapp.model;

import java.io.Serializable;

public class Deadline implements Serializable {
    public String docid;
    public String newDeadline;
    public String transferdeadline;


    public Deadline() {
    }

    public Deadline(String docid, String newDeadline, String transferdeadline) {
        this.docid = docid;
        this.newDeadline = newDeadline;
        this.transferdeadline = transferdeadline;
    }

    @Override
    public String toString() {
        return "Deadline{" +
                "docid='" + docid + '\'' +
                ", newDeadline='" + newDeadline + '\'' +
                ", transferdeadline='" + transferdeadline + '\'' +
                '}';
    }
}
