package com.edutilos.model;

import lombok.Data;

@Data
public class Person {
    private long id;
    private String name;
    private int age;
    private double wage;
    private boolean active;

    public Person(long id, String name, int age, double wage, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.wage = wage;
        this.active = active;
    }

    public Person() {
    }
}
