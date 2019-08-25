package Beans;

import Daos.RentDao;
import Daos.UserDao;
import Models.Book;
import Models.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean {

    private String username;
    private String password;
    UserDao userDao = new UserDao();
    User loggedIn_user = new User();
    private boolean logggedIn_user_type = false;
    RentDao rentDao = new RentDao();

    @ManagedProperty("#{settingBean}")
    SettingBean settingBean;

    public SettingBean getSettingBean() {
        return settingBean;
    }

    public void setSettingBean(SettingBean settingBean) {
        this.settingBean = settingBean;
    }

    public User getLoggedIn_user() {
        return loggedIn_user;
    }

    public void setLoggedIn_user(User loggedIn_user) {
        this.loggedIn_user = loggedIn_user;
    }

    public boolean isLogggedIn_user_type() {
        return logggedIn_user_type;
    }

    public void setLogggedIn_user_type(boolean logggedIn_user_tpye) {
        this.logggedIn_user_type = logggedIn_user_tpye;
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
            return null;
        } else {
            if (loggedIn_user.getType().equals("user")) {
                loginTime();
                logggedIn_user_type = true;

                return "userDashboard?faces-redirect=true";

            } else {
                logggedIn_user_type = false;
                return "dashboard?faces-redirect=true";

            }
        }
    }

    public boolean numberOfRentsMax() {
        ArrayList<Book> rentedBook = rentDao.getAllRentedUserBooks(loggedIn_user.getId());
        if (rentedBook.size() < settingBean.getMaxNumberForRent()) {
            return true;
        } else {
            return false;
        }

    }

    public void loginTime() {
        try {
            String string1 = "08:00";
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);
            calendar1.set(Calendar.DATE, 1);

            String string2 = "07:00";
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.set(Calendar.DATE, 1);

            String someRandomTime = new Date().toString();
            Date d = new SimpleDateFormat("HH:mm").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.set(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                System.out.println(true);
                                System.out.println("/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/**/");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
