package odataservice.odatav4.service;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Seyit Can
 * This class contains the meta data of an enum property for the OData service.
 */
@Getter
public class EntityMetaPropertyEnum extends EntityMetaProperty {

    @Builder
    private EntityMetaPropertyEnum(
            boolean isKey,
            String name,
            String fieldName,
            Class<?> fieldType,
            Boolean nullable) {

        super(isKey, name, fieldName, fieldType, nullable);
    }
}
