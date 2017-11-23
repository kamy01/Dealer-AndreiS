package ro.fortech.dealer;

import ro.fortech.entities.User;
import ro.fortech.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named
@RequestScoped
public class UserManageBean implements Serializable {
    private User user = new User();
    @EJB
    private UserService userService;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String doLogin() {
        boolean succesfullyAuth = userService.validateLogin(user);
        if (succesfullyAuth)
            return "success?faces-redirect=true";
        else
            return "login?faces-redirect=true";
    }

    public String doRegister() {
        boolean succesfullyRegistered = userService.validateRegister(user);
        if (succesfullyRegistered)
            return "login?faces-redirect=true";
        else
            return "register?faces-redirect=true";
    }
}
