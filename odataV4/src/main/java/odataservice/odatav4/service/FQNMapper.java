package odataservice.odatav4.service;

import odataservice.odatav4.service.EntityMetaData;
import odataservice.odatav4.service.EntityMetaDataContainer;
import odataservice.odatav4.service.EntityMetaProperty;
import odataservice.odatav4.service.EntityMetaPropertyComplex;
import odataservice.odatav4.service.EntityMetaPropertyEntity;
import odataservice.odatav4.service.EntityMetaPropertyEnum;
import odataservice.odatav4.service.EntityMetaPropertyPrimitve;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

/**
 *
 * @author Seyit Can
 */
public class FQNMapper {

    public static FullQualifiedName createFullQualifiedName(String serviceNamespace, String name) {
        return new FullQualifiedName(serviceNamespace, name);
    }

    public static FullQualifiedName createFullQualifiedName(EntityMetaDataContainer entityMetaDataContainer) {
        return createFullQualifiedName(entityMetaDataContainer.getServiceNamespace(), entityMetaDataContainer.getEdmContainerName());
    }

    public static <T> FullQualifiedName createFullQualifiedName(String serviceNamespace, EntityMetaData<T> meta) {
        return createFullQualifiedName(serviceNamespace, meta.getEntityTypeName());
    }


    public static FullQualifiedName mapToPropertyValueTypeFQN(String serviceNamespace, EntityMetaProperty metaProp) {
        FullQualifiedName typeFQN = null;
        if (metaProp instanceof EntityMetaPropertyEnum || metaProp instanceof EntityMetaPropertyComplex || metaProp instanceof EntityMetaPropertyEntity) {
            typeFQN = createFullQualifiedName(serviceNamespace, metaProp.getFieldType().getSimpleName());
        } else if (metaProp instanceof EntityMetaPropertyPrimitve) {
            typeFQN = OlingoTypeMapper.mapToEdmPrimitiveTypeKind(metaProp.getFieldType()).getFullQualifiedName();
        }
        return typeFQN;
    }

}
