package odataservice.odatav4.service.model;

import odataservice.odatav4.edm.annotation.EdmEntitySet;
import odataservice.odatav4.edm.annotation.EdmEntityType;
import odataservice.odatav4.edm.annotation.EdmFacets;
import odataservice.odatav4.edm.annotation.EdmProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@EdmEntityType
@EdmEntitySet
public class Author extends BaseEntity {

    @EdmProperty(facets = @EdmFacets(nullable = false))
    private String name;

    @EdmProperty(facets = @EdmFacets(nullable = false))
    private Gender gender;

    @EdmProperty(facets = @EdmFacets(nullable = false))
    private ContactInfo contactInfo;
}