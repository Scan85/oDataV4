package odataservice.odatav4.service.model;

import odataservice.odatav4.edm.annotation.EdmFacets;
import odataservice.odatav4.edm.annotation.EdmKey;
import odataservice.odatav4.edm.annotation.EdmProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @EdmKey
    @EdmProperty(facets = @EdmFacets(nullable = false))
    private long id;
}
