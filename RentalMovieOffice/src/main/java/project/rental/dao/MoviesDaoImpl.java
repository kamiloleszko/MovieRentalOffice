package project.rental.dao;

import com.sun.org.apache.regexp.internal.RESyntaxException;
import project.rental.connector.MySQLConnector;
import project.rental.entity.Movies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fmkam on 27.05.2017.
 */
public class MoviesDaoImpl implements MoviesDao {

    private MySQLConnector mySQLConnector = MySQLConnector.getInstance();

    @Override
    public void add(Movies movie) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into movies (title, director, available) values (?, ?, ?)");
        preparedStatement.setString(1,movie.getTitle());
        preparedStatement.setString(2,movie.getDirector());
        preparedStatement.setBoolean(3,movie.isAvailable());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from movies where movie_id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Movies> showMovies() throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from movies");

        resultSet = preparedStatement.executeQuery();
        List<Movies> moviesList = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Movies temp = new Movies(resultSet.getInt("movie_id"),resultSet.getString("title"), resultSet.getString("director"),resultSet.getBoolean("available"));
            moviesList.add(temp);
        }
        return moviesList;
    }

    @Override
    public List<Movies> showAvailableMovies() throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from movies where available = ?");
        preparedStatement.setBoolean(1,true);
        resultSet = preparedStatement.executeQuery();

        List<Movies> availableMovies = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Movies temp = new Movies(resultSet.getInt("movie_id"),resultSet.getString("title"), resultSet.getString("director"),resultSet.getBoolean("available"));
            availableMovies.add(temp);
        }
        return availableMovies;
    }

    @Override
    public List<Movies> findByTitle(String title) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from movies where title like ?");
        preparedStatement.setString(1,"%"+title+"%");
        resultSet = preparedStatement.executeQuery();

        List<Movies> moviesList = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Movies temp = new Movies(resultSet.getInt("movie_id"),resultSet.getString("title"), resultSet.getString("director"),resultSet.getBoolean("available"));
            moviesList.add(temp);
        }
        return moviesList;
    }

    @Override
    public List<Movies> findByDirector(String director) throws SQLException {
        Connection connection = mySQLConnector.getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from movies where director like ?");
        preparedStatement.setString(1,"%"+director+"%");
        resultSet = preparedStatement.executeQuery();

        List<Movies> moviesList = new LinkedList<>();
        if (resultSet == null){
            return null;
        }
        while (resultSet.next()){
            Movies temp = new Movies(resultSet.getInt("movie_id"),resultSet.getString("title"), resultSet.getString("director"),resultSet.getBoolean("available"));
            moviesList.add(temp);
        }
        return moviesList;
    }
}
