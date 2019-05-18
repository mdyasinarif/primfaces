/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhendeler;

import dao.AddDao;
import entity.Transtion;
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
public class TranstionMB {
    Transtion transtion = new Transtion();

    public Transtion getTranstion() {
        return transtion;
    }

    public void setTranstion(Transtion transtion) {
        this.transtion = transtion;
    }
    
    public String addTranstion(){
        transtion.setAccountNo(transtion.getAccountNo());
        transtion.setDate(transtion.getDate());
        transtion.setDeposit(transtion.getDeposit());
        transtion.setId(transtion.getId());
        transtion.setName(transtion.getName());
        transtion.setSlipNo(transtion.getSlipNo());
        transtion.setWithdraw(transtion.getWithdraw());
        boolean status = new AddDao().addTranstion(transtion);
        if (status) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Save", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data  not Save", ""));
        }
    return null;
    }
}
