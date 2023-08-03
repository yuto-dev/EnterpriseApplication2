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
import javax.persistence.TypedQuery;
import model.Booking;
import model.User;


/**
 *
 * @author Ryzen5
 */
@Stateless
public class BookingFacade {

    @PersistenceContext(unitName = "EnterpriseApplication2-ejbPU")
    private EntityManager em;

    public void addBooking(Booking booking) {
        em.persist(booking);
    }

    public void updateBooking(Booking booking) {
        em.merge(booking);
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = em.find(Booking.class, bookingId);
        if (booking != null) {
            em.remove(booking);
        }
    }
    
    public Booking find(Long id) {
        return em.find(Booking.class, id);
    }

    public Booking getBookingById(Long bookingId) {
        return em.find(Booking.class, bookingId);
    }
   
    public List<Booking> getBookingsByKitchenStaff(User assignedKitchenStaff) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.assignedKitchenStaff = :assignedKitchenStaff", Booking.class);
        query.setParameter("assignedKitchenStaff", assignedKitchenStaff);
        return query.getResultList();
    }
    
    public List<Booking> getKitchenStaffRatedBookings(User assignedKitchenStaff) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.assignedKitchenStaff = :assignedKitchenStaff AND b.rating IS NOT NULL", Booking.class);
        query.setParameter("assignedKitchenStaff", assignedKitchenStaff);
        return query.getResultList();
    }

    public List<Booking> getRatedBookings() {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.status = 'Rated'", Booking.class);
        return query.getResultList();
    }
    
    public List<Booking> getBookingsByCustomer(User customer) {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b WHERE b.customer = :customer", Booking.class);
        query.setParameter("customer", customer);
        return query.getResultList();
    }
  
    
    public List<Booking> getBookingByCustomerId(Long customerId) {
        Query query = em.createQuery("SELECT b FROM Booking b WHERE b.customerId = :customerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    public List<Booking> getAllBookings() {
        TypedQuery<Booking> query = em.createQuery("SELECT b FROM Booking b", Booking.class);
        return query.getResultList();
    }

}
