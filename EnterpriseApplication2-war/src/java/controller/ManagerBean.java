/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.UserFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
    private String newUsername;
    private String newPassword;
    private String newEmail;
    private String newPhoneNumber;

    @PostConstruct
    public void init() {
        newManager = new User();
        selectedManager = new User();
        
        selectedManagerId = null;
        newFirstName = null;
        newLastName = null;
        newUsername = null;
        newPassword = null;
        newEmail = null;
        newPhoneNumber = null;
        
        managers = UserFacade.getAllUsers(); // Calling the method to retrieve all Managers
    }

    // TODO CHANGE GETALLUSERS BACK TO GETROLE
    
    
    public static String generatePhoneNumber() {
        Random random = new Random();
        
        // Generate the first two digits ("01")
        StringBuilder phoneNumberBuilder = new StringBuilder("01");
        
        // Generate the remaining 8 digits
        for (int i = 0; i < 8; i++) {
            phoneNumberBuilder.append(random.nextInt(10));
        }
        
        return phoneNumberBuilder.toString();
    }
    
    public void addManager() {
        newManager.setUserType("M");
        System.out.println("inside");
        if (newManager.getEmail() == null || newManager.getEmail().trim().isEmpty()) {
            newManager.setEmail(newManager.getUsername()+"@mail.com");
        }
        
        if (newManager.getPhoneNumber()== null || newManager.getPhoneNumber().trim().isEmpty()) {
            String generatedPhoneNumber = generatePhoneNumber();
            newManager.setPhoneNumber(generatedPhoneNumber);
        }
        
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
                    
                    if (newFirstName == null || newFirstName.trim().isEmpty()) {
                        newFirstName = selectedManager.getFirstName();
                    }
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        newLastName = selectedManager.getLastName();
                    }
                    if (newUsername == null || newUsername.trim().isEmpty()) {
                        newUsername = selectedManager.getUsername();
                    }
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        newPassword = selectedManager.getPassword();
                    }
                    if (newEmail == null || newEmail.trim().isEmpty()) {
                        newEmail = selectedManager.getEmail();
                    }
                    if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
                        newPhoneNumber = selectedManager.getPhoneNumber();
                    }
                    
                    UserToUpdate.setFirstName(newFirstName);
                    UserToUpdate.setLastName(newLastName);
                    UserToUpdate.setUsername(newUsername);
                    UserToUpdate.setPassword(newPassword);
                    UserToUpdate.setEmail(newEmail);
                    UserToUpdate.setPhoneNumber(newPhoneNumber);

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
    // DELTE LATER
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

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }
    
}
