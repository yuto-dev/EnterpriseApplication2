/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class LinkBean {
    
    private String linkLogin;
    
    private String linkManagerMenu;
    
    private String linkManagerCrud;
    private String linkAddManager;
    private String linkDeleteManager;
    private String linkUpdateManager;
    
    private String linkKitchenStaffCrud;
    private String linkAddKitchenStaff;
    private String linkDeleteKitchenStaff;
    private String linkUpdateKitchenStaff;
    
    private String linkCustomerCrud;
    private String linkAddCustomer;
    private String linkDeleteCustomer;
    private String linkUpdateCustomer;
    
    // STAFF
    
    private String linkKitchenStaffMenu;
    private String linkEditKitchenStaff;
    
    @PostConstruct
    public void init() {
        
        linkLogin = "login";
        
        linkManagerCrud = "managerCrud";
        linkManagerMenu = "managerMenu";
        linkAddManager = "addManager";
        linkDeleteManager = "deleteManager";
        linkUpdateManager = "updateManager";
    
        linkKitchenStaffCrud = "kitchenStaffCrud";
        linkAddKitchenStaff = "addKitchenStaff";
        linkDeleteKitchenStaff = "deleteKitchenStaff";
        linkUpdateKitchenStaff = "updateKitchenStaff";
    
        linkCustomerCrud = "customerCrud";
        linkAddCustomer = "addCustomer";
        linkDeleteCustomer = "deleteCustomer";
        linkUpdateCustomer = "updateCustomer";
        
        // STAFF
        
        linkKitchenStaffMenu = "kitchenStaffMenu";
        linkEditKitchenStaff = "editKitchenStaff";
        
    }

    public String getLinkLogin() {
        return linkLogin;
    }

    public void setLinkLogin(String linkLogin) {
        this.linkLogin = linkLogin;
    }

    public String getLinkManagerCrud() {
        return linkManagerCrud;
    }

    public String getLinkManagerMenu() {
        return linkManagerMenu;
    }

    public String getLinkAddManager() {
        return linkAddManager;
    }

    public String getLinkDeleteManager() {
        return linkDeleteManager;
    }

    public String getLinkUpdateManager() {
        return linkUpdateManager;
    }

    public String getLinkKitchenStaffCrud() {
        return linkKitchenStaffCrud;
    }

    public String getLinkAddKitchenStaff() {
        return linkAddKitchenStaff;
    }

    public String getLinkDeleteKitchenStaff() {
        return linkDeleteKitchenStaff;
    }

    public String getLinkUpdateKitchenStaff() {
        return linkUpdateKitchenStaff;
    }

    public String getLinkCustomerCrud() {
        return linkCustomerCrud;
    }

    public String getLinkAddCustomer() {
        return linkAddCustomer;
    }

    public String getLinkDeleteCustomer() {
        return linkDeleteCustomer;
    }

    public String getLinkUpdateCustomer() {
        return linkUpdateCustomer;
    }

    public String getLinkKitchenStaffMenu() {
        return linkKitchenStaffMenu;
    }

    public void setLinkKitchenStaffMenu(String linkKitchenStaffMenu) {
        this.linkKitchenStaffMenu = linkKitchenStaffMenu;
    }

    public String getLinkEditKitchenStaff() {
        return linkEditKitchenStaff;
    }

    public void setLinkEditKitchenStaff(String linkEditKitchenStaff) {
        this.linkEditKitchenStaff = linkEditKitchenStaff;
    }
    
    
    
}
