/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Stock;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class StockFacade {

    @PersistenceContext(unitName = "EnterpriseApplication2-ejbPU")
    private EntityManager em;

    public void createStock(Long chicken, Long beef, Long vegetarian) {
        Stock stock = new Stock();
        stock.setChicken(chicken);
        stock.setBeef(beef);
        stock.setVegetarian(vegetarian);
        em.persist(stock);
    }

    public Stock getStock() {
        TypedQuery<Stock> query = em.createQuery("SELECT s FROM Stock s", Stock.class);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            // Handle the case when no entity is found with the given ID
            return null;
        }
    }

    public void updateStock(Stock stock) {
        em.merge(stock);
    }
}






