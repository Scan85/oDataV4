package odataservice.odatav4.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.Getter;

/**
 * @author Seyit Can
 * This class holds all meta data of multiple entities.
 */
public class EntityMetaDataContainer {

    @Getter
    private final String serviceNamespace;

    @Getter
    private final String edmContainerName;

    /**
     * List of meta data for multiple entities.
     */
    @Getter
    private final Set<EntityMetaData<?>> allEntityMetaData;

    public EntityMetaDataContainer(String serviceNamespace, String edmContainerName, Set<EntityMetaData<?>> allEntityMetaData) {
        this.serviceNamespace = serviceNamespace;
        this.edmContainerName = edmContainerName;
        this.allEntityMetaData = Collections.synchronizedSet(allEntityMetaData);
    }

    /**
     * Returns the meta entity data of the given type set name. If no set with
     * this name found, the method returns <code>null</code>.
     *
     * @param entityTypeSetName the name of the type set
     * @return the meta data of the entity or <code>null</code> if no set found
     */
    public EntityMetaData<?> getEntityMetaDataByTypeSetName(String entityTypeSetName) {
        EntityMetaData<?> entity = null;
        for (EntityMetaData<?> meta : this.allEntityMetaData) {
            if (meta.getEntityTypeSetName() != null && meta.getEntityTypeSetName().equals(entityTypeSetName)) {
                entity = meta;
                break;
            }
        }
        return entity;
    }

    /**
     * Returns the meta entity data of the given full qualified type name. If no
     * meta data with this name found, the method returns <code>null</code>.
     *
     * @param serviceNamespace the service namespace
     * @param entityTypeName the name of the type name
     * @return the meta data of the entity or <code>null</code> if no meta data
     * found
     */
    public EntityMetaData<?> getEntityMetaDataByTypeName(String serviceNamespace, String entityTypeName) {

        EntityMetaData<?> entity = null;

        if (this.serviceNamespace.equals(serviceNamespace)) {
            for (EntityMetaData<?> meta : this.allEntityMetaData) {
                if (meta.getEntityTypeName().equals(entityTypeName)) {
                    entity = meta;
                    break;
                }
            }
        }
        return entity;
    }

    public EntityMetaProperty getEntityMetaPropertyDataByTypeName(String serviceNamespace, String propertyTypeName) {
        EntityMetaProperty propData = null;
        if (this.serviceNamespace.equals(serviceNamespace)) {
            for (EntityMetaData<?> meta : this.allEntityMetaData) {
                List<EntityMetaProperty> enumPropertyDataList = meta.getEnumPropertyData();

                for (EntityMetaProperty propEntry : enumPropertyDataList) {
                    if (propEntry.getFieldType().getSimpleName().equals(propertyTypeName)) {
                        propData = propEntry;
                        break;
                    }

                }

            }
        }
        return propData;
    }
}
