/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhendeler;

import entity.Summary;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hp
 */
@ManagedBean
@SessionScoped
public class SummaryMB {
    Summary summary = new Summary();

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }
    public String addSummary(){
        summary.setAccountNo(summary.getAccountNo());
    return null;
    }
}
