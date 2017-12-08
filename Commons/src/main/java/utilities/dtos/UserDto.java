package utilities.dtos;

import java.io.Serializable;

public class UserDto implements Serializable {

    private String username;
    private String password;
    private long identificationNumber;
    private double amount;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(long identificationNumber) {
        this.identificationNumber = identificationNumber;
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
