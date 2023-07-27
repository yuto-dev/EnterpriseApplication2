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
    
    private String linkAssignBooking;
    
    // STAFF
    
    private String linkKitchenStaffMenu;
    
    private String linkEditKitchenStaff;
    
    private String linkUpdateKitchenStaffBooking;
    
    // CUSTOMER
    
    private String linkCustomerMenu;
    
    private String linkEditCustomer;;
    private String linkAddCustomerBooking;
    private String linkDeleteBooking;
    private String linkDeleteCustomerBooking;
    private String linkUpdateBooking;
    private String linkUpdateCustomerBooking;
    
    private String linkAddReview;
    private String linkDeleteReview;
    private String linkUpdateReview;
    
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
        
        linkAssignBooking = "assignBooking";
        
        linkDeleteBooking = "deleteBooking";
        
        // STAFF
        
        linkKitchenStaffMenu = "kitchenStaffMenu";
        
        linkEditKitchenStaff = "editKitchenStaff";
        
        linkUpdateKitchenStaffBooking = "updateKitchenStaffBooking";
        
        // CUSTOMER
        
        linkCustomerMenu = "customerMenu";
        
        linkEditCustomer = "editCustomer";
        
        linkAddCustomerBooking = "addCustomerBooking";
        linkDeleteCustomerBooking = "deleteCustomerBooking";
        linkUpdateCustomerBooking = "updateCustomerBooking";
        
        linkAddReview = "addReview";
        linkDeleteReview = "deleteReview";
        linkUpdateReview = "updateReview";
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

    public String getLinkEditKitchenStaff() {
        return linkEditKitchenStaff;
    }
    
    public String getLinkCustomerMenu() {
        return linkCustomerMenu;
    }

    public String getLinkEditCustomer() {
        return linkEditCustomer;
    }

    public String getLinkAddCustomerBooking() {
        return linkAddCustomerBooking;
    }

    public String getLinkDeleteBooking() {
        return linkDeleteBooking;
    }

    public String getLinkUpdateBooking() {
        return linkUpdateBooking;
    }

    public String getLinkAddReview() {
        return linkAddReview;
    }

    public String getLinkDeleteReview() {
        return linkDeleteReview;
    }

    public String getLinkUpdateReview() {
        return linkUpdateReview;
    }

    public String getLinkDeleteCustomerBooking() {
        return linkDeleteCustomerBooking;
    }

    public void setLinkDeleteCustomerBooking(String linkDeleteCustomerBooking) {
        this.linkDeleteCustomerBooking = linkDeleteCustomerBooking;
    }

    public String getLinkUpdateCustomerBooking() {
        return linkUpdateCustomerBooking;
    }

    public String getLinkAssignBooking() {
        return linkAssignBooking;
    }

    public void setLinkAssignBooking(String linkAssignBooking) {
        this.linkAssignBooking = linkAssignBooking;
    }

    public String getLinkUpdateKitchenStaffBooking() {
        return linkUpdateKitchenStaffBooking;
    }
    
    
    
}
