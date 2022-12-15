package com.qpedido.android.model;

public class Restaurant {
    private String name;
    private String icon;
    private double latitude;
    private double longitude;

    public Restaurant(String name, String icon, double latitude, double longitude) {
        this.name = name;
        this.icon = icon;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
