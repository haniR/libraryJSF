package Daos;

import Models.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<Book> getAllBooksFilterization(String author, String title, String genre) {
        try {
            System.out.println("author = " + author + " title = " + title);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = null;
            if (author == "" && title == "" && genre == "") {
                sql = "SELECT * FROM library.books";
            } else if (author != "" && title == "" && genre == "") {
                sql = "SELECT * FROM library.books where author='" + author + "'";

            } else if (author == "" && title != "" && genre == "") {
                sql = "SELECT * FROM library.books where title='" + title + "'";

            } else if (author == "" && title == "" && genre != "") {
                sql = "SELECT * FROM library.books where genre='" + genre + "'";

            } else if (author != "" && title != "" && genre != "") {
                sql = "SELECT * FROM library.books where genre='" + genre + "' and title = '" + title + "'and author = '" + author + "'";

            } else if (author != "" && title != "" && genre == "") {
                sql = "SELECT * FROM library.books where title = '" + title + "'and author = '" + author + "'";

            } else if (author != "" && title == "" && genre != "") {
                sql = "SELECT * FROM library.books where genre='" + genre + "' and author = '" + author + "'";

            } else if (author == "" && title != "" && genre != "") {
                sql = "SELECT * FROM library.books where genre='" + genre + "' and title = '" + title + "'";

            }
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                books.add(book);
            }
            resultSet.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<Book> getAllBooks() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT * FROM library.books";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                books.add(book);
            }
            resultSet.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addToWishList(int userId, int bookId) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "INSERT INTO library.wishlist (userId,bookId) "
                    + "values (?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
        }
    }
    public ArrayList<Book> getWishListBooks(int  userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT b.* FROM library.wishlist as w ,library.books as b where b.id= w.bookId and w.userId="+userId+"";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                books.add(book);
            }
            resultSet.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
