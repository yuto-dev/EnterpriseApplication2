package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-31T22:58:02")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, Long> assignedKitchenStaffId;
    public static volatile SingularAttribute<Booking, User> assignedKitchenStaff;
    public static volatile SingularAttribute<Booking, String> review;
    public static volatile SingularAttribute<Booking, Long> rating;
    public static volatile SingularAttribute<Booking, Long> customerId;
    public static volatile SingularAttribute<Booking, Date> bookingDate;
    public static volatile SingularAttribute<Booking, Long> id;
    public static volatile SingularAttribute<Booking, Long> seats;
    public static volatile SingularAttribute<Booking, String> food;
    public static volatile SingularAttribute<Booking, String> status;
    public static volatile SingularAttribute<Booking, User> customer;

}