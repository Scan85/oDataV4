package odataservice.odatav4.service.model;

import odataservice.odatav4.edm.annotation.EdmComplexType;
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

@EdmComplexType
public class ContactInfo {

    @EdmProperty(facets = @EdmFacets(nullable = false))
    private String eMail;

    @EdmProperty
    private String phone;
}
