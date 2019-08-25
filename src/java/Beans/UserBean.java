package Beans;

import Daos.UserDao;
import Models.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean {

    private UserDao userDao = new UserDao();
    User newUser = new User();
    User oldUser = new User();
    User editedUser = new User();
    boolean isBlocked = false;

    private ArrayList<User> allUsers = new ArrayList<User>();

    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;
    @ManagedProperty("#{listBean}")
    ListBean listBean;

    @PostConstruct
    public void init() {
        try {
            allUsers = userDao.getUsers();
            List<Integer> ids = listBean.getUsersIdBlocked();

            if (ids.contains(loginBean.loggedIn_user.getId())) {
                isBlocked = false;

            } else {
                isBlocked = true;

            }
            System.out.println(isBlocked);
        } catch (Exception e) {

        }
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    
    public User getEditedUser() {
        return editedUser;
    }

    public void setEditedUser(User editedUser) {
        this.editedUser = editedUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public User getOldUser() {
        return oldUser;
    }

    public void setOldUser(User oldUser) {
        this.oldUser = oldUser;
    }

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

    public ListBean getListBean() {
        return listBean;
    }

    public void setListBean(ListBean listBean) {
        this.listBean = listBean;
    }

    public UserBean() {
    }

    public void addUser() {
        try {
            System.err.println("user add func");
            userDao.addUser(newUser);
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void fillEditData() {
        editedUser.setName(oldUser.getName());
        editedUser.setUsername(oldUser.getUsername());
        editedUser.setPassword(oldUser.getPassword());
        editedUser.setType(oldUser.getType());
        editedUser.setDob(oldUser.getDob());
    }

    public void saveData() {
        editedUser.getName();
        editedUser.getDob();
        editedUser.getPassword();
        editedUser.getType();
        editedUser.getUsername();
//        init();
    }

    public void editUser() {
        try {
            editedUser.setId(oldUser.getId());
            System.err.println("user edit func");
            userDao.editUser(editedUser);

            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void removeFromUsers(int userId) {
        try {
            userDao.deleteUser(userId);
            init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



}
