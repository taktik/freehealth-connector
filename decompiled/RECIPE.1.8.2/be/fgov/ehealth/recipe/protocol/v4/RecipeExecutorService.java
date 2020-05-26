package be.fgov.ehealth.recipe.protocol.v4;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
   name = "RecipeExecutorService",
   wsdlLocation = "file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-executor-4_0.wsdl",
   targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4"
)
public class RecipeExecutorService extends Service {
   public static final URL WSDL_LOCATION;
   public static final QName SERVICE = new QName("urn:be:fgov:ehealth:recipe:protocol:v4", "RecipeExecutorService");
   public static final QName RecipeExecutorServiceSOAP11 = new QName("urn:be:fgov:ehealth:recipe:protocol:v4", "RecipeExecutorServiceSOAP11");

   public RecipeExecutorService(URL wsdlLocation) {
      super(wsdlLocation, SERVICE);
   }

   public RecipeExecutorService(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public RecipeExecutorService() {
      super(WSDL_LOCATION, SERVICE);
   }

   public RecipeExecutorService(WebServiceFeature... features) {
      super(WSDL_LOCATION, SERVICE, features);
   }

   public RecipeExecutorService(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, SERVICE, features);
   }

   public RecipeExecutorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "RecipeExecutorServiceSOAP11"
   )
   public RecipeExecutorPortType getRecipeExecutorServiceSOAP11() {
      return (RecipeExecutorPortType)super.getPort(RecipeExecutorServiceSOAP11, RecipeExecutorPortType.class);
   }

   @WebEndpoint(
      name = "RecipeExecutorServiceSOAP11"
   )
   public RecipeExecutorPortType getRecipeExecutorServiceSOAP11(WebServiceFeature... features) {
      return (RecipeExecutorPortType)super.getPort(RecipeExecutorServiceSOAP11, RecipeExecutorPortType.class, features);
   }

   static {
      URL url = null;

      try {
         url = new URL("file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-executor-4_0.wsdl");
      } catch (MalformedURLException var2) {
         Logger.getLogger(RecipeExecutorService.class.getName()).log(Level.INFO, "Can not initialize the default wsdl from {0}", "file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-executor-4_0.wsdl");
      }

      WSDL_LOCATION = url;
   }
}
