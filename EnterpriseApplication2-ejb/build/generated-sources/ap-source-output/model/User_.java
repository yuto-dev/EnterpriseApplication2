package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Booking;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T06:42:53")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phoneNumber;
    public static volatile SingularAttribute<User, Long> completedBooking;
    public static volatile SingularAttribute<User, Double> rating;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> userType;
    public static volatile ListAttribute<User, Booking> bookings;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}