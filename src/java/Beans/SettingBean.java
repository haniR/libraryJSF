/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Daos.RentDao;
import Models.Book;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author ASUS
 */
@ManagedBean
@ApplicationScoped
public class SettingBean {

    RentDao rentDao = new RentDao();
    private int maxNumberForRent = 3;
    Date from;
    Date to;

    String rentMaxMessage;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getRentMaxMessage() {
        return rentMaxMessage;
    }

    public void setRentMaxMessage(String rentMaxMessage) {
        this.rentMaxMessage = rentMaxMessage;
    }

    public int getMaxNumberForRent() {
        return maxNumberForRent;
    }

    public void setMaxNumberForRent(int maxNumberForRent) {
        this.maxNumberForRent = maxNumberForRent;
    }

    public void maxRentMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry!!", "You excceded your number for rented books you just allowed 3 "));
    }

    public void saveData(int maxRent, Date froma, Date toa) {
        maxNumberForRent = maxRent;
        from = froma;
        to = toa;
    }


}
