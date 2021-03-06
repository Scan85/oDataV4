package odataservice.odatav4.edm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Seyit Can
 * Annotation for definition of an EdmProperty for an EdmEntityType or
 * EdmComplexType which contains the EdmProperty as a field.
 * <p>
 * The EdmProperty annotation has to be used on a field within a EdmEntityType
 * or EdmComplexType annotated class.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EdmProperty {

    /**
     * Define the name for the Property. If not set a default value has to be
     * generated by the EDM provider.
     *
     * @return name for the Property
     */
    String name() default "";

    /**
     * Define the EdmFacets for the Property in the EDM. If not set the default
     * EdmFacet values are used (see {@link EdmFacets}).
     *
     * @return facets for the Property as used in the EDM
     */
    EdmFacets facets() default @EdmFacets;
}
