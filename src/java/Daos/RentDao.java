package Daos;

import Models.Book;
import Models.Rent;
import java.sql.*;
import java.util.ArrayList;

public class RentDao {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<Book> getAllRentedUserBooks(int userId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT b.* FROM library.rent as r, library.books as b ,library.users as u where b.id=r.bookId and u.id=r.userId and r.userId=" + userId + ";";

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

    public boolean rentBook(Rent rent) {
        try {
            int count = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "INSERT INTO library.rent (userId,bookId,from,to) "
                    + "values (?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, rent.getUserId());
            preparedStatement.setInt(2, rent.getBookId());
            preparedStatement.setDate(3, new java.sql.Date(rent.getFrom().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(rent.getTo().getTime()));
            preparedStatement.execute();
            count = minusCounter(rent.getBookId());
            if (count != 0) {
                String sql2 = "UPDATE taskmanagement.users SET qunatity =? where id=?";
                preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1, --count);
                preparedStatement.setInt(1, rent.getBookId());
                preparedStatement.execute();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
            return false;
        }
    }

    public int minusCounter(int bookId) {
        try {
            int qunatity = 0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "SELECT * FROM library.books where id = " + bookId + "";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                qunatity = resultSet.getInt("quantity");
            }
            return qunatity;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
