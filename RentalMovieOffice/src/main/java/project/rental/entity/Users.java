package project.rental.entity;

import project.rental.dao.UsersDao;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by fmkam on 23.05.2017.
 */
public class Users {

    private int id;
    private String name;
    private String address;

    public Users(){}

    public Users(String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Users(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "\n" + "ID: " + id + ". " + name + " - " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (getId() != users.getId()) return false;
        if (getName() != null ? !getName().equals(users.getName()) : users.getName() != null) return false;
        return getAddress() != null ? getAddress().equals(users.getAddress()) : users.getAddress() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}


