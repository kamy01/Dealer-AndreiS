package ro.fortech.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="dealer")
public class DealerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="dealer_fk")
    private Set<CarEntity> cars = new HashSet<CarEntity>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }
}
