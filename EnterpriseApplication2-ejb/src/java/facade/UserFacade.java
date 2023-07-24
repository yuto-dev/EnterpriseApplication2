/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

    public List<User> getUsersByRole(String userType) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.userType = :userType");
        query.setParameter("userType", userType);
        return query.getResultList();
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
