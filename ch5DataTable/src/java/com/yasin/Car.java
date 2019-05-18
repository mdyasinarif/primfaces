/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;

import java.util.Objects;

/**
 *
 * @author Students
 */
public class Car {
    public String id;
    public String brand;
    public int year;
    public String color;
    public int price;
    public boolean sold;

    public Car() {
    }

    public Car(String id, String brand, int year, String color, int price, boolean sold) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.sold = sold;
    }

    public Car(String id, String brand, int year, String color) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.brand);
        hash = 31 * hash + this.year;
        hash = 31 * hash + Objects.hashCode(this.color);
        hash = 31 * hash + this.price;
        hash = 31 * hash + (this.sold ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.year != other.year) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.sold != other.sold) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
    
    
}
