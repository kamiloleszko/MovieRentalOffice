package project.rental.dao;

import project.rental.entity.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fmkam on 23.05.2017.
 */
public interface UsersDao {

    void add (Users users) throws SQLException ;
    void delete (int userId) throws SQLException;
    Users selectActiveUser(String id) throws SQLException;
    List<Users> usersList () throws SQLException;
    List<Users> findUsers (String user) throws SQLException;
}
