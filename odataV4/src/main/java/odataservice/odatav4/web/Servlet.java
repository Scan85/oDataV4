package odataservice.odatav4.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;

import odataservice.odatav4.service.AnnotationEdmProvider;
import odataservice.odatav4.service.DataCollectionProcessor;
import odataservice.odatav4.service.DataEntityProcessor;
import odataservice.odatav4.service.DataPrimitiveValueProcessor;
import odataservice.odatav4.service.OlingoDataService;
import javax.inject.Inject;
import org.apache.olingo.commons.api.edmx.EdmxReference;
import org.apache.olingo.server.api.debug.DefaultDebugSupport;

import odataservice.odatav4.service.EdmProvider;
import odataservice.odatav4.service.EntityCollectionProcessor;

/**
 * This {@link Servlet} provides the OData service.
 *
 * @author Seyit Can
 */
@WebServlet(name = Servlet.SERVLET_NAME, urlPatterns = {Servlet.SERVLET_URL_PATTERNS})
public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public static final String SERVLET_NAME = "ODataDemoServlet";

    public static final String SERVLET_URL_PATTERNS = "/api/servlet/v1/odata.svc/*";

    private static final String BASE_MODEL_PACKAGE = "odataservice.odata.model";

    public static final String SERVICE_NAMESPACE = "OData";

    public static final String EDM_CONTAINER_NAME = "Container";

    private transient AnnotationEdmProvider edmProvider;
    private transient DataCollectionProcessor entityCollectionProcessor;
    private transient DataEntityProcessor entityProcessor;
    //private transient DataPrimitiveProcessor entityDataPrimitiveProcessor;
    private transient DataPrimitiveValueProcessor entityDataPrimitiveValueProcessor;

    @Inject
    private OlingoDataService dataService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.edmProvider = new AnnotationEdmProvider(SERVICE_NAMESPACE, EDM_CONTAINER_NAME, BASE_MODEL_PACKAGE);
        this.entityCollectionProcessor = new DataCollectionProcessor(dataService, this.edmProvider.getEntityMetaDataContainer());
        this.entityProcessor = new DataEntityProcessor(dataService, this.edmProvider.getEntityMetaDataContainer());
        // this.entityDataPrimitiveProcessor = new DataPrimitiveProcessor(dataService, this.edmProvider.getEntityMetaDataCollection());
        this.entityDataPrimitiveValueProcessor = new DataPrimitiveValueProcessor(dataService, this.edmProvider.getEntityMetaDataContainer());
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        List<EdmxReference> odataProcessors = new ArrayList<>();
        OData odata = OData.newInstance();
        ServiceMetadata edm = odata.createServiceMetadata(this.edmProvider, odataProcessors);
        ODataHttpHandler handler = odata.createHandler(edm);
        handler.register(entityCollectionProcessor);
        handler.register(entityProcessor);
        //handler.register(entityDataPrimitiveProcessor);
        handler.register(entityDataPrimitiveValueProcessor);

        //run service url query option odata-debug=json to return detailed error information in json format for each request.
        //http://olingo.apache.org/doc/odata2/tutorials/debug.html
        handler.register(new DefaultDebugSupport());

        handler.process(req, resp);

    }

}
