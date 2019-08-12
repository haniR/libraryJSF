package Models;

import Daos.ListsDao;
import java.util.ArrayList;
import java.util.List;

public class Lists {
    private List<String> authors = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> genres = new ArrayList<>();

    public Lists() {
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
