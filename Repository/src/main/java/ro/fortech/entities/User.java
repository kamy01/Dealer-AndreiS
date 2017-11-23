package ro.fortech.entities;

import javax.persistence.*;

@Entity(name="user")
@NamedQuery(name = "FindUserByName", query = "SELECT u from user u WHERE u.username=?1")
public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
