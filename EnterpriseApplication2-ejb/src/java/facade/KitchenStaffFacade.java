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
import model.KitchenStaff;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class KitchenStaffFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addKitchenStaff(KitchenStaff kitchenStaff) {
        entityManager.persist(kitchenStaff);
    }

    public void updateKitchenStaff(KitchenStaff kitchenStaff) {
        entityManager.merge(kitchenStaff);
    }

    public void deleteKitchenStaff(Long staffId) {
        KitchenStaff kitchenStaff = entityManager.find(KitchenStaff.class, staffId);
        if (kitchenStaff != null) {
            entityManager.remove(kitchenStaff);
        }
    }

    public KitchenStaff getKitchenStaffById(Long staffId) {
        return entityManager.find(KitchenStaff.class, staffId);
    }
    
    public List<KitchenStaff> getAllKitchenStaff() {
        TypedQuery<KitchenStaff> query = entityManager.createQuery("SELECT ks FROM KitchenStaff ks", KitchenStaff.class);
        return query.getResultList();
    }


}

