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
import javax.persistence.TypedQuery;
import model.Manager;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class ManagerFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addManager(Manager manager) {
        entityManager.persist(manager);
    }

    public void updateManager(Manager manager) {
        System.out.println("facade");
        entityManager.merge(manager);
    }

    public void deleteManager(Long managerId) {
        Manager manager = entityManager.find(Manager.class, managerId);
        if (manager != null) {
            entityManager.remove(manager);
        }
    }
    
    public Manager find(long managerId) {
        return entityManager.find(Manager.class, managerId);
    }

    // Custom remove method to delete a manager
    public void remove(Manager manager) {
        entityManager.remove(entityManager.merge(manager));
    }

    public Manager getManagerById(Long managerId) {
        return entityManager.find(Manager.class, managerId);
    }
    
    public List<Manager> getAllManagers() {
        TypedQuery<Manager> query = entityManager.createQuery("SELECT m FROM Manager m", Manager.class);
        return query.getResultList();
    }
    
    public void deleteAllManagers() {
        List<Manager> managers = entityManager.createQuery("SELECT m FROM Manager m", Manager.class)
                                 .getResultList();

        for (Manager manager : managers) {
            entityManager.remove(manager);
        }
    }

}
