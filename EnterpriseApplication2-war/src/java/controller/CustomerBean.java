/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.BookingFacade;
import facade.UserFacade;
import java.util.ArrayList;
import java.util.Date;
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
    private int numberOfSeats;
    private List<Integer> seatOptions;

    private Long bookingIdInput;
    
    private Booking selectedBooking;
    private Long selectedBookingId;
    private Date newBookingDate;
    private Long newBookingSeats;
    private String newBookingFood;
    
    private Long newRating;
    private String newReview;
    
    private Booking checkBooking;
    
    
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
        
        seatOptions = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            seatOptions.add(i);
        }
        
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId));
                System.out.println("init");

        
        selectedDate = null;
                System.out.println("init");

        selectedBooking = new Booking();
        selectedBookingId = null;
        checkBooking = null;
        
        newBookingDate = null;
        newBookingSeats = null;
        newBookingFood = null;
        
        newRating = null;
        newReview = null;
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
        
        if (newBooking.getFood().equals("Chicken")){
            newBooking.setPrice(8 * newBooking.getSeats());
        }
        else if (newBooking.getFood().equals("Beef")){
            newBooking.setPrice(10 * newBooking.getSeats());
        }
        else if (newBooking.getFood().equals("Vegetarian")){
            newBooking.setPrice(6 * newBooking.getSeats());
        }
        else{
            System.out.println("Pricing error");
        }
        newBooking.setStatus("Pending");
        newBooking.setCustomer(UserFacade.find(selfId));
        newBooking.setCustomerId(selfId);
        BookingFacade.addBooking(newBooking);
        
        newBooking = new Booking(); // Clear the form after adding a Booking
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId)); // Update the list of Users after adding a new one
    }
    
    public void updateBooking() {
        
        setSelectedBooking(BookingFacade.find(selectedBookingId));

        if (selectedBookingId != null && selectedBooking.getCustomerId().equals(selfId) && selectedBooking.getStatus().equals("Pending")) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);

                if (BookingToUpdate != null) {
                    // Update the User's properties using values from the UI
                    BookingToUpdate.setId(getSelectedBookingId());
                    
                    if (newBookingDate == null || newBookingDate.toString().trim().isEmpty()) {
                        newBookingDate = selectedBooking.getBookingDate();
                    }
                    if (newBookingSeats == null || newBookingSeats.toString().trim().isEmpty()) {
                        newBookingSeats = selectedBooking.getSeats();
                    }
                    if (newBookingFood == null || newBookingFood.trim().isEmpty()) {
                        newBookingFood = selectedBooking.getFood();
                    }
                    
                    BookingToUpdate.setBookingDate(newBookingDate);
                    BookingToUpdate.setSeats(newBookingSeats);
                    BookingToUpdate.setFood(newBookingFood);
                    
                    // Save the changes to the database
                    BookingFacade.updateBooking(BookingToUpdate);

                    // Refresh the list of Users
                    bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId));
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("Booking with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking ID is null, Booking belongs to a different customer, or booking has been approved.");
        }
    }
    
    public void deleteBooking() {
        checkBooking = BookingFacade.find(bookingIdInput);
        
        if (checkBooking.getStatus().equals("Pending")){
            BookingFacade.deleteBooking(bookingIdInput);
        }
        else {
            System.out.println("Booking cannot be deleted as it has been approved");
        }
        
        bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId)); // Update the list of Users after deletion
    }
    
    public void addRating() {
        
        setSelectedBooking(BookingFacade.find(selectedBookingId));

        if (selectedBookingId != null && selectedBooking.getCustomerId().equals(selfId) && selectedBooking.getStatus().equals("Paid")) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);

                if (BookingToUpdate != null) {
                    // Update the User's properties using values from the UI
                    BookingToUpdate.setId(getSelectedBookingId());
                    
                    BookingToUpdate.setRating(newRating);
                    BookingToUpdate.setReview(newReview);
                    BookingToUpdate.setStatus("Rated");
                    // Save the changes to the database
                    BookingFacade.updateBooking(BookingToUpdate);

                    // Refresh the list of Users
                    bookings = BookingFacade.getBookingsByCustomer(UserFacade.find(selfId));
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("Booking with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking ID is null, Booking belongs to a different customer, or booking has been approved.");
        }
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Integer> getSeatOptions() {
        return seatOptions;
    }

    public void setSeatOptions(List<Integer> seatOptions) {
        this.seatOptions = seatOptions;
    }

    public Booking getCheckBooking() {
        return checkBooking;
    }

    public void setCheckBooking(Booking checkBooking) {
        this.checkBooking = checkBooking;
    }

    public Date getNewBookingDate() {
        return newBookingDate;
    }

    public void setNewBookingDate(Date newBookingDate) {
        this.newBookingDate = newBookingDate;
    }

    public Long getNewBookingSeats() {
        return newBookingSeats;
    }

    public void setNewBookingSeats(Long newBookingSeats) {
        this.newBookingSeats = newBookingSeats;
    }

    public String getNewBookingFood() {
        return newBookingFood;
    }

    public void setNewBookingFood(String newBookingFood) {
        this.newBookingFood = newBookingFood;
    }

    public Long getNewRating() {
        return newRating;
    }

    public void setNewRating(Long newRating) {
        this.newRating = newRating;
    }

    public String getNewReview() {
        return newReview;
    }

    public void setNewReview(String newReview) {
        this.newReview = newReview;
    }
    
    
    
}
