package project.rental.entity;

/**
 * Created by fmkam on 27.05.2017.
 */
public class Orders {

    private int id;
    private int user_id;
    private String user_name;
    private int movie_id;
    private String movie_title;
    private String date_from;
    private boolean returned;

    public Orders(int id, int user_id, String user_name, int movie_id, String movie_title, String date_from, boolean returned) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.date_from = date_from;
        this.returned = returned;
    }

    public Orders(int id, int user_id, int movie_id, String date_from) {
        this.id = id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.date_from = date_from;
        returned = false;
    }

    public Orders(int user_id, int movie_id, String date_from) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.date_from = date_from;
        returned = false;
    }

    public Orders(int id, int user_id, String user_name, int movie_id, String movie_title, String date_from) {
        this.id = id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.date_from = date_from;
        returned = false;
    }

    public Orders(int user_id, String user_name, int movie_id, String movie_title, String date_from) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.date_from = date_from;
        returned = false;
    }

    public boolean isReturned() {
        return returned;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    @Override
    public String toString() {
        return "\n"+"id: " + id + " " + " userID: " + user_id + " user name: " + user_name + " movie ID: " + movie_id + " movie title: " + movie_title + " from: " + date_from + " returned? " + returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;

        Orders orders = (Orders) o;

        if (getId() != orders.getId()) return false;
        if (getUser_id() != orders.getUser_id()) return false;
        if (getMovie_id() != orders.getMovie_id()) return false;
        if (isReturned() != orders.isReturned()) return false;
        return getDate_from() != null ? getDate_from().equals(orders.getDate_from()) : orders.getDate_from() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getUser_id();
        result = 31 * result + getMovie_id();
        result = 31 * result + (getDate_from() != null ? getDate_from().hashCode() : 0);
        result = 31 * result + (isReturned() ? 1 : 0);
        return result;
    }
}
