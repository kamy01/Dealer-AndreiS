package ro.fortech.beans;

import ro.fortech.services.BuyerService;
import ro.fortech.services.DealerService;
import ro.fortech.services.UserService;
import utilities.dtos.UserDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserManagedBean implements Serializable {

    private UserDto user = new UserDto();
    private boolean isDealer;
    @EJB
    private UserService userService;
    @EJB
    private DealerService dealerService;
    @EJB
    private BuyerService buyerService;

    public String doLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean succesfullyAuth = userService.validateLogin(user);
        if (succesfullyAuth)
        {
            context.getExternalContext().getSessionMap().put("user", user);
            isDealer = userService.validateRole(user);
            return "mainPage?faces-redirect=true";
        }
        else
            return "login?faces-redirect=true";
    }

    public String doRegister() {
        boolean succesfullyRegistered = userService.validateRegister(user);
        if (isDealer)
            succesfullyRegistered = succesfullyRegistered && dealerService.validateRegister(user);
        else
            succesfullyRegistered = succesfullyRegistered && buyerService.validateRegister(user);
        if (succesfullyRegistered)
            return "login?faces-redirect=true";
        else
            return "register?faces-redirect=true";
    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }
}
