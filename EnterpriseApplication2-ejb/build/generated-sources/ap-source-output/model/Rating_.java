package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Customer;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T06:50:32")
@StaticMetamodel(Rating.class)
public class Rating_ { 

    public static volatile SingularAttribute<Rating, Long> ratingId;
    public static volatile SingularAttribute<Rating, Integer> rating;
    public static volatile SingularAttribute<Rating, String> comment;
    public static volatile SingularAttribute<Rating, Customer> customer;

}