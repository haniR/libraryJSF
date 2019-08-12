package Beans;

import Daos.UserDao;
import Models.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean {

    private String username;
    private String password;
    UserDao userDao = new UserDao();
    User loggedIn_user = new User();
    private boolean logggedIn = false;

    public User getLoggedIn_user() {
        return loggedIn_user;
    }

    public void setLoggedIn_user(User loggedIn_user) {
        this.loggedIn_user = loggedIn_user;
    }

    public boolean isLogggedIn() {
        return logggedIn;
    }

    public void setLogggedIn(boolean logggedIn) {
        this.logggedIn = logggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        loggedIn_user = userDao.login(username, password);
        if (loggedIn_user == null) {
            logggedIn = false;
            return null;
        } else {
            if (loggedIn_user.getType().equals("user")) {
                logggedIn = true;

                return "newRentalTransaction?faces-redirect=true";

            } else {
                logggedIn = true;
                return "dashboard?faces-redirect=true";

            }
        }
    }

}
