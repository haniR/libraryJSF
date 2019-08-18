package Beans;

import Daos.RentDao;
import Models.Book;
import Models.Rent;
import Models.User;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RentBean {

    Rent newRent = new Rent();
    RentDao rentDao = new RentDao();
    ArrayList<Book> allRentForUser = new ArrayList<>();
    private int rentBookId;
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;
    @ManagedProperty("#{bookBean}")
    BookBean bookBean;

    @PostConstruct
    public void init() {
        try {
            allRentForUser = rentDao.getAllRentedUserBooks(loginBean.loggedIn_user.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Book> getAllRentForUser() {
        return allRentForUser;
    }

    public void setAllRentForUser(ArrayList<Book> allRentForUser) {
        this.allRentForUser = allRentForUser;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void addRent(int userId) {

        newRent.setBookId(rentBookId);
        newRent.setFrom(new Date());
        newRent.setUserId(userId);
        rentDao.rentBook(newRent);
        bookBean.init();

    }

    public Rent getNewRent() {
        return newRent;
    }

    public int getRentBookId() {
        return rentBookId;
    }

    public void setRentBookId(int rentBookId) {
        this.rentBookId = rentBookId;
    }

    public void setNewRent(Rent newRent) {
        this.newRent = newRent;
    }

    public void bookIdToBeRent(int bookId) {
        System.out.println("book function ");
        rentBookId = bookId;
    }

    public void removeRentedBook(int bookId) {
        rentDao.removeRentedBook(loginBean.loggedIn_user.getId(), bookId);
        init();
    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }
    

}
