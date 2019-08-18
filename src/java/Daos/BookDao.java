package Daos;

import Models.Book;
import Models.Rent;
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
            con.close();
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void removeFromWishList(int userId, int bookId) {
        try {
            String sql = "DELETE FROM library.wishlist WHERE userid=? and bookId=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();

        } catch (Exception e) {

        }
    }

    public ArrayList<Book> getAllBooksFilterizationForRent(int userId, String author, String title, String genre) {
        try {
            System.out.println("author = " + author + " title = " + title);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = null;
            if (author == "" && title == "" && genre == "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " );";
            } else if (author != "" && title == "" && genre == "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.author='" + author + "';";

            } else if (author == "" && title != "" && genre == "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.title= '" + title + "';";

            } else if (author == "" && title == "" && genre != "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.genre='" + genre + "' ;";

            } else if (author != "" && title != "" && genre != "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.genre='" + genre + "' and b.title='" + title + "' and b.author='" + author + "';";

            } else if (author != "" && title != "" && genre == "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.author='" + author + "' and b.title='" + title + "';";

            } else if (author != "" && title == "" && genre != "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.genre='" + genre + "' and b.author='" + author + "';";

            } else if (author == "" && title != "" && genre != "") {
                sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId=" + userId + " ) and b.genre='" + genre + "' and b.title='" + title + "';";

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
            con.close();

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
            String sql = "SELECT * FROM library.books where quantity > 0";

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
            con.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Book> getAllBooksNotRented(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.rent as r  where r.userId='" + userId + "' ) and b.quantity >0 ; ";

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
            con.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Book> getAllBooksNotWishedAndRented(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT b.* FROM library.books as b where b.id not in (select bookId from library.wishlist as w  where w.userId=" + userId + "  ) and b.id not in ( select bookId from library.rent as r  where r.userId=" + userId + ") and b.quantity > 0 ";

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
            con.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkWishList(int userId, int bookId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT * FROM library.wishlist where userId =" + userId + " and bookId = " + bookId + ";";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                books.add(book);
            }
            resultSet.close();
            con.close();

            if (books.size() == 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addToWishList(int userId, int bookId) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            if (!checkWishList(userId, bookId)) {
                return false;
            } else {

                String sql = "INSERT INTO library.wishlist (userId,bookId) "
                        + "values (?,?)";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, bookId);
                preparedStatement.execute();
                con.close();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
            return false;
        }
    }

    public ArrayList<Book> getWishListBooks(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT b.* FROM library.wishlist as w ,library.books as b where b.id= w.bookId and w.userId=" + userId + "";

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
            con.close();

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
