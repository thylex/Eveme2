package org.thylex.eveme2.db.dyn;

import org.thylex.eveme2.io.local.dyn.ItemPrice;
import java.text.DateFormat;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-19T17:29:20", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(ItemPrice.class)
public class ItemPrice_ { 

    public static volatile SingularAttribute<ItemPrice, Integer> itemID;
    public static volatile SingularAttribute<ItemPrice, DateFormat> checkedAt;
    public static volatile SingularAttribute<ItemPrice, Float> highSellPrice;
    public static volatile SingularAttribute<ItemPrice, Float> lowBuyPrice;
    public static volatile SingularAttribute<ItemPrice, Float> highBuyPrice;
    public static volatile SingularAttribute<ItemPrice, Float> lowSellPrice;

}