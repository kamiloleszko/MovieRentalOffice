package project.rental.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by fmkam on 22.04.2017.
 */
public class MySQLConnector {

    private Connection connection = null;
    private Statement statement = null;
    private static MySQLConnector mySQLConnector  = null;


    private MySQLConnector(){
        connect();
    }

    private void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_rental?" +
                    "user=root&password=fifa2010");
            statement = connection.createStatement();
            System.out.println("Application has been connected to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void disconnect(){

        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.out.println("Application has been disconnected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }


    public static MySQLConnector getInstance(){
        if (mySQLConnector == null){
            mySQLConnector = new MySQLConnector();
        }
        return mySQLConnector;
    }


}

