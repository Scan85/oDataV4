package odataservice.odatav4.edm.enumeration;

import odataservice.odatav4.edm.annotation.EdmComplexType;
import odataservice.odatav4.edm.annotation.EdmEntityType;
import java.lang.reflect.Field;
import org.apache.commons.lang3.ClassUtils;

/**
 * @author Seyit Can
 * Defines the type of a value (see Valuable).
 */
public enum EdmValueType {
    /**
     * Primitive type
     */
    PRIMITIVE,
    /**
     * Enum type
     */
    ENUM,
    /**
     * Complex type
     */
    COMPLEX,
    /**
     * Entity type
     */
    ENTITY;


    public static EdmValueType valueOf(Field field) {
        Class<?> type = field.getType();
        EdmValueType edmValueType;

        if (type.isEnum()) {
            edmValueType = ENUM;
        } else if (ClassUtils.isPrimitiveOrWrapper(type)) {
            edmValueType = PRIMITIVE;
        } else if (type.isAnnotationPresent(EdmComplexType.class)) {
            edmValueType = COMPLEX;
        } else if (type.isAnnotationPresent(EdmEntityType.class)) {
            edmValueType = ENTITY;
        } else { // fallback for String, Date, etc.
            edmValueType = PRIMITIVE;
        }

        return edmValueType;
    }
}
