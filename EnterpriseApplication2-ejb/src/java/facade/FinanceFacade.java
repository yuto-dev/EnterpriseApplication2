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
import model.Finance;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class FinanceFacade {

    @PersistenceContext(unitName = "EnterpriseApplication2-ejbPU")
    private EntityManager em;

    public void addFinance(Finance finance) {
        em.persist(finance);
    }

    public void updateFinance(Finance finance) {
        em.merge(finance);
    }

    public void deleteFinance(Finance finance) {
        em.remove(em.merge(finance));
    }

    public Finance findFinance(Long id) {
        return em.find(Finance.class, id);
    }

    public List<Finance> getAllFinance() {
        return em.createQuery("SELECT f FROM Finance f", Finance.class)
                .getResultList();
    }
    
    public Finance getMostRecentFinance() {
        List<Finance> results = em.createQuery("SELECT f FROM Finance f ORDER BY f.id DESC", Finance.class)
                .setMaxResults(1)
                .getResultList();

        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null; // Return null if there are no entries in the Finance table
        }
    }
}