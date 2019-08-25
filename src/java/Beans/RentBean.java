package Beans;

import Daos.RentDao;
import Models.Book;
import Models.Rent;
import Models.User;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

@ManagedBean
@ViewScoped
public class RentBean {

    Rent newRent = new Rent();
    RentDao rentDao = new RentDao();
    ArrayList<Book> allRentForUser = new ArrayList<>();
    ArrayList<Book> allRentedBooks = new ArrayList<>();
    private int rentBookId;
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;
    @ManagedProperty("#{bookBean}")
    BookBean bookBean;
    Date from;
    Date to;

    @PostConstruct
    public void init() {
        try {
            allRentForUser = rentDao.getAllRentedUserBooks(loginBean.loggedIn_user.getId());
            allRentedBooks = rentDao.getAllRentedBooks();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    
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

    public ArrayList<Book> getAllRentedBooks() {
        return allRentedBooks;
    }

    public void setAllRentedBooks(ArrayList<Book> allRentedBooks) {
        this.allRentedBooks = allRentedBooks;
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
        destroyWorld();
        init();
    }

    public BookBean getBookBean() {
        return bookBean;
    }

    public void setBookBean(BookBean bookBean) {
        this.bookBean = bookBean;
    }

    public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }

    public void handleMove(MoveEvent event) {
        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }

    public void destroyWorld() {
        addMessage(" success", "Delete a Rented Book is done.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void filter(Date from, Date to) {
        allRentedBooks = rentDao.getAllRentedBooksFiktered(from, to);

    }

    

}
