/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.ManagerFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import model.Manager;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class ManagerBean implements Serializable {

    @EJB
    private ManagerFacade managerFacade;

    private Manager newManager;
    private List<Manager> managers;
    
    private Long managerIdInput; //delete ID
    private Manager selectedManager; //current Manager
    private Manager tempManager;
    
    private Long selectedManagerId;
    private String newFirstName;
    private String newLastName;

    @PostConstruct
    public void init() {
        newManager = new Manager();
        selectedManager = new Manager();
        tempManager = new Manager();
        
        selectedManagerId = null;
        newFirstName = null;
        newLastName = null;
        
        managers = managerFacade.getAllManagers(); // Calling the method to retrieve all managers
    }

    public void addManager() {
        newManager.setUserType("M");
        managerFacade.addManager(newManager);
        System.out.println(newManager);
        newManager = new Manager(); // Clear the form after adding a manager
        managers = managerFacade.getAllManagers(); // Update the list of managers after adding a new one
    }
    
    public void updateManager() {
        if (selectedManagerId != null) {
        Manager managerToUpdate = managerFacade.find(selectedManagerId);

            if (managerToUpdate != null) {
                // Update the manager's properties using values from the UI
                managerToUpdate.setFirstName(newFirstName);
                managerToUpdate.setLastName(newLastName);

                // Save the changes to the database
                managerFacade.updateManager(managerToUpdate);

                // Refresh the list of managers
                managers = managerFacade.getAllManagers();
            } else {
                // Handle the case when the manager with the selected ID is not found
                System.out.println("Manager with ID " + selectedManagerId + " not found!");
            }
        } else {
            System.out.println("Selected Manager ID is null");
        }
    }

    public void deleteManager() {
        managerFacade.deleteManager(managerIdInput);
        managers = managerFacade.getAllManagers(); // Update the list of managers after deletion
    }
    
    public void deleteAllManagers() {
        managerFacade.deleteAllManagers();
    }

    public Manager getManagerById(Long managerId) {
        return managerFacade.getManagerById(managerId);
    }

    public List<Manager> getAllManagers() {
        return managers;
    }


    
    // Getters and setters

    public Manager getNewManager() {
        return newManager;
    }

    public void setNewManager(Manager newManager) {
        this.newManager = newManager;
    }

    public List<Manager> getManagers() {
        return managers;
    }
    
    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
    
    public Long getManagerIdInput() {
        return managerIdInput;
    }

    public void setManagerIdInput(Long managerIdInput) {
        this.managerIdInput = managerIdInput;
    }
    
    public Manager getSelectedManager() {
        return selectedManager;
    }

    public void setSelectedManager(Manager selectedManager) {
        this.selectedManager = selectedManager;
        System.out.println(selectedManager);
        System.out.println(selectedManager.getFirstName());
        System.out.println(selectedManager.getId());
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
