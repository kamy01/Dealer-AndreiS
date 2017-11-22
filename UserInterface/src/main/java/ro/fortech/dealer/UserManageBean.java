package ro.fortech.dealer;

import ro.fortech.entities.User;
import ro.fortech.services.UserService;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named
@SessionScoped
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
        boolean succesfullyAuth = userService.validate(user);
        if (succesfullyAuth)
            return "success?faces-redirect=true";
        else
            return "login?faces-redirect=true";
    }
}
