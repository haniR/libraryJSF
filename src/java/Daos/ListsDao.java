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
            String sql = "SELECT name FROM library.author";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> authors = new ArrayList<>();

            while (resultSet.next()) {
                authors.add(resultSet.getString("name"));
            }
            resultSet.close();
            con.close();

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
            con.close();

            return genres;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String> fillTitlesList() {
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
            con.close();

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<User> fillUsersForBlockList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT u.* FROM library.users as u where u.id not in( select userId  from library.blacklist as b  )";

            resultSet = statement.executeQuery(sql);
            ArrayList<User> usersForBlockList = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));

                usersForBlockList.add(user);
            }
            resultSet.close();
            con.close();

            return usersForBlockList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<Integer> fillUsersForBlockNamesList() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT u.id FROM library.users as u where u.id not in( select userId  from library.blacklist as b  )";

            resultSet = statement.executeQuery(sql);
            ArrayList<Integer> usersForBlockList = new ArrayList<>();

            while (resultSet.next()) {
                usersForBlockList.add(resultSet.getInt("id"));
            }
            resultSet.close();
            con.close();

            return usersForBlockList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String> fillTitlesForRentList(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT b.title FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " );";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> titles = new ArrayList<>();

            while (resultSet.next()) {
                titles.add(resultSet.getString("title"));
            }
            resultSet.close();
            con.close();

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String> fillGenresForList(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT b.genre FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " );";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> titles = new ArrayList<>();

            while (resultSet.next()) {
                titles.add(resultSet.getString("genre"));
            }
            resultSet.close();
            con.close();

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String> fillAuthorsForList(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            statement = con.createStatement();
            String sql = "SELECT DISTINCT a.name\n"
                    + "FROM library.books as b ,library.author as a,library.author_books as ab where \n"
                    + "b.id not In (select bookId from library.rent as r  where r.userId="+userId+"  ) \n"
                    + "and b.id=ab.bookId \n"
                    + "and a.id=ab.authorId  \n"
                    + "and b.quantity >0\n"
                    + ";";

            resultSet = statement.executeQuery(sql);
            ArrayList<String> titles = new ArrayList<>();

            while (resultSet.next()) {
                titles.add(resultSet.getString("name"));
            }
            resultSet.close();
            con.close();

            return titles;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

}
