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
    private String linkManagerBookingMenu;
    
    private String linkManagerCrud;
    private String linkAddManager;
    private String linkDeleteManager;
    private String linkUpdateManager;
    
    private String linkKitchenStaffCrud;
    private String linkAddKitchenStaff;
    private String linkDeleteKitchenStaff;
    private String linkUpdateKitchenStaff;
    private String linkSearchKitchenStaff;
    
    private String linkCustomerCrud;
    private String linkAddCustomer;
    private String linkDeleteCustomer;
    private String linkUpdateCustomer;
    
    private String linkAssignBooking;
    private String linkCollectPayment;
    
    private String linkManagerReportMenu;
    private String linkReportViewRating;
    private String linkReportViewStock;
    private String linkReportViewFinance;
    private String linkReportBuyStock;
    
    // STAFF
    
    private String linkKitchenStaffMenu;
    
    private String linkEditKitchenStaff;
    
    private String linkUpdateKitchenStaffBooking;
    
    private String linkViewSelfRating;
    
    // CUSTOMER
    
    private String linkCustomerMenu;
    private String linkCustomerBookingMenu;
    private String linkCustomerRatingMenu;
    
    private String linkEditCustomer;;
    private String linkAddCustomerBooking;
    private String linkDeleteBooking;
    private String linkDeleteCustomerBooking;
    private String linkUpdateBooking;
    private String linkUpdateCustomerBooking;
    
    private String linkAddCustomerRating;
    private String linkDeleteCustomerRating;
    private String linkUpdateCustomerRating;
    
    private String linkAddReview;
    private String linkDeleteReview;
    private String linkUpdateReview;
    
    
    
    @PostConstruct
    public void init() {
        
        linkLogin = "login";
        
        linkManagerCrud = "managerCrud";
        linkManagerMenu = "managerMenu";
        linkManagerBookingMenu = "managerBookingMenu";
        
        linkAddManager = "addManager";
        linkDeleteManager = "deleteManager";
        linkUpdateManager = "updateManager";
    
        linkKitchenStaffCrud = "kitchenStaffCrud";
        linkAddKitchenStaff = "addKitchenStaff";
        linkDeleteKitchenStaff = "deleteKitchenStaff";
        linkUpdateKitchenStaff = "updateKitchenStaff";
        linkSearchKitchenStaff = "searchKitchenStaff";
    
        linkCustomerCrud = "customerCrud";
        linkAddCustomer = "addCustomer";
        linkDeleteCustomer = "deleteCustomer";
        linkUpdateCustomer = "updateCustomer";
        
        linkAssignBooking = "assignBooking";
        linkCollectPayment = "collectPayment";
        linkDeleteBooking = "deleteBooking";
        
        linkManagerReportMenu = "managerReportMenu";
        linkReportViewRating = "reportViewRating";
        linkReportViewStock = "reportViewStock";
        linkReportViewFinance = "reportViewFinance";
        linkReportBuyStock = "reportBuyStock";
        
        // STAFF
        
        linkKitchenStaffMenu = "kitchenStaffMenu";
        
        linkEditKitchenStaff = "editKitchenStaff";
        
        linkUpdateKitchenStaffBooking = "updateKitchenStaffBooking";
        
        linkViewSelfRating = "viewSelfRating";
        
        // CUSTOMER
        
        linkCustomerMenu = "customerMenu";
        linkCustomerBookingMenu = "customerBookingMenu";
        linkCustomerRatingMenu = "customerRatingMenu";
                
        linkEditCustomer = "editCustomer";
        
        linkAddCustomerBooking = "addCustomerBooking";
        linkDeleteCustomerBooking = "deleteCustomerBooking";
        linkUpdateCustomerBooking = "updateCustomerBooking";
        
        linkAddCustomerRating = "addCustomerRating";
        linkDeleteCustomerRating = "deleteCustomerRating";
        linkUpdateCustomerRating = "updateCustomerRating";
        
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


    public String getLinkUpdateCustomerBooking() {
        return linkUpdateCustomerBooking;
    }

    public String getLinkAssignBooking() {
        return linkAssignBooking;
    }

    public String getLinkUpdateKitchenStaffBooking() {
        return linkUpdateKitchenStaffBooking;
    }

    public String getLinkCollectPayment() {
        return linkCollectPayment;
    }

    public String getLinkManagerBookingMenu() {
        return linkManagerBookingMenu;
    }

    public String getLinkCustomerBookingMenu() {
        return linkCustomerBookingMenu;
    }

    public String getLinkCustomerRatingMenu() {
        return linkCustomerRatingMenu;
    }

    public String getLinkAddCustomerRating() {
        return linkAddCustomerRating;
    }
    
    public String getLinkDeleteCustomerRating() {
        return linkDeleteCustomerRating;
    }

    public String getLinkUpdateCustomerRating() {
        return linkUpdateCustomerRating;
    }

    public String getLinkViewSelfRating() {
        return linkViewSelfRating;
    }

    public String getLinkManagerReportMenu() {
        return linkManagerReportMenu;
    }

    public String getLinkReportViewRating() {
        return linkReportViewRating;
    }

    public String getLinkReportViewStock() {
        return linkReportViewStock;
    }

    public String getLinkReportViewFinance() {
        return linkReportViewFinance;
    }

    public String getLinkReportBuyStock() {
        return linkReportBuyStock;
    }

    public String getLinkSearchKitchenStaff() {
        return linkSearchKitchenStaff;
    }
    
    
    
}
