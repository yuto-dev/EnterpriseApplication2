/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.BookingFacade;
import facade.FinanceFacade;
import facade.StockFacade;
import facade.UserFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import static jdk.nashorn.internal.runtime.JSType.toLong;
import model.Booking;
import model.Finance;
import model.Stock;
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
    
    @EJB
    private BookingFacade BookingFacade;
    
    @EJB
    private StockFacade StockFacade;
    
    @EJB
    private FinanceFacade FinanceFacade;
    
    @Inject
    private UserSessionBean UserSessionBean;
    
    private Finance initFinance;
    private Stock initStock;
    
    private User newManager;
    private List<User> managers;
    
    private User newKitchenStaff;
    private List<User> kitchenStaffs;
    
    private User newCustomer;
    private List<User> customers;
    
    private Booking newBooking;
    private List<Booking> bookings;
    
    private Long searchKitchenStaffId;
    private User searchKitchenStaff;
    
    private Long searchCustomerId;
    private User searchCustomer;
    
    private Long managerIdInput; //delete ID
    private User selectedManager; //current User
    
    private Long kitchenStaffIdInput; //delete ID
    private User selectedKitchenStaff;
    
    private Long customerIdInput;
    private User selectedCustomer;
    
    private Booking selectedBooking;
    
    private Long selectedManagerId;
    private Long selectedKitchenStaffId;
    private Long selectedCustomerId;
    private Long selectedBookingId;
    
    private String newFirstName;
    private String newLastName;
    private String newUsername;
    private String newPassword;
    private String newEmail;
    private String newPhoneNumber;
    
    private Long assignedStaffId;
    private User checkStaff;
    
    private List<Booking> ratedBookings;
    private Stock stocks;
    private List<Finance> finances;
    
    private BigDecimal currentBalance;
    private Long restockChicken;
    private Long restockBeef;
    private Long restockVegetarian;
    
    // TEST VARIABLES, DELETE LATER
    private int navigation;
    private Long testId;

    @PostConstruct
    public void init() {
        newManager = new User();
        managers = UserFacade.getUsersByRole("M"); // Calling the method to retrieve all Managers
        
        newKitchenStaff = new User(); // new instance for creation
        kitchenStaffs = UserFacade.getUsersByRole("S");
        
        newCustomer = new User();
        customers = UserFacade.getUsersByRole("C");
        
        newBooking = new Booking();
        bookings = BookingFacade.getAllBookings();
        
        selectedManager = new User();
        selectedManagerId = null;
        
        selectedKitchenStaff = new User();
        selectedKitchenStaffId = null;
        
        selectedCustomer = new User();
        selectedCustomerId = null;
        
        selectedBooking = new Booking();
        selectedBookingId = null;
        
        newFirstName = null;
        newLastName = null;
        newUsername = null;
        newPassword = null;
        newEmail = null;
        newPhoneNumber = null;
        
        navigation = 2;
        
        assignedStaffId = null;
        checkStaff = null;
        
        ratedBookings = BookingFacade.getRatedBookings();
        stocks = StockFacade.getStock();
        finances = FinanceFacade.getAllFinance();
        
        if (FinanceFacade.getMostRecentFinance() != null){
            currentBalance = FinanceFacade.getMostRecentFinance().getBalance();
        }
        
        restockChicken = null;
        restockBeef = null;
        restockVegetarian = null;
        
        initStock = new Stock();
        initFinance = new Finance();
        
    }

    // TODO CHANGE GETALLUSERS BACK TO GETROLE
    /*
    public String testNavigation() {
        System.out.println("Entering testNavigation() method");
        if (navigation == 1) {
            System.out.println("Condition: navigation == 1");
            return "pageOne";
        } else {
            System.out.println("Condition: navigation != 1");
            return "pageTwo";
        }
    }
 */
    
    public void initCafeteria(){
        
        Long initStockValue = toLong(50);
        
        initFinance.setId(toLong(1));
        initFinance.setTransactionType("IN");
        initFinance.setItem("Init");
        initFinance.setAmount(toLong(200));
        initFinance.setBalance(BigDecimal.valueOf(200));
        
        StockFacade.createStock(initStockValue, initStockValue, initStockValue);
        FinanceFacade.addFinance(initFinance);
        
    }
    
    // Phone Number Generator and Format
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

    
    // 
    // MANAGER
    // CRUD
    //
    
    public void addManager() {

        newManager.setUserType("M");

        if (newManager.getEmail() == null || newManager.getEmail().trim().isEmpty()) {
            newManager.setEmail(newManager.getUsername()+"@mail.com");
        }
        
        if (newManager.getPhoneNumber()== null || newManager.getPhoneNumber().trim().isEmpty()) {
            String generatedPhoneNumber = generatePhoneNumber();
            newManager.setPhoneNumber(formatPhoneNumber(generatedPhoneNumber));
        }
        
        UserFacade.addUser(newManager);
        newManager = new User(); // Clear the form after adding a User
        managers = UserFacade.getUsersByRole("M"); // Update the list of Users after adding a new one
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
                    UserToUpdate.setPhoneNumber(formatPhoneNumber(newPhoneNumber));

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
        UserFacade.deleteUser(managerIdInput);
        managers = UserFacade.getUsersByRole("M"); // Update the list of Users after deletion
    }
    
    public void deleteAllManagers() {
        UserFacade.deleteAllManagers();
    }

    public User getManagerById(Long UserId) {
        return UserFacade.find(UserId);
    }
    
    // 
    // KITCHEN
    // STAFF
    // CRUD
    //
    
    public void addKitchenStaff() {
        
        newKitchenStaff.setUserType("S");
        newKitchenStaff.setCompletedBooking(toLong(0));
        newKitchenStaff.setRating(0);
        if (newKitchenStaff.getEmail() == null || newKitchenStaff.getEmail().trim().isEmpty()) {
            newKitchenStaff.setEmail(newKitchenStaff.getUsername()+"@mail.com");
        }
    
        if (newKitchenStaff.getPhoneNumber()== null || newKitchenStaff.getPhoneNumber().trim().isEmpty()) {
            String generatedPhoneNumber = generatePhoneNumber();
            newKitchenStaff.setPhoneNumber(formatPhoneNumber(generatedPhoneNumber));
        }
        
        User checkUsername = UserFacade.getUserByUsername(newKitchenStaff.getUsername());
        User checkEmail = UserFacade.getUserByEmail(newKitchenStaff.getEmail());
        
        if (checkUsername == null && checkEmail == null){
            UserFacade.addUser(newKitchenStaff);
        }
        else {
            System.out.println("Username or email has been taken.");
        }
        
        newKitchenStaff = new User(); // Clear the form after adding a User
        kitchenStaffs = UserFacade.getUsersByRole("S"); // Update the list of Users after adding a new one
    }
    
    public void searchKitchenStaff(){
        System.out.println("1");
        if (searchKitchenStaffId != null){
            System.out.println("2");
            kitchenStaffs = null;
            System.out.println("4");
            kitchenStaffs = UserFacade.getUserByRoleAndId("S", searchKitchenStaffId);
            System.out.println("5");
        }
        else{
            System.out.println("Kitchen Staff not found");
        }
    }
    
    public void resetKitchenStaff(){
        kitchenStaffs = UserFacade.getUsersByRole("S");
    }
    
    public void updateKitchenStaff() {
        
        setSelectedKitchenStaff(UserFacade.find(selectedKitchenStaffId));

        if (selectedKitchenStaffId != null && selectedKitchenStaff.getUserType().equals("S")) {
            User UserToUpdate = UserFacade.find(selectedKitchenStaffId);

                if (UserToUpdate != null) {
                    // Update the User's properties using values from the UI
                    UserToUpdate.setId(getSelectedKitchenStaffId());
                    
                    if (newFirstName == null || newFirstName.trim().isEmpty()) {
                        newFirstName = selectedKitchenStaff.getFirstName();
                    }
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        newLastName = selectedKitchenStaff.getLastName();
                    }
                    if (newUsername == null || newUsername.trim().isEmpty()) {
                        newUsername = selectedKitchenStaff.getUsername();
                    }
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        newPassword = selectedKitchenStaff.getPassword();
                    }
                    if (newEmail == null || newEmail.trim().isEmpty()) {
                        newEmail = selectedKitchenStaff.getEmail();
                    }
                    if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
                        newPhoneNumber = selectedKitchenStaff.getPhoneNumber();
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
                    kitchenStaffs = UserFacade.getUsersByRole("S");
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("User with ID " + selectedKitchenStaffId + " not found!");
                }
        } else {
            System.out.println("Selected User ID is null");
        }
    }
    
    public void deleteKitchenStaff() {
        UserFacade.deleteUser(kitchenStaffIdInput);
        kitchenStaffs = UserFacade.getUsersByRole("S"); // Update the list of Users after deletion
    }
    
    //
    // CUSTOMER
    // CRUD
    //
        
    public void addCustomer() {
        
        newCustomer.setUserType("C");
        if (newCustomer.getEmail() == null || newCustomer.getEmail().trim().isEmpty()) {
            newCustomer.setEmail(newCustomer.getUsername()+"@mail.com");
        }
    
        if (newCustomer.getPhoneNumber()== null || newCustomer.getPhoneNumber().trim().isEmpty()) {
            String generatedPhoneNumber = generatePhoneNumber();
            newCustomer.setPhoneNumber(formatPhoneNumber(generatedPhoneNumber));
        }
        
        User checkUsername = UserFacade.getUserByUsername(newCustomer.getUsername());
        User checkEmail = UserFacade.getUserByEmail(newCustomer.getEmail());
        
        if (checkUsername == null && checkEmail == null){
            UserFacade.addUser(newCustomer);
        }
        else {
            System.out.println("Username or email has been taken.");
        }
        newCustomer = new User(); // Clear the form after adding a User
        customers = UserFacade.getUsersByRole("C"); // Update the list of Users after adding a new one
    }
    
    public void searchCustomer(){

        if (searchCustomerId != null){
            System.out.println("2");
            customers = null;
            System.out.println("4");
            customers = UserFacade.getUserByRoleAndId("C", searchCustomerId);
            System.out.println("5");
        }
        else{
            System.out.println("Kitchen Staff not found");
        }
    }
    
    public void resetCustomer(){
        customers = UserFacade.getUsersByRole("C");
    }
    
    public void updateCustomer() {
        
        setSelectedCustomer(UserFacade.find(selectedCustomerId));

        if (selectedCustomerId != null && selectedCustomer.getUserType().equals("C")) {
            User UserToUpdate = UserFacade.find(selectedCustomerId);

                if (UserToUpdate != null) {
                    // Update the User's properties using values from the UI
                    UserToUpdate.setId(getSelectedCustomerId());
                    
                    if (newFirstName == null || newFirstName.trim().isEmpty()) {
                        newFirstName = selectedCustomer.getFirstName();
                    }
                    if (newLastName == null || newLastName.trim().isEmpty()) {
                        newLastName = selectedCustomer.getLastName();
                    }
                    if (newUsername == null || newUsername.trim().isEmpty()) {
                        newUsername = selectedCustomer.getUsername();
                    }
                    if (newPassword == null || newPassword.trim().isEmpty()) {
                        newPassword = selectedCustomer.getPassword();
                    }
                    if (newEmail == null || newEmail.trim().isEmpty()) {
                        newEmail = selectedCustomer.getEmail();
                    }
                    if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
                        newPhoneNumber = selectedCustomer.getPhoneNumber();
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
                    customers = UserFacade.getUsersByRole("C");
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("User with ID " + selectedCustomerId + " not found!");
                }
        } else {
            System.out.println("Selected User ID is null");
        }
    }
    
    public void deleteCustomer() {
        UserFacade.deleteUser(customerIdInput);
        customers = UserFacade.getUsersByRole("C"); // Update the list of Users after deletion
    }
    
    //
    // BOOKING
    // MANAGEMENT
    //
    
    public void assignBooking(){
        
        setSelectedBooking(BookingFacade.find(selectedBookingId));
        checkStaff = UserFacade.find(assignedStaffId);
        
        if (selectedBookingId != null && selectedBooking.getStatus().equals("Pending") && checkStaff != null && checkStaff.getUserType().equals("S")) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);
            User StaffToAssign = UserFacade.find(assignedStaffId);

                if (BookingToUpdate != null && StaffToAssign != null) {
                    // Update the User's properties using values from the UI
                    
                    BookingToUpdate.setId(getSelectedBookingId());
                    BookingToUpdate.setStatus("Approved");
                    BookingToUpdate.setAssignedKitchenStaff(StaffToAssign);
                    BookingToUpdate.setAssignedKitchenStaffId(assignedStaffId);
                    

                    // Save the changes to the database
                    BookingFacade.updateBooking(BookingToUpdate);

                    // Refresh the list of Users
                    bookings = BookingFacade.getAllBookings();
                } else {
                    // Handle the case when the User with the selected ID is not found
                    System.out.println("Booking/Staff with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking/Staff ID is null, booking has been assigned, or the assigned user is not a staff.");
        }
    }
    
    public void collectPayment(Booking booking) {
        setSelectedBooking(booking);
        Finance newFinance = new Finance();
        Finance prevFinance = FinanceFacade.getMostRecentFinance();
        
        
        selectedBookingId = booking.getId();
        if (selectedBookingId != null) {
            Booking BookingToUpdate = BookingFacade.find(selectedBookingId);

                if (BookingToUpdate != null) {
                    
                    if (BookingToUpdate.getStatus().equals("Fulfilled")){
                        
                        newFinance.setTransactionType("IN");
                        newFinance.setItem(BookingToUpdate.getFood());
                        newFinance.setAmount(BookingToUpdate.getPrice());
                        newFinance.setBalance(prevFinance.getBalance().add(BigDecimal.valueOf(newFinance.getAmount())));
                        
                        BookingToUpdate.setStatus("Paid");
                    
                        // Save the changes to the database
                        BookingFacade.updateBooking(BookingToUpdate);
                        FinanceFacade.addFinance(newFinance);

                        // Refresh the list of Bookings
                        bookings = BookingFacade.getAllBookings();
                    }
                    else {
                        System.out.println("Booking is not yet fulfilled");
                    }
   
                } else {
                    // Handle the case when the Booking with the selected ID is not found
                    System.out.println("Booking with ID " + selectedBookingId + " not found!");
                }
        } else {
            System.out.println("Selected Booking ID is null");
        }
    }
    
    public void restock(){
        
        stocks = StockFacade.getStock();
        
        if (restockChicken != null){
            Finance newFinance = new Finance();
            Finance prevFinance = FinanceFacade.getMostRecentFinance();
            
            stocks.setChicken(stocks.getChicken() + restockChicken);
            newFinance.setTransactionType("OUT");
            newFinance.setItem("Chicken");
            newFinance.setAmount(6 * restockChicken);
            newFinance.setBalance(prevFinance.getBalance().subtract(BigDecimal.valueOf(newFinance.getAmount())));
            FinanceFacade.addFinance(newFinance);
        }
        if (restockBeef != null){
            Finance newFinance = new Finance();
            Finance prevFinance = FinanceFacade.getMostRecentFinance();
            
            stocks.setBeef(stocks.getBeef() + restockBeef);
            newFinance.setTransactionType("OUT");
            newFinance.setItem("Beef");
            newFinance.setAmount(8 * restockBeef);
            newFinance.setBalance(prevFinance.getBalance().subtract(BigDecimal.valueOf(newFinance.getAmount())));
            
            FinanceFacade.addFinance(newFinance);
        }
        if (restockVegetarian != null){
            Finance newFinance = new Finance();
            Finance prevFinance = FinanceFacade.getMostRecentFinance();
            
            stocks.setVegetarian(stocks.getVegetarian() + restockVegetarian);
            newFinance.setTransactionType("OUT");
            newFinance.setItem("Vegetarian");
            newFinance.setAmount(4 * restockVegetarian);
            newFinance.setBalance(prevFinance.getBalance().subtract(BigDecimal.valueOf(newFinance.getAmount())));
            
            FinanceFacade.addFinance(newFinance);
        }
        
        
        StockFacade.updateStock(stocks);
    }

