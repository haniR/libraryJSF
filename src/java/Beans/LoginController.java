/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sohayb Saleh
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private boolean logggedIn = false;

    public boolean isLogggedIn() {
        return logggedIn;
    }

    public void setLogggedIn(boolean logggedIn) {
        this.logggedIn = logggedIn;
    }

}
