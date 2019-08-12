package Beans;

import Daos.ListsDao;
import Models.Lists;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
    private ListsDao listsDao = new ListsDao();

    public ArrayList<String> getAuthors() {
        return authors;
    }

    @PostConstruct
    public void init() {
        try {
            authors = listsDao.fillAuthorList();
            genres = listsDao.fillGenreList();
            titles = listsDao.fillTitlesList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

}
