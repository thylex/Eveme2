package org.thylex.eveme2.io.local.sde;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.thylex.eveme2.io.local.sde.InvGroups;
import org.thylex.eveme2.io.local.sde.InvItems;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-21T14:13:37", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(InvTypes.class)
public class InvTypes_ { 

    public static volatile SingularAttribute<InvTypes, Integer> iconID;
    public static volatile SingularAttribute<InvTypes, Integer> raceID;
    public static volatile SingularAttribute<InvTypes, Float> mass;
    public static volatile SingularAttribute<InvTypes, String> typeName;
    public static volatile SingularAttribute<InvTypes, String> description;
    public static volatile SingularAttribute<InvTypes, Boolean> published;
    public static volatile SingularAttribute<InvTypes, InvGroups> invGroup;
    public static volatile SingularAttribute<InvTypes, Float> capacity;
    public static volatile ListAttribute<InvTypes, InvItems> invItems;
    public static volatile SingularAttribute<InvTypes, Float> volume;
    public static volatile SingularAttribute<InvTypes, Integer> soundID;
    public static volatile SingularAttribute<InvTypes, Integer> graphicID;
    public static volatile SingularAttribute<InvTypes, Integer> typeID;
    public static volatile SingularAttribute<InvTypes, Integer> portionSize;
    public static volatile SingularAttribute<InvTypes, Float> basePrice;
    public static volatile SingularAttribute<InvTypes, Integer> marketGroupID;

}