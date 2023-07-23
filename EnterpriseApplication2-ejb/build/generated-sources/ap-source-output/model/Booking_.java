package model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;
import model.KitchenStaff;
import model.Manager;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-23T15:05:54")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, LocalDate> date;
    public static volatile SingularAttribute<Booking, KitchenStaff> assignedKitchenStaff;
    public static volatile SingularAttribute<Booking, Manager> assignedManager;
    public static volatile SingularAttribute<Booking, LocalTime> time;
    public static volatile SingularAttribute<Booking, Long> bookingId;
    public static volatile SingularAttribute<Booking, String> status;
    public static volatile SingularAttribute<Booking, Customer> customer;

}