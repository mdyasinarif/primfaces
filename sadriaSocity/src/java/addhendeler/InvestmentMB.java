/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhendeler;

import dao.AddDao;
import entity.Investment;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author hp
 */
@ManagedBean
@SessionScoped
public class InvestmentMB {
    Investment investment = new Investment();

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }
    public String addInvestment(){
        investment.setAccountNo(investment.getAccountNo());
        investment.setDateofBirth(investment.getDateofBirth());
        investment.setDirectortName(investment.getDirectortName());
        investment.setGender(investment.getGender());
        investment.setId(investment.getId());
        investment.setMobileNo(investment.getMobileNo());
        investment.setNidno(investment.getNidno());
        investment.setParmanetAddress(investment.getParmanetAddress());
        investment.setPresentAddress(investment.getPresentAddress());
        investment.setProjectLocation(investment.getProjectLocation());
        investment.setProjectDuration(investment.getProjectDuration());
        investment.setProjectName(investment.getProjectName());
        investment.setReligion(investment.getReligion());
        investment.setStartDate(investment.getStartDate());
        investment.setTotalInvestment(investment.getTotalInvestment());
        boolean status = new AddDao().addInvestment(investment);
        if (status) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Save", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data  not Save", ""));
        }
    return null;
    }
}
