package org.thylex.eveme2.io.local.sde;

import org.thylex.eveme2.io.entities.sde.IndustryActivityProbabilities;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-03-28T14:39:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(IndustryActivityProbabilities.class)
public class IndustryActivityProbabilities_ { 

    public static volatile SingularAttribute<IndustryActivityProbabilities, Integer> activityID;
    public static volatile SingularAttribute<IndustryActivityProbabilities, Integer> indActProbID;
    public static volatile SingularAttribute<IndustryActivityProbabilities, Double> probability;
    public static volatile SingularAttribute<IndustryActivityProbabilities, Integer> typeID;
    public static volatile SingularAttribute<IndustryActivityProbabilities, Integer> productTypeID;

}