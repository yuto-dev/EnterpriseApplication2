/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.BookingFacade;
import facade.UserFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import static jdk.nashorn.internal.runtime.JSType.toLong;
import model.Booking;
import model.User;

/**
 *
 * @author Ryzen5
 */
@Named
@RequestScoped
public class CustomerBean {
    
    @EJB
    private UserFacade UserFacade;
    @EJB
    private BookingFacade BookingFacade;
    
    @Inject
    private UserSessionBean UserSessionBean;
    
    private Long selfId;
    
    User selfCustomer;
    
    private String newFirstName;
    private String newLastName;
    private String newUsername;
    private String newPassword;
    private String newEmail;
    private String newPhoneNumber;
    
    private Booking newBooking;
    private List<Booking> bookings;
    private Date selectedDate;

    private Long bookingIdInput;
    
    private Booking selectedBooking;
    private Long selectedBookingId;
    
    
    
    @PostConstruct
    public void init() {
        
        System.out.println("init");
        selfId = UserSessionBean.getUserId();
        System.out.println("init");
        selfCustomer = UserFacade.find(selfId);
                System.out.println("init");

        newBooking = new Booking();
                System.out.println("init");

        if (BookingFacade == null){
                System.out.println("waduh gaming");
        }
        
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId));
                System.out.println("init");

        
        selectedDate = null;
                System.out.println("init");

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
    
    public void updateCustomer() {
        
        System.out.println(selfId);

        if (selfId != null) {
            User UserToUpdate = selfCustomer;

                if (UserToUpdate != null) {
                    // Update the User's properties using values from the UI
                    UserToUpdate.setId(selfId);
                    
                    if (newFirstName == null || newFirstName.trim().isEmpty()) {
                        newFirstName = selfCustomer.getFirstName();
                    }
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        newLastName = selfCustomer.getLastName();
                    }
                    if (newUsername == null || newUsername.trim().isEmpty()) {
                        newUsername = selfCustomer.getUsername();
                    }
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        newPassword = selfCustomer.getPassword();
                    }
                    if (newEmail == null || newEmail.trim().isEmpty()) {
                        newEmail = selfCustomer.getEmail();
                    }
                    if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
                        newPhoneNumber = selfCustomer.getPhoneNumber();
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
                    selfCustomer = UserFacade.find(selfId);
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("User with ID " + selfId + " not found!");
                }
        } else {
            System.out.println("Selected User ID is null");
        }
    }
    
    public void addBooking() {
        
        System.out.println("1");
        newBooking.setStatus("Pending");
        System.out.println("1");
        newBooking.setCustomer(UserFacade.find(selfId));
        System.out.println("1");
        newBooking.setCustomerId(selfId);
        BookingFacade.addBooking(newBooking);
        System.out.println("1");
        
        newBooking = new Booking(); // Clear the form after adding a Booking
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId)); // Update the list of Users after adding a new one
    }
    
    public void updateBooking(Booking booking) {
        setSelectedBooking(booking);

        selectedBookingId = getSelectedBookingId();
        if (selectedBookingId != null) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);

                if (BookingToUpdate != null) {
                    // Update the User's properties using values from the UI
                    BookingToUpdate.setId(getSelectedBookingId());
                    
                    BookingToUpdate.setBookingDate(selectedDate);
                    

                    // Save the changes to the database
                    BookingFacade.updateBooking(BookingToUpdate);

                    // Refresh the list of Users
                    bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId));
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("Booking with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking ID is null");
        }
    }
    
    public void deleteBooking() {
        BookingFacade.deleteBooking(bookingIdInput);
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId)); // Update the list of Users after deletion
    }
    
    // Getter & Setter

    public Booking getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(Booking newBooking) {
        this.newBooking = newBooking;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public User getSelfCustomer() {
        return selfCustomer;
    }

    public void setSelfCustomer(User selfCustomer) {
        this.selfCustomer = selfCustomer;
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

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public Long getBookingIdInput() {
        return bookingIdInput;
    }

    public void setBookingIdInput(Long bookingIdInput) {
        this.bookingIdInput = bookingIdInput;
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
    
    
    
}
