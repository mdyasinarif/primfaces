/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Students
 */
@ManagedBean
public class DashboardContoller {
    private Dashboard dashboard;

    public Dashboard getDashboard() {
        if (dashboard == null) {
            dashboard = new Dashboard();
            dashboard.setDashbordTitle("ATM Shop");
        }
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
    
}
