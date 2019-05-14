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
public class CarContoller {
    private List<Car> cars ;

    public List<Car> getCars() {
        cars = new ArrayList<>();
        cars.add(new Car("BD Nsno", "Bsnhlsdrdh eoyy dysty doon the car"));
        cars.add(new Car("Tata Nsno", "huio  fsdh kljsdkf fkj eoyy dysty doon the car"));
        cars.add(new Car("MutirTin", "huio  fsdh kljsdkf fkj eoyy dysty doon the car"));
        cars.add(new Car("USA Nano", "huio  fsdh kljsdkf fkj eoyy dysty doon the car"));
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    
}
