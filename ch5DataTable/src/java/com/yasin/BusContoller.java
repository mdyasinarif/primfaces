/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Students
 */
@ManagedBean
public class BusContoller {
    private List<Bus> busList;
    private Bus selectedBus;

    public Bus getSelectedBus() {
        return selectedBus;
    }

    public void setSelectedBus(Bus selectedBus) {
        this.selectedBus = selectedBus;
    }

   

    public List<Bus> getBusList() {
        busList = new ArrayList<>();
        busList.add(new Bus("100", "1980", "Toyto", "Red"));
        busList.add(new Bus("101", "1990", "Nano", "Green"));
        busList.add(new Bus("102", "2010", "Dhakar Chaka", "Blue"));
        busList.add(new Bus("103", "2019", "BRTC Charakar", "Red"));
        busList.add(new Bus("104", "2015", "Bandu", "Kala"));
        busList.add(new Bus("105", "2019", "Khandu", "Sada"));
        busList.add(new Bus("106", "1920", "Xkorola", "White"));
        busList.add(new Bus("107", "2016", "BMW", "Black"));
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }
    
    
}
