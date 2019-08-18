package Beans;

import Daos.UserDao;
import Models.User;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean {

    private UserDao userDao = new UserDao();
    private ArrayList<User> allUsers = new ArrayList<User>();

    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public UserBean() {
    }

}
