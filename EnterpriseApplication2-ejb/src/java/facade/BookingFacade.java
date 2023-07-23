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
import model.Booking;

/**
 *
 * @author Ryzen5
 */
@Stateless
public class BookingFacade {

    @PersistenceContext
    private EntityManager entityManager;

    public void addBooking(Booking booking) {
        entityManager.persist(booking);
    }

    public void updateBooking(Booking booking) {
        entityManager.merge(booking);
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = entityManager.find(Booking.class, bookingId);
        if (booking != null) {
            entityManager.remove(booking);
        }
    }

    public Booking getBookingById(Long bookingId) {
        return entityManager.find(Booking.class, bookingId);
    }
    
    public List<Booking> getAllBookings() {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking b", Booking.class);
        return query.getResultList();
    }

}
