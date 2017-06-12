package project.rental.front;

import project.rental.dao.MoviesDao;
import project.rental.dao.MoviesDaoImpl;
import project.rental.dao.UsersDao;
import project.rental.dao.UsersDaoImpl;
import project.rental.entity.Users;

import java.sql.SQLException;

/**
 * Created by fmkam on 01.06.2017.
 */
public class MainTest {
    public static void main(String[] args) {

//        UsersDao usersDao = new UsersDaoImpl();
//        try {
//            System.out.println(usersDao.usersList());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


        MoviesDao moviesDao = new MoviesDaoImpl();
        UsersDao usersDao = new UsersDaoImpl();
        Users tmp = new Users("user3", "WWA");

        try {
            usersDao.add(tmp);
            System.out.println(usersDao.usersList());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
