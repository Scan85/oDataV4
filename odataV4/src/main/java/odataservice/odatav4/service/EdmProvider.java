/**
 * 
 */
package odataservice.odatav4.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;

/**
 * @author Seyit Can
 *
 */
public class EdmProvider extends CsdlAbstractEdmProvider {
	// Service Namespace
	public static final String NAMESPACE = "OData.odatav4";

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_PRODUCT_NAME = "Product";
	public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);

	// Entity Set Names
	public static final String ES_PRODUCTS_NAME = "Products";

	/* (non-Javadoc)
	 * @see org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider#getEntityContainer()
	 */
	@Override
	public CsdlEntityContainer getEntityContainer() throws ODataException {
		// create EntitySets
		  List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
		  entitySets.add(getEntitySet(CONTAINER, ES_PRODUCTS_NAME));

		  // create EntityContainer
		  CsdlEntityContainer entityContainer = new CsdlEntityContainer();
		  entityContainer.setName(CONTAINER_NAME);
		  entityContainer.setEntitySets(entitySets);

		  return entityContainer;
	}

	/* (non-Javadoc)
	 * @see org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider#getEntityContainerInfo(org.apache.olingo.commons.api.edm.FullQualifiedName)
	 */
	@Override
	public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {
		// This method is invoked when displaying the Service Document at e.g. http://localhost:8080/DemoService/DemoService.svc
	    if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
	        CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
	        entityContainerInfo.setContainerName(CONTAINER);
	        return entityContainerInfo;
	    }

	    return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider#getEntitySet(org.apache.olingo.commons.api.edm.FullQualifiedName, java.lang.String)
	 */
	@Override
	public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {
		if(entityContainer.equals(CONTAINER)){
		    if(entitySetName.equals(ES_PRODUCTS_NAME)){
		      CsdlEntitySet entitySet = new CsdlEntitySet();
		      entitySet.setName(ES_PRODUCTS_NAME);
		      entitySet.setType(ET_PRODUCT_FQN);

		      return entitySet;
		    }
		  }

		  return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider#getEntityType(org.apache.olingo.commons.api.edm.FullQualifiedName)
	 */
	@Override
	public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {
		// this method is called for one of the EntityTypes that are configured in the Schema
		  if(entityTypeName.equals(ET_PRODUCT_FQN)){

		    //create EntityType properties
		    CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		    CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
		    CsdlProperty  description = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		    // create CsdlPropertyRef for Key element
		    CsdlPropertyRef propertyRef = new CsdlPropertyRef();
		    propertyRef.setName("ID");

		    // configure EntityType
		    CsdlEntityType entityType = new CsdlEntityType();
		    entityType.setName(ET_PRODUCT_NAME);
		    entityType.setProperties(Arrays.asList(id, name , description));
		    entityType.setKey(Collections.singletonList(propertyRef));

		    return entityType;
		  }

		  return null;
	}

	/* (non-Javadoc)
	 * @see org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider#getSchemas()
	 */
	@Override
	public List<CsdlSchema> getSchemas() {
		// create Schema
		  CsdlSchema schema = new CsdlSchema();
		  schema.setNamespace(NAMESPACE);

		  // add EntityTypes
		  List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
		  entityTypes.add(getEntityType(ET_PRODUCT_FQN));
		  schema.setEntityTypes(entityTypes);

		  // add EntityContainer
		  schema.setEntityContainer(getEntityContainer());

		  // finally
		  List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
		  schemas.add(schema);

		  return schemas;
	}

	/**
	 * 
	 */
	public EdmProvider() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
