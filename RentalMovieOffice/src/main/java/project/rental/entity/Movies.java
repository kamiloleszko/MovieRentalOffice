package project.rental.entity;

/**
 * Created by fmkam on 27.05.2017.
 */
public class Movies {

    private int id;
    private String title;
    private String director;
    private boolean available;

    public Movies(){}

    public Movies(int id, String title, String director, boolean available) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.available = available;
    }

    public Movies(String title, String director, boolean available) {
        this.title = title;
        this.director = director;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "\n"+ "ID: " + id + ". " + title + " " + director + " availability " + available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movies)) return false;

        Movies movies = (Movies) o;

        if (getId() != movies.getId()) return false;
        if (isAvailable() != movies.isAvailable()) return false;
        if (getTitle() != null ? !getTitle().equals(movies.getTitle()) : movies.getTitle() != null) return false;
        return getDirector() != null ? getDirector().equals(movies.getDirector()) : movies.getDirector() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDirector() != null ? getDirector().hashCode() : 0);
        result = 31 * result + (isAvailable() ? 1 : 0);
        return result;
    }
}
