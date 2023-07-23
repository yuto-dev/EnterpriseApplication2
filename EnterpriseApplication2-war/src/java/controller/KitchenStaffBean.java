/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.KitchenStaffFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.KitchenStaff;

/**
 *
 * @author Ryzen5
 */
@Named
@SessionScoped
public class KitchenStaffBean implements Serializable {
    
    @EJB
    private KitchenStaffFacade kitchenStaffFacade;

    private KitchenStaff newKitchenStaff;
    private KitchenStaff selectedKitchenStaff;

    public KitchenStaffBean() {
        newKitchenStaff = new KitchenStaff();
    }

    public List<KitchenStaff> getAllKitchenStaff() {
        return kitchenStaffFacade.getAllKitchenStaff();
    }

    public void addKitchenStaff() {
        newKitchenStaff.setUserType("S");
        kitchenStaffFacade.addKitchenStaff(newKitchenStaff);
        System.out.println(newKitchenStaff);
        newKitchenStaff = new KitchenStaff(); // Clear the form after adding a manager
        managers = managerFacade.getAllManagers(); // Update the list of managers after adding a new one
    }

    public void updateKitchenStaff() {
        kitchenStaffFacade.update(selectedKitchenStaff);
    }

    public void deleteKitchenStaff() {
        kitchenStaffFacade.delete(selectedKitchenStaff);
    }

    // Getters and setters for newKitchenStaff and selectedKitchenStaff
    // ...
}
