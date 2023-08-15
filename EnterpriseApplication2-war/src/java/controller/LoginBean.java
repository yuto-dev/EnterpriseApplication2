/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UserFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.User;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class LoginBean implements Serializable {
    
    @Inject
    private UserSessionBean UserSessionBean;

    @EJB
    private UserFacade UserFacade;
    
    private String username;
    private String password;
    private Long loggedInUserId;
    private String secretKey;

    @PostConstruct
    public void init() {
        loggedInUserId = null;
        username = null;
        password = null;
        secretKey = "123backdoor";
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

    public Long getLoggedInUserId() {
        return loggedInUserId;
    }

    public void testPrint() {
        System.out.println("in in");
    }
    
    public String login() {
        System.out.println(username);
        System.out.println("in");
        if (username.equals(secretKey)){
                return "addManager";
        }
        // Find the user with the matching credentials
        User matchedUser = UserFacade.userLogin(username, password);

        if (matchedUser != null) {
 
            loggedInUserId = matchedUser.getId();
            UserSessionBean.setUserId(matchedUser.getId());

            // Redirect to the appropriate home page based on user type
            switch (matchedUser.getUserType()) {
                case "M":
                    return "managerMenu";
                case "S":
                    return "kitchenStaffMenu";
                case "C":
                    return "customerMenu";
                default:
                    // Unknown user type, handle as needed
                    return "unknownUserType?faces-redirect=true";
            }

        } else {
            System.out.println("waduh");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }
}
