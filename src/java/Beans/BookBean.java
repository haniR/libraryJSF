package Beans;

import Daos.BookDao;
import Daos.RentDao;
import Models.Book;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

@ManagedBean
@ViewScoped
public class BookBean {

    private BookDao bookDao = new BookDao();
    private RentDao rentDao = new RentDao();
    private ArrayList<Book> allBooks = new ArrayList<>();
    private ArrayList<Book> wishListBooks = new ArrayList<>();
    private ArrayList<Book> allBookCanRent = new ArrayList<>();
    private ArrayList<Book> allBooksForUser = new ArrayList<>();
    Book newBook = new Book();
    Book oldBook = new Book();
    Book editedBook = new Book();

    private int userId;
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;

    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public Book getOldBook() {
        return oldBook;
    }

    public void setOldBook(Book oldBook) {
        this.oldBook = oldBook;
    }

    public Book getEditedBook() {
        return editedBook;
    }

    public void setEditedBook(Book editedBook) {
        this.editedBook = editedBook;
    }

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
        allBooksForUser = bookDao.getAllBooksFilterization(author, title, genre);
    }
      public void filterFroAdmin(String author, String title, String genre) {
        allBooks = bookDao.getAllBooksFilterizationForAdmin(author, title, genre);
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
        destroyWorld();
        init();
    }

    public String goToBook(int id) {
        return "bookDetailes.xhtml?faces-redirect=true&id=" + id;
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

    public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }

    public void handleMove(MoveEvent event) {
        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }

    public void destroyWorld() {
        addMessage(" success", "Delete a book from your wish list is done.");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getAuthors(int Id) {
        
        System.err.println(Id);
        ArrayList<String> authors = new ArrayList<>();
        authors = bookDao.getAllAuthorsForBook(Id);
        return String.join("  ,  ", authors);
    }

    public ArrayList<Book> getallBookForAuthor(String author) {
        ArrayList<Book> bookAuthors = new ArrayList<>();
        bookAuthors = bookDao.getAllBooksForAuthor(author);
        return bookAuthors;
    }

    public Book getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }

    public void editBook() {
        try {
            editedBook.setId(oldBook.getId());
            System.err.println("user edit func");
            bookDao.editBook(editedBook);

            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void fillEditBookData(String book) {
        System.err.println("from init => " + book);
//        oldBook = getBookById(id);
//        editedBook.setId(oldBook.getId());
//        editedBook.setName(oldBook.getName());
//        editedBook.setGenre(oldBook.getGenre());
//        editedBook.setTitle(oldBook.getTitle());
//        editedBook.setIsbn(oldBook.getIsbn());
//        editedBook.setNumberOfPages(oldBook.getNumberOfPages());
//        editedBook.setQauantity(oldBook.getQauantity());
        System.err.println(book);
    }

    public void saveData() {
        editedBook.getName();
        editedBook.getGenre();
        editedBook.getTitle();
        editedBook.getIsbn();
        editedBook.getNumberOfPages();
        editedBook.getQauantity();
    }

    public void removeFromUsers(int bookId) {
        try {
            bookDao.deleteBook(bookId);
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public int rentedNow(int bookId){
        return rentDao.rentedNow(bookId);
    }
    

}
