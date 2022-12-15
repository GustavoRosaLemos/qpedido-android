package com.qpedido.android.model;

public class Item {
    private String name;
    private String description;
    private double price;
    private int timesOrdened;

    public Item(String name, String description, double price, int timesOrdened) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.timesOrdened = timesOrdened;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getTimesOrdened() {
        return timesOrdened;
    }
}
