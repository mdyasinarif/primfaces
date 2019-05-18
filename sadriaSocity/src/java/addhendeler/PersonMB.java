/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhendeler;

import dao.AddDao;
import entity.Person;
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
public class PersonMB {
    Person person = new Person();

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    public String addPerson(){
        person.setAccountNo(person.getAccountNo());
        person.setAdmissionDate(person.getAdmissionDate());
        person.setAnnunity(person.getAnnunity());
        person.setCardNo(person.getCardNo());
        person.setDateofBirth(person.getDateofBirth());
        person.setFatherHusbendName(person.getFatherHusbendName());
        person.setFromNo(person.getFromNo());
        person.setGender(person.getGender());
        person.setId(person.getId());
        person.setMobileNo(person.getMobileNo());
        person.setMotherName(person.getMotherName());
        person.setName(person.getName());
        person.setNidno(person.getNidno());
        person.setNomineName(person.getNomineName());
        person.setParmanetAddress(person.getParmanetAddress());
        person.setPresentAddress(person.getPresentAddress());
        person.setRelations(person.getReligion());
        person.setSavingType(person.getSavingType());
        person.setShare(person.getShare());
        boolean status = new AddDao().addPerson(person);
        if (status) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Save", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data  not Save", ""));
        }
    return null;
    }
}
