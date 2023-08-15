/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.User;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class UserFacade {

    @PersistenceContext(unitName = "EnterpriseApplication2-ejbPU")
    private EntityManager em;

    public void addUser(User user) {
        em.persist(user);
    }

    public void updateUser(User user) {
        em.merge(user);
    }

    public void deleteUser(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    public User find(Long id) {
        return em.find(User.class, id);
    }

    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    public User getUserByUsername(String username) {
        try {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
        } catch (NoResultException ex) {
        // If no user is found, return null or throw an exception as needed
        return null;
    }
        
    }
    
    public User getUserByEmail(String email) {
        try {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
        } catch (NoResultException ex) {
        // If no user is found, return null
        return null;
        }
    }
            
            
    public List<User> getUsersByRole(String userType) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userType = :userType");
        query.setParameter("userType", userType);
        return query.getResultList();
    }
    
    public List<User> getUserByRoleAndId(String userType, Long userId) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userType = :userType AND u.id = :userId", User.class);
        query.setParameter("userType", userType);
        query.setParameter("userId", userId);
    
        try {
            return query.getResultList();
        } catch (NoResultException ex) {
            return null; // Return null if no result is found
        }
    }
    
    public User userLogin(String username, String password) {
        System.out.println("1");
        System.out.println(username);
        System.out.println(password);
        try {
            System.out.println("2");
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("3");
            return null; // Return null if no user with the given credentials is found
        }
    }

    // Add other methods as needed for user management, such as findByUsername, findByEmail, etc.
    
    public void deleteAllUsers() {
    List<User> users = em.createQuery("SELECT u FROM User u", User.class)
                        .getResultList();

    for (User user : users) {
        em.remove(user);
    }
}
    
    public void deleteAllManagers() {
        List<User> managers = em.createQuery("SELECT u FROM User u WHERE u.userType = :userType", User.class)
                           .setParameter("userType", "M")
                           .getResultList();

    for (User manager : managers) {
        em.remove(manager);
    }
}
}
