/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.UUID;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Ryzen5
 */
@Named
@SessionScoped
public class UserSessionBean implements Serializable {
    private Long userId;
    private String sessionID;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        System.out.println(userId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SESSION_ID", UUID.randomUUID().toString());

        this.userId = userId;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public boolean isLoggedIn() {
        return userId != null;
    }

    public String logout() {
        userId = null;
        // Get the FacesContext and ExternalContext
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Invalidate the session
        externalContext.invalidateSession();

        return "login";
    }
}
