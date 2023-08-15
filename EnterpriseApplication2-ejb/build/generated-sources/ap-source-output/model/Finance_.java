package model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-16T06:50:32")
@StaticMetamodel(Finance.class)
public class Finance_ { 

    public static volatile SingularAttribute<Finance, String> transactionType;
    public static volatile SingularAttribute<Finance, String> item;
    public static volatile SingularAttribute<Finance, Long> amount;
    public static volatile SingularAttribute<Finance, BigDecimal> balance;
    public static volatile SingularAttribute<Finance, Long> id;

}