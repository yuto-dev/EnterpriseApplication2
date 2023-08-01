/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.BookingFacade;
import facade.UserFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Booking;
import model.User;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class KitchenStaffBean {
    
    @EJB
    private UserFacade UserFacade;
    
    @EJB
    private BookingFacade BookingFacade;
    
    @Inject
    private UserSessionBean UserSessionBean;
    
    private Long selfId;
    
    User selfKitchenStaff;
    
    private String newFirstName;
    private String newLastName;
    private String newUsername;
    private String newPassword;
    private String newEmail;
    private String newPhoneNumber;
    
    private List<Booking> bookings;
    private List<Booking> selfRatings;
    
    private Booking selectedBooking;
    private Long selectedBookingId;

    @PostConstruct
    public void init() {
        
        selfId = UserSessionBean.getUserId();
        selfKitchenStaff = UserFacade.find(selfId);
        
        newFirstName = null;
        newLastName = null;
        newUsername = null;
        newPassword = null;
        newEmail = null;
        newPhoneNumber = null;
        
        bookings = BookingFacade.getBookingsByKitchenStaff(UserFacade.find(selfId));
        selfRatings = BookingFacade.getKitchenStaffRatedBookings(UserFacade.find(selfId));
        
        selectedBooking = new Booking();
        selectedBookingId = null;
        
    }
    
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            // Return original input if it is not a 10-digit number
            return phoneNumber;
        }

        // Use StringBuilder to build the formatted phone number
        StringBuilder formattedNumber = new StringBuilder();

        // Add the first three digits
        formattedNumber.append(phoneNumber.substring(0, 3));
        formattedNumber.append("-");

        // Add the next four digits
        formattedNumber.append(phoneNumber.substring(3, 7));
        formattedNumber.append("-");

        // Add the last three digits
        formattedNumber.append(phoneNumber.substring(7));

        return formattedNumber.toString();
    }
    
    public void updateKitchenStaff() {
        
        System.out.println(selfId);

        if (selfId != null) {
            User UserToUpdate = selfKitchenStaff;

                if (UserToUpdate != null) {
                    // Update the User's properties using values from the UI
                    UserToUpdate.setId(selfId);
                    
                    if (newFirstName == null || newFirstName.trim().isEmpty()) {
                        newFirstName = selfKitchenStaff.getFirstName();
                    }
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        newLastName = selfKitchenStaff.getLastName();
                    }
                    if (newUsername == null || newUsername.trim().isEmpty()) {
                        newUsername = selfKitchenStaff.getUsername();
                    }
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        newPassword = selfKitchenStaff.getPassword();
                    }
                    if (newEmail == null || newEmail.trim().isEmpty()) {
                        newEmail = selfKitchenStaff.getEmail();
                    }
                    if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
                        newPhoneNumber = selfKitchenStaff.getPhoneNumber();
                    }
                    
                    UserToUpdate.setFirstName(newFirstName);
                    UserToUpdate.setLastName(newLastName);
                    UserToUpdate.setUsername(newUsername);
                    UserToUpdate.setPassword(newPassword);
                    UserToUpdate.setEmail(newEmail);
                    UserToUpdate.setPhoneNumber(formatPhoneNumber(newPhoneNumber));

                    // Save the changes to the database
                    UserFacade.updateUser(UserToUpdate);

                    // Refresh the list of Users
                    selfKitchenStaff = UserFacade.find(selfId);
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("User with ID " + selfId + " not found!");
                }
        } else {
            System.out.println("Selected User ID is null");
        }
    }
    
    public void progressBooking(Booking booking) {
        setSelectedBooking(booking);
        
        selectedBookingId = booking.getId();
        if (selectedBookingId != null) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);

                if (BookingToUpdate != null) {
                    // Update the User's properties using values from the UI
                    BookingToUpdate.setStatus("Fulfilled");
                    
                    

                    // Save the changes to the database
                    BookingFacade.updateBooking(BookingToUpdate);

                    // Refresh the list of Users
                    bookings = BookingFacade.getBookingsByKitchenStaff(UserFacade.find(selfId));
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("Booking with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking ID is null");
        }
    }
    
    // Getter & Setter

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public User getSelfKitchenStaff() {
        return selfKitchenStaff;
    }

    public void setSelfKitchenStaff(User selfKitchenStaff) {
        this.selfKitchenStaff = selfKitchenStaff;
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

    public Booking getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }

    public Long getSelectedBookingId() {
        return selectedBookingId;
    }

    public void setSelectedBookingId(Long selectedBookingId) {
        this.selectedBookingId = selectedBookingId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Booking> getSelfRatings() {
        return selfRatings;
    }

    public void setSelfRatings(List<Booking> selfRatings) {
        this.selfRatings = selfRatings;
    }
    
    
    
}
