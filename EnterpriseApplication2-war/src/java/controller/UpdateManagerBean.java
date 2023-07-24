/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Ryzen5
 */
@Named
@ViewScoped
public class UpdateManagerBean {

    @EJB
    private UserFacade UserFacade;

    private User newManager;
    private List<User> managers;
    
    private Long ManagerIdInput; //delete ID
    private User selectedManager; //current User
    
    private Long selectedManagerId;
    private String newFirstName;
    private String newLastName;

    @PostConstruct
    public void init() {
        newManager = new User();
        selectedManager = new User();
        
        selectedManagerId = null;
        newFirstName = null;
        newLastName = null;
        
        managers = UserFacade.getAllUsers(); // Calling the method to retrieve all Managers
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
                    UserToUpdate.setFirstName(newFirstName);
                    UserToUpdate.setLastName(newLastName);

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
