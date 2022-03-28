package org.thylex.eveme2.io.local.sde;

import org.thylex.eveme2.io.entities.sde.IndustryActivityMaterials;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.thylex.eveme2.io.entities.sde.InvTypes;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-28T14:39:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(IndustryActivityMaterials.class)
public class IndustryActivityMaterials_ { 

    public static volatile SingularAttribute<IndustryActivityMaterials, Integer> activityID;
    public static volatile SingularAttribute<IndustryActivityMaterials, Integer> indActMatID;
    public static volatile SingularAttribute<IndustryActivityMaterials, Integer> quantity;
    public static volatile SingularAttribute<IndustryActivityMaterials, InvTypes> material;
    public static volatile SingularAttribute<IndustryActivityMaterials, Integer> typeID;

}