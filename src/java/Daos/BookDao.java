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

    public Book getBookById(int bookId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = null;
            sql = "SELECT  *  FROM library.books where id =" + bookId + "";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            Book book = new Book();

            while (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
            }
            resultSet.close();
            con.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void uploadFile(String fileName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = " INSERT INTO `library`.`documents` (`src`) VALUES('"+fileName+"')";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
        }

    }

    public ArrayList<Book> getAllBooksFilterization(String author, String title, String genre) {
        try {
            System.out.println(" title = " + title);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = null;
            if (author == "" && title == "" && genre == "") {
                sql = "SELECT * FROM library.books where quantity >0 ";
            } else if (author != "" && title == "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.quantity >0;";

            } else if (author == "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and b.title='" + title + "' and b.quantity >0;";

            } else if (author == "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and b.genre='" + genre + "' and b.quantity >0;";

            } else if (author != "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.title='" + title + "' and b.genre='" + genre + "' and b.quantity >0;";

            } else if (author != "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.title='" + title + "' and b.quantity >0 ;";

            } else if (author != "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and  b.genre='" + genre + "' and b.quantity >0;";

            } else if (author == "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId   and b.title='" + title + "' and b.genre='" + genre + "' and b.quantity >0;";

            }
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
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

    public ArrayList<Book> getAllBooksFilterizationForAdmin(String author, String title, String genre) {
        try {
            System.out.println(" title = " + title);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = null;
            if (author == "" && title == "" && genre == "") {
                sql = "SELECT * FROM library.books where quantity >0 ";
            } else if (author != "" && title == "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.quantity >0;";

            } else if (author == "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and b.title='" + title + "' and b.quantity >0;";

            } else if (author == "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and b.genre='" + genre + "' and b.quantity >0;";

            } else if (author != "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.title='" + title + "' and b.genre='" + genre + "' and b.quantity >0;";

            } else if (author != "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and b.title='" + title + "' and b.quantity >0 ;";

            } else if (author != "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId  and a.name='" + author + "' and  b.genre='" + genre + "' and b.quantity >0;";

            } else if (author == "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.*  FROM library.books as b ,library.author as a,library.author_books as ab where b.id=ab.bookId and a.id=ab.authorId   and b.title='" + title + "' and b.genre='" + genre + "' and b.quantity >0;";

            }
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                book.setNumOfRent(resultSet.getInt("numOfRent"));
                book.setAvailable(resultSet.getInt("available"));

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
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0;";
            } else if (author != "" && title == "" && genre == "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and a.name ='" + author + "';";

            } else if (author == "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and b.title ='" + title + "';";

            } else if (author == "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and b.genre ='" + genre + "';";

            } else if (author != "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and a.name ='" + author + "' and b.title ='" + title + "' and b.genre ='" + genre + "';";

            } else if (author != "" && title != "" && genre == "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and a.name ='" + author + "' and b.title ='" + title + "' ;";

            } else if (author != "" && title == "" && genre != "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0 and a.name ='" + author + "'  and b.genre ='" + genre + "';";

            } else if (author == "" && title != "" && genre != "") {
                sql = "SELECT DISTINCT b.* FROM library.books as b ,library.author as a,library.author_books as ab where b.id not In (select bookId from library.rent as r  where r.userId=" + userId + "  ) and b.id=ab.bookId and a.id=ab.authorId  and b.quantity >0  and b.title ='" + title + "' and b.genre ='" + genre + "';";

            }
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
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

    public ArrayList<Book> getAllBooksForAuthor(String author) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "    SELECT b.* FROM library.books as b ,library.author as a , library.author_books as ab where ab.authorId=a.id and ab.bookId=b.id and  a.name='" + author + "'   ;\n"
                    + "";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setIsbn(resultSet.getInt("isbn"));
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
                book.setGenre(resultSet.getString("genre"));
                book.setTitle(resultSet.getString("title"));
                book.setQauantity(resultSet.getInt("quantity"));
                book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                book.setNumOfRent(resultSet.getInt("numOfRent"));
                book.setAvailable(resultSet.getInt("available"));
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

    public ArrayList<String> getAllAuthorsForBook(int bookId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");
            String sql = "SELECT a.name  FROM library.books as b ,library.author as a,library.author_books as ab where ab.bookId=b.id and a.id=ab.authorId and b.id=" + bookId + " and b.quantity >0 ; ";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ArrayList<String> authorsForBook = new ArrayList<>();

            while (resultSet.next()) {
                authorsForBook.add(resultSet.getString("name"));
            }
            resultSet.close();
            con.close();

            return authorsForBook;
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

    public void addBook(Book book) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "INSERT INTO library.books (name,isbn,numberOfPages,quantity,title,genre,available) "
                    + "values (?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getIsbn());
            preparedStatement.setInt(3, book.getNumberOfPages());
            preparedStatement.setInt(4, book.getQauantity());
            preparedStatement.setString(5, book.getTitle());
            preparedStatement.setString(6, book.getGenre());
            preparedStatement.setInt(7, book.getQauantity());

            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
        }

    }

    public void deleteBook(int bookId) {
        try {
            String sql = "DELETE FROM library.books WHERE id=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void editBook(Book book) {
        try {
            String sql = "UPDATE library.books SET name =?, isbn =?, numberOfPages =?"
                    + ", quantity =?, title =?,genre=? "
                    + " WHERE id =?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getIsbn());
            preparedStatement.setInt(3, book.getNumberOfPages());
            preparedStatement.setInt(4, book.getQauantity());
            preparedStatement.setString(5, book.getTitle());
            preparedStatement.setString(6, book.getGenre());
            preparedStatement.setInt(7, book.getId());
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
