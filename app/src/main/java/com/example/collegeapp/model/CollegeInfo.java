package com.example.collegeapp.model;

import java.io.Serializable;

public class CollegeInfo implements Serializable {
    String docid;
   public String info;

    public CollegeInfo() {
    }

    public CollegeInfo(String docid, String info) {
        this.docid = docid;
        this.info = info;
    }

    public CollegeInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "info='" + info + '\'' +
                '}';
    }
}
