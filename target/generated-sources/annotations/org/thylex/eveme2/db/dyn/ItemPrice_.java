package org.thylex.eveme2.db.dyn;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-06-03T23:20:30", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(ItemPrice.class)
public class ItemPrice_ { 

    public static volatile SingularAttribute<ItemPrice, Long> itemID;
    public static volatile SingularAttribute<ItemPrice, Date> checkedAt;
    public static volatile SingularAttribute<ItemPrice, Double> price;

}