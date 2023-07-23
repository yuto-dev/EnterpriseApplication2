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
import model.Rating;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class RatingFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addRating(Rating rating) {
        entityManager.persist(rating);
    }

    public void updateRating(Rating rating) {
        entityManager.merge(rating);
    }

    public void deleteRating(Long ratingId) {
        Rating rating = entityManager.find(Rating.class, ratingId);
        if (rating != null) {
            entityManager.remove(rating);
        }
    }

    public Rating getRatingById(Long ratingId) {
        return entityManager.find(Rating.class, ratingId);
    }
    
    public List<Rating> getAllRatings() {
        TypedQuery<Rating> query = entityManager.createQuery("SELECT r FROM Rating r", Rating.class);
        return query.getResultList();
    }

}