// Getters and setters

    public User getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(User selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public Long getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    
    
    public Long getCustomerIdInput() {
        return customerIdInput;
    }

    public void setCustomerIdInput(Long customerIdInput) {
        this.customerIdInput = customerIdInput;
    }

    public User getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(User newCustomer) {
        this.newCustomer = newCustomer;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomers(List<User> customers) {
        this.customers = customers;
    }

    
    
    public User getSelectedKitchenStaff() {
        return selectedKitchenStaff;
    }

    public void setSelectedKitchenStaff(User selectedKitchenStaff) {
        this.selectedKitchenStaff = selectedKitchenStaff;
    }

    public Long getSelectedKitchenStaffId() {
        return selectedKitchenStaffId;
    }

    public void setSelectedKitchenStaffId(Long selectedKitchenStaffId) {
        this.selectedKitchenStaffId = selectedKitchenStaffId;
    }
    
    

    public Long getKitchenStaffIdInput() {
        return kitchenStaffIdInput;
    }

    public void setKitchenStaffIdInput(Long kitchenStaffIdInput) {
        this.kitchenStaffIdInput = kitchenStaffIdInput;
    }

    
    
    public User getNewKitchenStaff() {
        return newKitchenStaff;
    }

    public void setNewKitchenStaff(User newKitchenStaff) {    
        this.newKitchenStaff = newKitchenStaff;
    }

    public List<User> getKitchenStaffs() {
        return kitchenStaffs;
    }
 
    public void setKitchenStaffs(List<User> kitchenStaffs) {
        this.kitchenStaffs = kitchenStaffs;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
    
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
        return managerIdInput;
    }

    public void setManagerIdInput(Long ManagerIdInput) {
        this.managerIdInput = ManagerIdInput;
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

    public int getNavigation() {
        return navigation;
    }

    public void setNavigation(int navigation) {
        this.navigation = navigation;
    }

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

    public Long getAssignedStaffId() {
        return assignedStaffId;
    }

    public void setAssignedStaffId(Long assignedStaffId) {
        this.assignedStaffId = assignedStaffId;
    }

    public List<Booking> getRatedBookings() {
        return ratedBookings;
    }

    public void setRatedBookings(List<Booking> ratedBookings) {
        this.ratedBookings = ratedBookings;
    }

    public User getCheckStaff() {
        return checkStaff;
    }

    public void setCheckStaff(User checkStaff) {
        this.checkStaff = checkStaff;
    }

    public Finance getInitFinance() {
        return initFinance;
    }

    public void setInitFinance(Finance initFinance) {
        this.initFinance = initFinance;
    }

    public Stock getInitStock() {
        return initStock;
    }

    public void setInitStock(Stock initStock) {
        this.initStock = initStock;
    }

    public Stock getStocks() {
        return stocks;
    }

    public void setStocks(Stock stocks) {
        this.stocks = stocks;
    }

    public List<Finance> getFinances() {
        return finances;
    }

    public void setFinances(List<Finance> finances) {
        this.finances = finances;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Long getRestockChicken() {
        return restockChicken;
    }

    public void setRestockChicken(Long restockChicken) {
        this.restockChicken = restockChicken;
    }

    public Long getRestockBeef() {
        return restockBeef;
    }

    public void setRestockBeef(Long restockBeef) {
        this.restockBeef = restockBeef;
    }

    public Long getRestockVegetarian() {
        return restockVegetarian;
    }

    public void setRestockVegetarian(Long restockVegetarian) {
        this.restockVegetarian = restockVegetarian;
    }

    public Long getSearchKitchenStaffId() {
        return searchKitchenStaffId;
    }

    public void setSearchKitchenStaffId(Long searchKitchenStaffId) {
        this.searchKitchenStaffId = searchKitchenStaffId;
    }

    public User getSearchKitchenStaff() {
        return searchKitchenStaff;
    }

    public void setSearchKitchenStaff(User searchKitchenStaff) {
        this.searchKitchenStaff = searchKitchenStaff;
    }

    public Long getSearchCustomerId() {
        return searchCustomerId;
    }

    public void setSearchCustomerId(Long searchCustomerId) {
        this.searchCustomerId = searchCustomerId;
    }

    public User getSearchCustomer() {
        return searchCustomer;
    }

    public void setSearchCustomer(User searchCustomer) {
        this.searchCustomer = searchCustomer;
    }
    
    
}
