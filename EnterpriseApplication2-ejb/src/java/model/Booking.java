/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ryzen5
 */
@Entity
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private LocalDate date;
    private LocalTime time;
    private String status;

    @ManyToOne
    private Manager assignedManager;

    @ManyToOne
    private KitchenStaff assignedKitchenStaff;

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return bookingId;
    }

    public void setId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Manager getAssignedManager() {
        return assignedManager;
    }

    public void setAssignedManager(Manager assignedManager) {
        this.assignedManager = assignedManager;
    }

    public KitchenStaff getAssignedKitchenStaff() {
        return assignedKitchenStaff;
    }

    public void setAssignedKitchenStaff(KitchenStaff assignedKitchenStaff) {
        this.assignedKitchenStaff = assignedKitchenStaff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.booking[ bookingId=" + bookingId + " ]";
    }
    
}
