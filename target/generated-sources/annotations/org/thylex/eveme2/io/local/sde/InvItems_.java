package org.thylex.eveme2.io.local.sde;

import org.thylex.eveme2.io.entities.sde.InvItems;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.thylex.eveme2.io.entities.sde.InvTypes;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-28T14:39:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(InvItems.class)
public class InvItems_ { 

    public static volatile SingularAttribute<InvItems, Long> itemID;
    public static volatile SingularAttribute<InvItems, InvTypes> invType;
    public static volatile SingularAttribute<InvItems, Long> quantity;
    public static volatile SingularAttribute<InvItems, Long> locationID;
    public static volatile SingularAttribute<InvItems, Long> flagID;
    public static volatile SingularAttribute<InvItems, Long> ownerID;

}