package Beans;

import Daos.BookDao;
import Models.Book;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BookBean {

    private BookDao bookDao = new BookDao();
    private ArrayList<Book> allBooks = new ArrayList<>();
    private ArrayList<Book> wishListBooks = new ArrayList<>();
    private ArrayList<Book> allBookCanRent = new ArrayList<>();
    private ArrayList<Book> allBooksForUser = new ArrayList<>();

    private int userId;
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;

    public ArrayList<Book> getAllBookCanRent() {
        return allBookCanRent;
    }

    public void setAllBookCanRent(ArrayList<Book> allBookCanRent) {
        this.allBookCanRent = allBookCanRent;
    }

    public ArrayList<Book> getAllBooksForUser() {
        return allBooksForUser;
    }

    public void setAllBooksForUser(ArrayList<Book> allBooksForUser) {
        this.allBooksForUser = allBooksForUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @PostConstruct
    public void init() {
        try {
            allBookCanRent = bookDao.getAllBooksNotRented(loginBean.loggedIn_user.getId());
            allBooksForUser = bookDao.getAllBooksNotWishedAndRented(loginBean.loggedIn_user.getId());
            allBooks = bookDao.getAllBooks();
            wishListBooks = bookDao.getWishListBooks(loginBean.loggedIn_user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getWishListBooks() {
        return wishListBooks;
    }

    public void setWishListBooks(ArrayList<Book> wishListBooks) {
        this.wishListBooks = wishListBooks;
    }

    public ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(ArrayList<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public void filter(String author, String title, String genre) {
        allBooks = bookDao.getAllBooksFilterization(author, title, genre);
    }

    public void filterForRent(int userId, String author, String title, String genre) {
        allBookCanRent = bookDao.getAllBooksFilterizationForRent(userId, author, title, genre);
    }

    public void addToWishList(int userId, int bookId) {
        bookDao.addToWishList(userId, bookId);
        init();
    }

    public void removeFromWishList(int userId, int bookId) {
        bookDao.removeFromWishList(userId, bookId);
        init();
    }

//    public ArrayList<Book> getWishListBooks(int userId) {
//        wishListBooks = bookDao.getWishListBooks(userId);
//        return wishListBooks;
//    }
    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
