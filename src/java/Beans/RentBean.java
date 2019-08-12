package Beans;

import Daos.RentDao;
import Models.Rent;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class RentBean {

    Rent newRent = new Rent();
    RentDao rentDao = new RentDao();
    Boolean isRent = false;
    private int rentBookId;

    public void addRent( int userId) {
        
        newRent.setBookId(rentBookId);
        newRent.setFrom(new Date());
        newRent.setUserId(userId);
        rentDao.rentBook(newRent);
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
    public void bookIdToBeRent(int bookId){
        rentBookId = bookId;
    }

}
