package ro.fortech.entities;

import javax.persistence.*;

@Entity(name="user")
@NamedQueries({
@NamedQuery(name = "User.findByName", query = "SELECT u from user u WHERE u.username=?1"),
@NamedQuery(name = "User.findIdByName", query = "SELECT u.id from user u WHERE u.username=?1")
})
public class UserEntity {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

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
