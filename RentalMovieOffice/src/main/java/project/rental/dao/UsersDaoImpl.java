package project.rental.dao;

import project.rental.connector.MySQLConnector;
import project.rental.entity.Users;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fmkam on 23.05.2017.
 */
public class UsersDaoImpl implements UsersDao {

    private MySQLConnector mySQLConnector = MySQLConnector.getInstance();

    @Override
    public void add(Users users) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into users (user_name, address) values (?,?)");
        preparedStatement.setString(1,users.getName());
        preparedStatement.setString(2,users.getAddress());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int usersId) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where user_id = ?");
        preparedStatement.setInt(1,usersId);
        preparedStatement.executeUpdate();
    }

    @Override
    public Users selectActiveUser (String id) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_id =" + id);
        ResultSet resultSet = null;
        resultSet = preparedStatement.executeQuery();
        if (resultSet == null){
            return null;
        }
        Users temp = null;
        while (resultSet.next()) {
            temp = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("address"));
        }
        return temp;
    }

    @Override
    public List<Users> usersList() throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        resultSet = preparedStatement.executeQuery();

        List<Users> usersList = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Users temp = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("address"));
            usersList.add(temp);
        }
        return usersList;
    }

    @Override
    public List<Users> findUsers(String user) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where user_name like ?");
        preparedStatement.setString(1, "%"+user+"%");
        resultSet = preparedStatement.executeQuery();

        List<Users> userList = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Users temp = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("address"));
            userList.add(temp);
        }
        return userList;
    }
}
