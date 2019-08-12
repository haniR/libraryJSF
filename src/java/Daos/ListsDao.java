package Daos;

import Models.Lists;
import Models.User;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;

public class ListsDao {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<String> fillAuthorList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT author FROM library.books";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> authors = new ArrayList<>();

            while (resultSet.next()) {
                authors.add(resultSet.getString("author"));
            }
            resultSet.close();
            return authors;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> fillGenreList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT genre FROM library.books";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> genres = new ArrayList<>();

            while (resultSet.next()) {
                genres.add(resultSet.getString("genre"));
            }

            resultSet.close();
            return genres;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String>  fillTitlesList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT title FROM library.books";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> titles = new ArrayList<>();

            while (resultSet.next()) {
                titles.add(resultSet.getString("title"));
            }
            resultSet.close();
            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

}
