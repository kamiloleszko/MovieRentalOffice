package project.rental.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import project.rental.connector.MySQLConnector;
import project.rental.entity.Movies;
import project.rental.entity.Orders;
import project.rental.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fmkam on 27.05.2017.
 */
public class OrdersDaoImpl implements OrdersDao {

    MySQLConnector mySQLConnector = MySQLConnector.getInstance();

    @Override
    public void newOrder(Orders orders) throws SQLException {
        Movies movies = new Movies();
        Users users = new Users();
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into orders (ouser_id, omovie_id, date_from, returned) values (?,?,?,?)");
        preparedStatement.setInt(1, orders.getUser_id());
        preparedStatement.setInt(2, orders.getMovie_id());
        preparedStatement.setString(3,orders.getDate_from());
        preparedStatement.setBoolean(4,false);
        preparedStatement.executeUpdate();

        Connection connection1 = mySQLConnector.getConnection();

        PreparedStatement preparedStatement1 = connection.prepareStatement("update movies set available = 0 where movie_id = ?");
        //preparedStatement1.setBoolean(1,false);
        preparedStatement1.setInt(1,orders.getMovie_id());
        preparedStatement1.executeUpdate();
    }

    @Override
    public void returnMovie(String movieID) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update orders set returned = 1 where omovie_id = ? ");
        preparedStatement.setString(1,movieID);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = connection.prepareStatement("update movies set available = 1 where movie_id = ?");
        preparedStatement1.setString(1,movieID);
        preparedStatement1.executeUpdate();

    }

    @Override
    public void returnAllUserMovies(String userID) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update orders set returned = 1 where ouser_id = ?");
        preparedStatement.setString(1,userID);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = connection.prepareStatement("update movies set available = 1 where movie_id = ANY (select omovie_id from orders where ouser_id = ?)");
        preparedStatement1.setString(1,userID);
        preparedStatement1.executeUpdate();
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update movies set available = ? where id = (select omovie_id from orders where order_id = ?)");
        preparedStatement.setBoolean(1,true);
        preparedStatement.setInt(2,id);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = connection.prepareStatement("delete from orders where order_id = ?");
        preparedStatement1.setInt(1,id);
        preparedStatement1.executeUpdate();


    }

    @Override
    public List<Orders> showAllOrders() throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select orders.order_id, users.user_id, user_name, movies.movie_id, title, date_from, returned from orders join " +
                "users, movies where orders.ouser_id = users.user_id and orders.omovie_id = movies.movie_id;");
        resultSet = preparedStatement.executeQuery();

        List<Orders> allOrdersList = new LinkedList<>();
        if  (resultSet ==null){
            return null;
        }
        while (resultSet.next()){
            Orders temp = new Orders(resultSet.getInt("order_id"),resultSet.getInt("user_id"),resultSet.getString("user_name"), resultSet.getInt("movie_id"),
                    resultSet.getString("title"),resultSet.getString("date_from"), resultSet.getBoolean("returned"));
            allOrdersList.add(temp);
        }
        return allOrdersList;
    }

    @Override
    public List<Orders> showActiveOrders() throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select orders.order_id, users.user_id, user_name, movies.movie_id, title, date_from, returned from orders join " +
                "users, movies where orders.ouser_id = users.user_id and orders.omovie_id = movies.movie_id and returned = 0;");
        resultSet = preparedStatement.executeQuery();

        List<Orders> activeOrderList = new LinkedList<>();
        if  (resultSet ==null){
            return null;
        }
        while (resultSet.next()){
            Orders temp = new Orders(resultSet.getInt("order_id"),resultSet.getInt("user_id"),resultSet.getString("user_name"), resultSet.getInt("movie_id"),
                    resultSet.getString("title"),resultSet.getString("date_from"), resultSet.getBoolean("returned"));
            activeOrderList.add(temp);
        }
        return activeOrderList;
    }

    @Override
    public List<Orders> showActiveUserallOrders(String id) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select orders.order_id, users.user_id, user_name, movies.movie_id, title, date_from, returned from orders join " +
                "users, movies where orders.ouser_id = users.user_id and orders.omovie_id = movies.movie_id and ouser_id = ?;");
        preparedStatement.setString(1,id);
        resultSet = preparedStatement.executeQuery();

        List<Orders> activeUserallOrders = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Orders temp = new Orders(resultSet.getInt("order_id"),resultSet.getInt("user_id"),resultSet.getString("user_name"), resultSet.getInt("movie_id"),
                    resultSet.getString("title"),resultSet.getString("date_from"), resultSet.getBoolean("returned"));
            activeUserallOrders.add(temp);
        }
        return activeUserallOrders;
    }

    @Override
    public List<Orders> showActiveUsersActiveOrders(String id) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select orders.order_id, users.user_id, user_name, movies.movie_id, title, date_from, returned from orders join " +
                "users, movies where orders.ouser_id = users.user_id and orders.omovie_id = movies.movie_id and returned = 0 and ouser_id = ?;");
        preparedStatement.setString(1,id);
        resultSet = preparedStatement.executeQuery();

        List<Orders> activeUserActiveOrders = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Orders temp = new Orders(resultSet.getInt("order_id"),resultSet.getInt("user_id"),resultSet.getString("user_name"), resultSet.getInt("movie_id"),
                    resultSet.getString("title"),resultSet.getString("date_from"), resultSet.getBoolean("returned"));
            activeUserActiveOrders.add(temp);
        }
        return activeUserActiveOrders;
    }
}
