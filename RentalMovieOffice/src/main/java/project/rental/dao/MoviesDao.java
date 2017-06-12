package project.rental.dao;

import project.rental.entity.Movies;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by fmkam on 27.05.2017.
 */
public interface MoviesDao {
    void add (Movies movie) throws SQLException;
    void delete (int id) throws SQLException;
    List <Movies> showMovies () throws SQLException;
    List <Movies> showAvailableMovies () throws SQLException;
    List <Movies> findByTitle (String title) throws SQLException;
    List <Movies> findByDirector (String director) throws SQLException;
}
