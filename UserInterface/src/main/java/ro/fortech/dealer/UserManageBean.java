package ro.fortech.dealer;

import ro.fortech.entities.User;
import ro.fortech.services.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ManagedBean(name="userManageBean")
@SessionScoped
public class UserManageBean {
    private User user = new User();
    //@Inject
    private UserService userService = new UserService();

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
