package Beans;

import Daos.ListsDao;
import Models.Book;
import Models.Lists;
import Models.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ListBean {

    private String author;
    private ArrayList<String> authors = new ArrayList<>();
    private String title;
    private ArrayList<String> titles = new ArrayList<>();
    private String genre;
    private ArrayList<String> genres = new ArrayList<>();

    private String authorForRent;
    private List<String> authorsForRent = new ArrayList<>();
    private String titleForRent;
    private List<String> titlesForRent = new ArrayList<>();
    private String genreForRent;
    private List<String> genresForRent = new ArrayList<>();
    private int userForBlockList;
    private List<User> usersForBlockList = new ArrayList<>();
    private List<Integer> usersIdBlocked = new ArrayList<>();

    private ListsDao listsDao = new ListsDao();
    @ManagedProperty("#{loginBean}")
    LoginBean loginBean;

    public ArrayList<String> getAuthors() {
        return authors;
    }

    @PostConstruct
    public void init() {
        try {
            authors = listsDao.fillAuthorList();
            genres = listsDao.fillGenreList();
            titles = listsDao.fillTitlesList();
            authorsForRent = listsDao.fillAuthorsForList(loginBean.loggedIn_user.getId());
            titlesForRent = listsDao.fillTitlesForRentList(loginBean.loggedIn_user.getId());
            genresForRent = listsDao.fillGenresForList(loginBean.loggedIn_user.getId());
            usersForBlockList = listsDao.fillUsersForBlockList();
            usersIdBlocked = listsDao.fillUsersForBlockNamesList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Integer> getUsersIdBlocked() {
        return usersIdBlocked;
    }

    public void setUsersIdBlocked(List<Integer> usersIdBlocked) {
        this.usersIdBlocked = usersIdBlocked;
    }

    

    public int getUserForBlockList() {
        return userForBlockList;
    }

    public void setUserForBlockList(int userForBlockList) {
        this.userForBlockList = userForBlockList;
    }

    public List<User> getUsersForBlockList() {
        return usersForBlockList;
    }

    public void setUsersForBlockList(List<User> usersForBlockList) {
        this.usersForBlockList = usersForBlockList;
    }

    public String getAuthorForRent() {
        return authorForRent;
    }

    public void setAuthorForRent(String authorForRent) {
        this.authorForRent = authorForRent;
    }

    public List<String> getAuthorsForRent() {
        return authorsForRent;
    }

    public void setAuthorsForRent(List<String> authorsForRent) {
        this.authorsForRent = authorsForRent;
    }

    public String getTitleForRent() {
        return titleForRent;
    }

    public void setTitleForRent(String titleForRent) {
        this.titleForRent = titleForRent;
    }

    public List<String> getTitlesForRent() {
        return titlesForRent;
    }

    public void setTitlesForRent(List<String> titlesForRent) {
        this.titlesForRent = titlesForRent;
    }

    public String getGenreForRent() {
        return genreForRent;
    }

    public void setGenreForRent(String genreForRent) {
        this.genreForRent = genreForRent;
    }

    public List<String> getGenresForRent() {
        return genresForRent;
    }

    public void setGenresForRent(List<String> genresForRent) {
        this.genresForRent = genresForRent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ListsDao getListsDao() {
        return listsDao;
    }

    public void setListsDao(ListsDao listsDao) {
        this.listsDao = listsDao;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}
