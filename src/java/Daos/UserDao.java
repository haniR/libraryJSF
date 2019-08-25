package Daos;

import Models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UserDao {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<User> getUsers() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                + "user=root&password=root");

        statement = con.createStatement();
        String sql = "SELECT * FROM library.users";

        resultSet = statement.executeQuery(sql);
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setType(resultSet.getString("type"));
            user.setUsername(resultSet.getString("username"));
            user.setDob(resultSet.getDate("dob"));
            users.add(user);
            System.out.println(users);
        }
        resultSet.close();
        return users;
    }

    public void addUser(User user) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "INSERT INTO library.users (name,password,userName,dob,type) "
                    + "values (?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setDate(4, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(5, user.getType());
            System.out.println(user.getName() + " /***************************************/");
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
        }
    }

    public void editUser(User user) {
        try {
            String sql = "UPDATE library.users SET name =?, password =?, userName =?"
                    + ", dob =?, type =? "
                    + " WHERE id =?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setDate(4, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(5, user.getType());
            preparedStatement.setInt(6, user.getId());
            System.out.println(user.getId() + " /***************************************/");

            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            String sql = "DELETE FROM library.users WHERE id=?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User login(String username, String password) {

        try {
            User user = new User();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "SELECT * FROM library.users WHERE username= '" + username + "' and password= '" + password + "' ";

            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setType(resultSet.getString("type"));
                user.setUsername(resultSet.getString("username"));
                user.setDob(resultSet.getDate("dob"));
                System.out.println("success");

            } else {
                System.out.println("fail");

                return null;

            }
            resultSet.close();

            return user;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return null;

        }
    }
}
