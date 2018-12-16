package com.alex.entity;

public class Materials {
    private int id;
    private String name;
    private double price;
    private int thickness;

    public Materials(String name, double price, int thickness) {
        this.name = name;
        this.price = price;
        this.thickness = thickness;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"name\" : \"" + name +
                "\", \"price\" : " + price +
                ", \"thickness\" : " +thickness +
                '}';
    }

    public Materials(int id, String name, double price, int thickness) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.thickness = thickness;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getThickness() {
        return thickness;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setThickness(double price) {
        this.thickness = thickness;
    }
}
