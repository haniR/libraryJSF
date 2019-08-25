package Beans;

import Daos.BlacklistDao;
import Models.BlackList;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BlacklistBean {

    private ArrayList<BlackList> allBlockedUsers = new ArrayList<BlackList>();
    private BlacklistDao blacklistDao = new BlacklistDao();
    BlackList newBlackList = new BlackList();
    BlackList userblocked = new BlackList();
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;
    
    @PostConstruct
    public void init(){
        try{
            allBlockedUsers = blacklistDao.getBlockedUsers();
            userblocked = blacklistDao.getBlockedUser(loginBean.loggedIn_user.getId());
        }catch(Exception  e){
            e.printStackTrace();
        }
    }

    public BlackList getUserblocked() {
        return userblocked;
    }

    public void setUserblocked(BlackList userblocked) {
        this.userblocked = userblocked;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
    
    public BlackList getNewBlackList() {
        return newBlackList;
    }

    public void setNewBlackList(BlackList newBlackList) {
        this.newBlackList = newBlackList;
    }

    public ArrayList<BlackList> getAllBlockedUsers() {
        return allBlockedUsers;
    }

    public void setAllBlockedUsers(ArrayList<BlackList> allBlockedUsers) {
        this.allBlockedUsers = allBlockedUsers;
    }
    public void addBlockUser(){
        blacklistDao.addBlockUser(newBlackList);
        System.out.println(newBlackList.getUserId());
        init();
    }
    public void remove(int id){
        blacklistDao.deleteBlockUser(id);
        init();
    }
    

}
