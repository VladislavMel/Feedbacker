package com.example.feedbacker;

import androidx.annotation.NonNull;

public class Organization {
    private String address;
    private String name;
    private String id;

    public Organization(String address, String name, String id) {
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Addres: " + address + "\n" +
                "Name: " + name + "\n" +
                "ID: " + id;
    }
}
