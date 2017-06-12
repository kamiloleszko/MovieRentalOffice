package project.rental.front;

import com.sun.org.apache.bcel.internal.generic.LADD;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import project.rental.dao.*;
import project.rental.entity.Movies;
import project.rental.entity.Orders;
import project.rental.entity.Users;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;
import java.awt.*;
import java.awt.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by fmkam on 06.05.2017.
 */
public class Controller implements Initializable {

    UsersDaoImpl usersDao = new UsersDaoImpl();
    MoviesDao moviesDao = new MoviesDaoImpl();
    OrdersDao ordersDao = new OrdersDaoImpl();

    //Labels
    @FXML
    private Label activeUserId;
    @FXML
    private Label activeUserName;
    @FXML
    private Label activeUserAddress;

    //TextFields
    @FXML
    private javafx.scene.control.TextField newUserName;
    @FXML
    private javafx.scene.control.TextField newUserAddress;
    @FXML
    private javafx.scene.control.TextField movieTitleTxt;
    @FXML
    private javafx.scene.control.TextField directorTxt;

    //Tables
    @FXML
     TableView <Users> usersTable;
    @FXML
     TableColumn <Users, Integer> userid;
    @FXML
    TableColumn <Users, String> name;
    @FXML
    TableColumn <Users, String> address;

    @FXML
    TableView<Movies> moviesTable;
    @FXML
    TableColumn<Movies, Integer> movieID;
    @FXML
    TableColumn<Movies, String> movieTitle;
    @FXML
    TableColumn<Movies, String> movieDirector;
    @FXML
    TableColumn<Movies, Boolean> isMovieAvailable;

    @FXML
    TableView<Orders> ordersTable;
    @FXML
    TableColumn<Orders, Integer> orderID;
    @FXML
    TableColumn<Orders, Integer> orderUserID;
    @FXML
    TableColumn<Orders, String> orderUserName;
    @FXML
    TableColumn<Orders, Integer> orderMovieID;
    @FXML
    TableColumn<Orders, String> orderMovieTitle;
    @FXML
    TableColumn<Orders, String> orderDateFrom;
    @FXML
    TableColumn<Orders, Boolean> orderReturned;




    public void txtFieldsDisable(){
        newUserName.setText("");
        newUserName.setDisable(true);
        newUserAddress.setText("");
        newUserAddress.setDisable(true);
        movieTitleTxt.setText("");
        movieTitleTxt.setDisable(true);
        directorTxt.setText("");
        directorTxt.setDisable(true);
    }

    //Users
    public void selectUserButton() {
        activeUserId.setText(String.valueOf(usersTable.getSelectionModel().getSelectedItem().getId()));
        activeUserName.setText(usersTable.getSelectionModel().getSelectedItem().getName());
        activeUserAddress.setText(usersTable.getSelectionModel().getSelectedItem().getAddress());
    }

