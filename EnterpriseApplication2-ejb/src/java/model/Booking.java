/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ryzen5
 */
@Entity
@Table(name = "bookings")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    
    private String status;
    @Column(name = "FOOD")
    private String food;
    @Column(name = "SEATS")
    private Long seats;
    @Column(name = "RATING")
    private Long rating;
    @Column(name = "REVIEW")
    private String review;
    
    private Long price;
    
    private Long assignedKitchenStaffId;
    private Long customerId;

    @JoinColumn(name = "ASSIGNEDKITCHENSTAFF_ID")
    @ManyToOne
    private User assignedKitchenStaff;
    
    @JoinColumn(name = "CUSTOMER_ID")
    @ManyToOne
    private User customer;
    
    //private Long rating;
    //private String feedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getAssignedKitchenStaff() {
        return assignedKitchenStaff;
    }

    public void setAssignedKitchenStaff(User assignedKitchenStaff) {
        this.assignedKitchenStaff = assignedKitchenStaff;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Long getAssignedKitchenStaffId() {
        return assignedKitchenStaffId;
    }

   public void setAssignedKitchenStaffId(Long assignedKitchenStaffId) {
        this.assignedKitchenStaffId = assignedKitchenStaffId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Long getSeats() {
        return seats;
    }

    public void setSeats(Long seats) {
        this.seats = seats;
    }
    
    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.booking[ bookingId=" + id + " ]";
    }
    
}
