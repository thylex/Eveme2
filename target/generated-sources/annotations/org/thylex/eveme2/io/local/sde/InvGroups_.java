package org.thylex.eveme2.io.local.sde;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.thylex.eveme2.io.local.sde.InvCategories;
import org.thylex.eveme2.io.local.sde.InvTypes;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-21T14:13:37", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(InvGroups.class)
public class InvGroups_ { 

    public static volatile SingularAttribute<InvGroups, Integer> iconID;
    public static volatile SingularAttribute<InvGroups, String> groupName;
    public static volatile ListAttribute<InvGroups, InvTypes> invTypes;
    public static volatile SingularAttribute<InvGroups, Integer> fittableNonSingleton;
    public static volatile SingularAttribute<InvGroups, Integer> groupID;
    public static volatile SingularAttribute<InvGroups, Integer> anchored;
    public static volatile SingularAttribute<InvGroups, Integer> anchorable;
    public static volatile SingularAttribute<InvGroups, InvCategories> invCategory;
    public static volatile SingularAttribute<InvGroups, Integer> published;
    public static volatile SingularAttribute<InvGroups, Integer> useBasePrice;

}