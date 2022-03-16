package org.thylex.eveme2.db.sde;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.thylex.eveme2.db.sde.InvGroups;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-09-19T17:29:20", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(InvCategories.class)
public class InvCategories_ { 

    public static volatile SingularAttribute<InvCategories, Integer> iconID;
    public static volatile ListAttribute<InvCategories, InvGroups> invGroups;
    public static volatile SingularAttribute<InvCategories, Integer> published;
    public static volatile SingularAttribute<InvCategories, String> categoryName;
    public static volatile SingularAttribute<InvCategories, Integer> categoryID;

}