    public void findUserButton(){
        try {
            String us = JOptionPane.showInputDialog(null,"Please provide user name you want to find", "Search for user", JOptionPane.QUESTION_MESSAGE);
            ObservableList<Users> userList = FXCollections.observableArrayList(usersDao.findUsers(us));
            userid.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
            address.setCellValueFactory(new PropertyValueFactory<Users, String>("address"));
            usersTable.itemsProperty().setValue(userList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllUsersButton(){
        try {
            ObservableList<Users> userList = FXCollections.observableArrayList(usersDao.usersList());
            userid.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
            address.setCellValueFactory(new PropertyValueFactory<Users, String>("address"));
            usersTable.itemsProperty().setValue(userList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserButton(){
        try {
            if (newUserName.getText().equals("") || newUserAddress.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide new user data","No user data", JOptionPane.WARNING_MESSAGE);
            } else{
                int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to add new user?", "New User Confirmation", JOptionPane.YES_NO_OPTION);
                if (question == 0) {
                    usersDao.add(new Users(newUserName.getText(), newUserAddress.getText()));
                    showAllUsersButton();
                }
            }
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUserButton(){
        try {
            if (usersTable.getSelectionModel().getSelectedItem().getName() == null){

        }
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please chose user to delete","No user data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected user from database?", "Delete user Confirmation", JOptionPane.YES_NO_OPTION);
        if (question == 0) {
            try {
                usersDao.delete(usersTable.getSelectionModel().getSelectedItem().getId());
                showAllUsersButton();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void enableUserAdd(){
        newUserName.setDisable(false);
        newUserAddress.setDisable(false);
    }

    //Movies

    public void enableMovieAdd(){
        movieTitleTxt.setDisable(false);
        directorTxt.setDisable(false);
    }

    public void addMovieButton(){
        try {
            if (movieTitleTxt.getText().equals("") || directorTxt.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide new movie data","No user data", JOptionPane.WARNING_MESSAGE);
            } else {
                int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to add new movie?", "New User Confirmation", JOptionPane.YES_NO_OPTION);
                if (question ==0) {
                    moviesDao.add(new Movies(movieTitleTxt.getText(), directorTxt.getText(), true));
                    showAllMoviesButton();
                }
                txtFieldsDisable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteMovieButton(){
        try{
            if (moviesTable.getSelectionModel().getSelectedItem().getTitle() == null){
            }
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please chose movie to delete","No movie data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int question = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete selected movie from database?","Delete movie Confirmation", JOptionPane.YES_NO_OPTION);
        if (question ==0){
            try {
                moviesDao.delete(moviesTable.getSelectionModel().getSelectedItem().getId());
                showAllMoviesButton();
                txtFieldsDisable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAllMoviesButton(){
        try {
            ObservableList<Movies> moviesList = FXCollections.observableArrayList(moviesDao.showMovies());
            movieID.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("id"));
            movieTitle.setCellValueFactory(new PropertyValueFactory<Movies, String>("title"));
            movieDirector.setCellValueFactory(new PropertyValueFactory<Movies, String>("director"));
            isMovieAvailable.setCellValueFactory(new PropertyValueFactory<Movies, Boolean>("available"));
            moviesTable.itemsProperty().setValue(moviesList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAvailMoviesButton(){
        try {
            ObservableList<Movies> moviesList = FXCollections.observableArrayList(moviesDao.showAvailableMovies());
            movieID.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("id"));
            movieTitle.setCellValueFactory(new PropertyValueFactory<Movies, String>("title"));
            movieDirector.setCellValueFactory(new PropertyValueFactory<Movies, String>("director"));
            isMovieAvailable.setCellValueFactory(new PropertyValueFactory<Movies, Boolean>("available"));
            moviesTable.itemsProperty().setValue(moviesList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByTitleButton(){
        String ask = JOptionPane.showInputDialog(null, "Plesae provide title you want to find","Search movie by title", JOptionPane.QUESTION_MESSAGE);
        try {
            ObservableList<Movies> searchByTitle = FXCollections.observableArrayList(moviesDao.findByTitle(ask));
            if (moviesDao.findByTitle(ask) == null){
                JOptionPane.showMessageDialog(null, "There is no required movie");
                return;
            }else {
                movieID.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("id"));
                movieTitle.setCellValueFactory(new PropertyValueFactory<Movies, String>("title"));
                movieDirector.setCellValueFactory(new PropertyValueFactory<Movies, String>("director"));
                isMovieAvailable.setCellValueFactory(new PropertyValueFactory<Movies, Boolean>("available"));
                moviesTable.itemsProperty().setValue(searchByTitle);
            }
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchByDirectorButton(){
        String ask = JOptionPane.showInputDialog(null, "Plesae provide director you want to find","Search movie by title", JOptionPane.QUESTION_MESSAGE);
        try {
            ObservableList<Movies> searchbyDirector = FXCollections.observableArrayList(moviesDao.findByDirector(ask));
            if (moviesDao.findByTitle(ask) == null){
                JOptionPane.showMessageDialog(null, "There is no required movie");
                return;
            }else {
                movieID.setCellValueFactory(new PropertyValueFactory<Movies, Integer>("id"));
                movieTitle.setCellValueFactory(new PropertyValueFactory<Movies, String>("title"));
                movieDirector.setCellValueFactory(new PropertyValueFactory<Movies, String>("director"));
                isMovieAvailable.setCellValueFactory(new PropertyValueFactory<Movies, Boolean>("available"));
                moviesTable.itemsProperty().setValue(searchbyDirector);
            }
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Orders

    public void showAllOrdersButton(){
        try {
            ObservableList<Orders> allOrdersList = FXCollections.observableArrayList(ordersDao.showAllOrders());
            orderID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
            orderUserID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("user_id"));
            orderUserName.setCellValueFactory(new PropertyValueFactory<Orders, String >("user_name"));
            orderMovieID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("movie_id"));
            orderMovieTitle.setCellValueFactory(new PropertyValueFactory<Orders, String>("movie_title"));
            orderDateFrom.setCellValueFactory(new PropertyValueFactory<Orders, String>("date_from"));
            orderReturned.setCellValueFactory(new PropertyValueFactory<Orders, Boolean>("returned"));
            ordersTable.setItems(allOrdersList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showActiveOrdersButton(){
        try {
            ObservableList<Orders> avtiveOrdersList = FXCollections.observableArrayList(ordersDao.showActiveOrders());
            orderID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
            orderUserID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("user_id"));
            orderUserName.setCellValueFactory(new PropertyValueFactory<Orders, String >("user_name"));
            orderMovieID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("movie_id"));
            orderMovieTitle.setCellValueFactory(new PropertyValueFactory<Orders, String>("movie_title"));
            orderDateFrom.setCellValueFactory(new PropertyValueFactory<Orders, String>("date_from"));
            orderReturned.setCellValueFactory(new PropertyValueFactory<Orders, Boolean>("returned"));
            ordersTable.setItems(avtiveOrdersList);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void orderMovieButton(){
        if (activeUserId.getText().equals("")){
            JOptionPane.showMessageDialog(null,"PLease choose active user first");
            return;
        }

        try{
            if (moviesTable.getSelectionModel().getSelectedItem().getTitle() == null){
            }
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please chose movie you want to order first","No movie data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (moviesTable.getSelectionModel().getSelectedItem().isAvailable() == false){
            JOptionPane.showMessageDialog(null,"Selected movie is already unavailable", "Unavailable Movie", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int question = JOptionPane.showConfirmDialog(null,""+activeUserName.getText() + ", are you sure you want to order " +
                    "\"" + moviesTable.getSelectionModel().getSelectedItem().getTitle() + "\"" + " ?", "Order confirmation", JOptionPane.YES_NO_OPTION);
            if (question ==0) {
                String date = JOptionPane.showInputDialog(null, "Please provide order date in format: DD-MM-YYYY", "Order date", JOptionPane.QUESTION_MESSAGE);
                Orders temp = new Orders(Integer.valueOf(activeUserId.getText()), activeUserName.getText(), moviesTable.getSelectionModel().getSelectedItem().getId(),
                        moviesTable.getSelectionModel().getSelectedItem().getTitle(), date);

                if (date.length()!=10){
                    JOptionPane.showMessageDialog(null,"Please provide correct date ", "Invalid date", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < date.length(); i++){

                    if (i == 2 || i == 5){
                        if (date.charAt(i) != '-') {
                            JOptionPane.showMessageDialog(null, "Please provide correct date ", "Invalid date", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        } else {
                            if (date.charAt(i) == '1' || date.charAt(i) == '2' || date.charAt(i) == '3' || date.charAt(i) == '4' || date.charAt(i) == '5' || date.charAt(i) == '6'
                                    || date.charAt(i) == '7' || date.charAt(i) == '8' || date.charAt(i) == '9' || date.charAt(i) == '0') {

                            } else {
                                JOptionPane.showMessageDialog(null, "Please provide correct date ", "Invalid date", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }



                ordersDao.newOrder(temp);
                showActiveOrdersButton();
                showAllMoviesButton();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showActiveUserAllOrdersButton(){
        try {
            if (activeUserId.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please select active user first","No user selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
                ObservableList<Orders> activeUserAllOrders = FXCollections.observableArrayList(ordersDao.showActiveUserallOrders(activeUserId.getText()));
                orderID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
                orderUserID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("user_id"));
                orderUserName.setCellValueFactory(new PropertyValueFactory<Orders, String>("user_name"));
                orderMovieID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("movie_id"));
                orderMovieTitle.setCellValueFactory(new PropertyValueFactory<Orders, String>("movie_title"));
                orderDateFrom.setCellValueFactory(new PropertyValueFactory<Orders, String>("date_from"));
                orderReturned.setCellValueFactory(new PropertyValueFactory<Orders, Boolean>("returned"));
                ordersTable.setItems(activeUserAllOrders);
                txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void activeUserActiveOrdersButton(){
        try {
            if (activeUserId.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please select active user first","No user selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ObservableList<Orders> activeUserAllOrders = FXCollections.observableArrayList(ordersDao.showActiveUsersActiveOrders(activeUserId.getText()));
            orderID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("id"));
            orderUserID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("user_id"));
            orderUserName.setCellValueFactory(new PropertyValueFactory<Orders, String>("user_name"));
            orderMovieID.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("movie_id"));
            orderMovieTitle.setCellValueFactory(new PropertyValueFactory<Orders, String>("movie_title"));
            orderDateFrom.setCellValueFactory(new PropertyValueFactory<Orders, String>("date_from"));
            orderReturned.setCellValueFactory(new PropertyValueFactory<Orders, Boolean>("returned"));
            ordersTable.setItems(activeUserAllOrders);
            txtFieldsDisable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnMovieButton(){
        try {
            if (!activeUserName.getText().equals(ordersTable.getSelectionModel().getSelectedItem().getUser_name())) {
                JOptionPane.showMessageDialog(null, "Please choose correct user/movie to return.", "Returning a movue", JOptionPane.INFORMATION_MESSAGE);
            } else {
                ordersDao.returnMovie(String.valueOf(ordersTable.getSelectionModel().getSelectedItem().getMovie_id()));
                showAllMoviesButton();
                showAllOrdersButton();
                txtFieldsDisable();
                System.out.println("returned");
            }
//        } catch (SQLException e){
//            e.printStackTrace();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Please choose active user and mark movie you want to return","Returning a movie",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void returnAllUserOrdersButton(){
        try {
            if (!activeUserName.getText().equals("")) {
                int question = JOptionPane.showConfirmDialog(null, activeUserName.getText() + ", are you sure you want to return all your movies?",
                        "Return all movies confirmation", JOptionPane.YES_NO_OPTION);
                if (question == 0) {
                    ordersDao.returnAllUserMovies(activeUserId.getText());
                    showAllMoviesButton();
                    showAllOrdersButton();
                    txtFieldsDisable();
                    System.out.println("returned all");
                }

            } else {
                JOptionPane.showMessageDialog(null,"Please choose active user who want to return all movies","Returning a movie",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Please choose active user who want to return all movies","Returning a movie",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}