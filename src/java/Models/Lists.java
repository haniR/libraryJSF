package Models;

import Daos.ListsDao;
import java.util.ArrayList;
import java.util.List;

public class Lists {

    private List<String> authors = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<String> authorsForRent = new ArrayList<>();
    private List<String> titlesForRent = new ArrayList<>();
    private List<String> genresForRent = new ArrayList<>();
    private List<User> usersForBlocking = new ArrayList<>();

    public Lists() {
    }

    public List<User> getUsersForBlocking() {
        return usersForBlocking;
    }

    public void setUsersForBlocking(List<User> usersForBlocking) {
        this.usersForBlocking = usersForBlocking;
    }

    public List<String> getAuthorsForRent() {
        return authorsForRent;
    }

    public void setAuthorsForRent(List<String> authorsForRent) {
        this.authorsForRent = authorsForRent;
    }

    public List<String> getTitlesForRent() {
        return titlesForRent;
    }

    public void setTitlesForRent(List<String> titlesForRent) {
        this.titlesForRent = titlesForRent;
    }

    public List<String> getGenresForRent() {
        return genresForRent;
    }

    public void setGenresForRent(List<String> genresForRent) {
        this.genresForRent = genresForRent;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}
