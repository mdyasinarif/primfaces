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

public class Car {
    private String carName ;
    private String carDetelis ;

     public Car() {
    }

    public Car(String carName, String carDetelis) {
        this.carName = carName;
        this.carDetelis = carDetelis;
    }

   
    

    
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarDetelis() {
        return carDetelis;
    }

    public void setCarDetelis(String carDetelis) {
        this.carDetelis = carDetelis;
    }

   
    
    
}
