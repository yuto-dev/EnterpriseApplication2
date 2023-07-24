/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UserFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.User;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class ManagerBean implements Serializable {

    @EJB
    private UserFacade UserFacade;

    private User newManager;
    private List<User> managers;
    
    private Long ManagerIdInput; //delete ID
    private User selectedManager; //current User
    
    private Long selectedManagerId;
    private String newFirstName;
    private String newLastName;

    @PostConstruct
    public void init() {
        newManager = new User();
        selectedManager = new User();
        
        selectedManagerId = null;
        newFirstName = null;
        newLastName = null;
        
        managers = UserFacade.getAllUsers(); // Calling the method to retrieve all Managers
    }

    // TODO CHANGE GETALLUSERS BACK TO GETROLE
    
    public void addManager() {
        newManager.setUserType("M");
        UserFacade.addUser(newManager);
        newManager = new User(); // Clear the form after adding a User
        managers = UserFacade.getAllUsers(); // Update the list of Users after adding a new one
    }
    
    public void updateManager(User manager) {
        System.out.println(manager);
        setSelectedManager(manager);
        System.out.println(selectedManager);
        System.out.println(getSelectedManagerId());
        selectedManagerId = getSelectedManagerId();
        if (selectedManagerId != null) {
            User UserToUpdate = UserFacade.find(selectedManagerId);

                if (UserToUpdate != null) {
                    // Update the User's properties using values from the UI
                    UserToUpdate.setId(getSelectedManagerId());
                    UserToUpdate.setFirstName(newFirstName);
                    UserToUpdate.setLastName(newLastName);

                    // Save the changes to the database
                    UserFacade.updateUser(UserToUpdate);

                    // Refresh the list of Users
                    managers = UserFacade.getUsersByRole("M");
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("User with ID " + selectedManagerId + " not found!");
                }
        } else {
            System.out.println("Selected User ID is null");
        }
    }

    public void deleteManager() {
        UserFacade.deleteUser(ManagerIdInput);
        managers = UserFacade.getAllUsers(); // Update the list of Users after deletion
    }
    
    public void deleteAllManagers() {
        UserFacade.deleteAllManagers();
    }
    
    public void deleteAllUsers() {
        UserFacade.deleteAllUsers();
    }

    public User getManagerById(Long UserId) {
        return UserFacade.find(UserId);
    }

    public List<User> getAllUsers() {
        return managers;
    }


    
    // Getters and setters

    public User getNewManager() {
        return newManager;
    }

    public void setNewManager(User newManager) {
        this.newManager = newManager;
    }

    public List<User> getManagers() {
        return managers;
    }
    
    public void setManagers(List<User> Managers) {
        this.managers = Managers;
    }
    
    public Long getManagerIdInput() {
        return ManagerIdInput;
    }

    public void setManagerIdInput(Long ManagerIdInput) {
        this.ManagerIdInput = ManagerIdInput;
    }
    
    public User getSelectedManager() {
        return selectedManager;
    }

    public void setSelectedManager(User selectedManager) {
        this.selectedManager = selectedManager;
    }

    public Long getSelectedManagerId() {
        return selectedManagerId;
    }

    public void setSelectedManagerId(Long selectedManagerId) {
        this.selectedManagerId = selectedManagerId;
    }
    
    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }
}
