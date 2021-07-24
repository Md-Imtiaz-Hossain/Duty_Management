package com.example.pack.enums;

public enum  Designation {

    CEO ("CEO"),
    CXO ("CXO"),
    CFO ("CFO"),
    CGO ("CGO");


    private final String name;

    Designation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
