package Daos;

import Models.BlackList;
import Models.User;
import java.sql.*;
import java.util.ArrayList;

public class BlacklistDao {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public ArrayList<BlackList> getBlockedUsers() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                + "user=root&password=root");

        statement = con.createStatement();
        String sql = "SELECT b.*,u.* FROM library.blacklist as b , library.users as u where b.userId=u.id;";

        resultSet = statement.executeQuery(sql);
        ArrayList<BlackList> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            BlackList blackList = new BlackList();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setType(resultSet.getString("type"));
            user.setUsername(resultSet.getString("username"));
            user.setDob(resultSet.getDate("dob"));

            blackList.setUserId(resultSet.getInt("userId"));
            blackList.setFrom(resultSet.getDate("from"));
            blackList.setTo(resultSet.getDate("to"));
            blackList.setResaon(resultSet.getString("reason"));
            blackList.setUser(user);
            System.out.println("//////////////////********************************///////////////////");
            users.add(blackList);
            System.out.println(users);
        }
        resultSet.close();
        return users;
    }

    public void addBlockUser(BlackList user) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "INSERT INTO `library`.`blacklist` (`userId`, `from`, `to`, `reason`) VALUES (?,?,?,?);";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(user.getTo().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(user.getFrom().getTime()));
            preparedStatement.setString(4, user.getResaon());
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
        }
    }

    public void deleteBlockUser(int id) {
        try {
            String sql = "DELETE FROM library.blacklist WHERE userId=?";
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

    public BlackList getBlockedUser(int userId) {
        try {
            BlackList bl = new BlackList();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library?"
                    + "user=root&password=root");

            String sql = "SELECT * From library.blackList where userId = " + userId + ";";
            statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                bl.setFrom(resultSet.getDate("from"));
                bl.setTo(resultSet.getDate("to"));
                bl.setResaon(resultSet.getString("reason"));
            }
            resultSet.close();
            con.close();
            return bl;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error Happened");
            return null;
        }

    }

}
