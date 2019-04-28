package com.example.collegeapp.model;

import java.io.Serializable;

public class Courses implements Serializable {
    public  String id;
    public  String doc_Id;
    public String Name;

    public Courses() {
    }

    public Courses(String id, String name,String doc_Id) {
        this.id = id;
        this.Name = name;
        this.doc_Id=doc_Id;
    }

    @Override
    public String toString() {
        return "Courses{" +
                "id='" + id + '\'' +
                ", doc_Id='" + doc_Id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
