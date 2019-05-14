/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;

/**
 *
 * @author Students
 */
public class Dashboard {
    private String dashbordTitle = "Admin Dashboard";
    private double todaySales = 150000.0;
    private double weeklyAvgSales = 70000.0;
    private double lastMonthSales = 480000.0;
    private int noOfCustomers = 25000;
    private String mostPurchaseCustomer = "Mr. Yasin";
    private int noOfProductInShop = 380;

    public String getDashbordTitle() {
        return dashbordTitle;
    }

    public double getTodaySales() {
        return todaySales;
    }

    public double getWeeklyAvgSales() {
        return weeklyAvgSales;
    }

    public double getLastMonthSales() {
        return lastMonthSales;
    }

    public int getNoOfCustomers() {
        return noOfCustomers;
    }

    public String getMostPurchaseCustomer() {
        return mostPurchaseCustomer;
    }

    public int getNoOfProductInShop() {
        return noOfProductInShop;
    }

    public void setDashbordTitle(String dashbordTitle) {
        this.dashbordTitle = dashbordTitle;
    }

    public void setTodaySales(double todaySales) {
        this.todaySales = todaySales;
    }

    public void setWeeklyAvgSales(double weeklyAvgSales) {
        this.weeklyAvgSales = weeklyAvgSales;
    }

    public void setLastMonthSales(double lastMonthSales) {
        this.lastMonthSales = lastMonthSales;
    }

    public void setNoOfCustomers(int noOfCustomers) {
        this.noOfCustomers = noOfCustomers;
    }

    public void setMostPurchaseCustomer(String mostPurchaseCustomer) {
        this.mostPurchaseCustomer = mostPurchaseCustomer;
    }

    public void setNoOfProductInShop(int noOfProductInShop) {
        this.noOfProductInShop = noOfProductInShop;
    }
    
    
    
    
}
