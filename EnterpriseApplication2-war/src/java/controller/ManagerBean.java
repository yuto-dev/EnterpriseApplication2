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

    private User newUser;
    private List<User> Users;
    
    private Long UserIdInput; //delete ID
    private User selectedUser; //current User
    private User tempUser;
    
    private Long selectedUserId;
    private String newFirstName;
    private String newLastName;

    @PostConstruct
    public void init() {
        newUser = new User();
        selectedUser = new User();
        tempUser = new User();
        
        selectedUserId = null;
        newFirstName = null;
        newLastName = null;
        
        Users = UserFacade.getAllUsers(); // Calling the method to retrieve all Users
    }

    // TODO CHANGE GETALLUSERS BACK TO GETROLE
    
    public void addManager() {
        newUser.setUserType("M");
        UserFacade.addUser(newUser);
        newUser = new User(); // Clear the form after adding a User
        Users = UserFacade.getAllUsers(); // Update the list of Users after adding a new one
    }
    
    public void updateManager() {
        if (selectedUserId != null) {
        User UserToUpdate = UserFacade.find(selectedUserId);

            if (UserToUpdate != null) {
                // Update the User's properties using values from the UI
                UserToUpdate.setFirstName(newFirstName);
                UserToUpdate.setLastName(newLastName);

                // Save the changes to the database
                UserFacade.updateUser(UserToUpdate);

                // Refresh the list of Users
                Users = UserFacade.getUsersByRole("M");
            } else {
                // Handle the case when the User with the selected ID is not found
                System.out.println("User with ID " + selectedUserId + " not found!");
            }
        } else {
            System.out.println("Selected User ID is null");
        }
    }

    public void deleteManager() {
        UserFacade.deleteUser(UserIdInput);
        Users = UserFacade.getUsersByRole("M"); // Update the list of Users after deletion
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
        return Users;
    }


    
    // Getters and setters

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<User> getUsers() {
        return Users;
    }
    
    public void setUsers(List<User> Users) {
        this.Users = Users;
    }
    
    public Long getUserIdInput() {
        return UserIdInput;
    }

    public void setUserIdInput(Long UserIdInput) {
        this.UserIdInput = UserIdInput;
    }
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        System.out.println(selectedUser);
        System.out.println(selectedUser.getFirstName());
        System.out.println(selectedUser.getId());
    }

    public Long getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
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
