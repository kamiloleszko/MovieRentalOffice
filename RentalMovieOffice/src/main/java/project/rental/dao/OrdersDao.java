package project.rental.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import project.rental.entity.Movies;
import project.rental.entity.Orders;
import project.rental.entity.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fmkam on 27.05.2017.
 */
public interface OrdersDao {
    public void newOrder (Orders orders) throws SQLException;
    public void returnMovie (String movieID) throws SQLException;
    public void returnAllUserMovies (String userID) throws SQLException;
    public void deleteOrder (int id) throws SQLException;
    List<Orders> showAllOrders() throws SQLException;
    List<Orders> showActiveOrders() throws SQLException;
    List<Orders> showActiveUserallOrders(String id) throws SQLException;
    List<Orders> showActiveUsersActiveOrders(String id) throws SQLException;

}
