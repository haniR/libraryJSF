package Beans;

import Daos.BookDao;
import Models.Book;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BookBean {

    private BookDao bookDao = new BookDao();
    private ArrayList<Book> allBooks = new ArrayList<>();
    private ArrayList<Book> wishListBooks = new ArrayList<>();
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

    @PostConstruct
    public void init() {
        try {
            allBooks = bookDao.getAllBooks();
            wishListBooks = getWishListBooks( userId);
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

    public void addToWishList(int userId, int bookId) {
        bookDao.addToWishList(userId, bookId);
        getWishListBooks(userId);
    }

    public ArrayList<Book> getWishListBooks(int userId) {
        wishListBooks = bookDao.getWishListBooks(userId);
        return wishListBooks;
    }

}
