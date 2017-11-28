package ro.fortech.beans;

import ro.fortech.entities.User;
import ro.fortech.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class UserManagedBean implements Serializable {
    private User user = new User();

    @EJB
    private UserService userService;

    public String doLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean succesfullyAuth = userService.validateLogin(user);
        if (succesfullyAuth)
        {
            context.getExternalContext().getSessionMap().put("user", user);
            return "mainPage?faces-redirect=true";
        }
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

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
