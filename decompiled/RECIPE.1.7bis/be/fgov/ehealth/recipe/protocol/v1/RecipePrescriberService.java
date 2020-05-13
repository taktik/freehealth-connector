package be.fgov.ehealth.recipe.protocol.v1;

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
   name = "RecipePrescriberService",
   wsdlLocation = "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-prescriber-1_0.wsdl",
   targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v1"
)
public class RecipePrescriberService extends Service {
   public static final URL WSDL_LOCATION;
   public static final QName SERVICE = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "RecipePrescriberService");
   public static final QName RecipePrescriberServiceSOAP11 = new QName("urn:be:fgov:ehealth:recipe:protocol:v1", "RecipePrescriberServiceSOAP11");

   public RecipePrescriberService(URL wsdlLocation) {
      super(wsdlLocation, SERVICE);
   }

   public RecipePrescriberService(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public RecipePrescriberService() {
      super(WSDL_LOCATION, SERVICE);
   }

   public RecipePrescriberService(WebServiceFeature... features) {
      super(WSDL_LOCATION, SERVICE, features);
   }

   public RecipePrescriberService(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, SERVICE, features);
   }

   public RecipePrescriberService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "RecipePrescriberServiceSOAP11"
   )
   public RecipePrescriberPortType getRecipePrescriberServiceSOAP11() {
      return (RecipePrescriberPortType)super.getPort(RecipePrescriberServiceSOAP11, RecipePrescriberPortType.class);
   }

   @WebEndpoint(
      name = "RecipePrescriberServiceSOAP11"
   )
   public RecipePrescriberPortType getRecipePrescriberServiceSOAP11(WebServiceFeature... features) {
      return (RecipePrescriberPortType)super.getPort(RecipePrescriberServiceSOAP11, RecipePrescriberPortType.class, features);
   }

   static {
      URL url = null;

      try {
         url = new URL("file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-prescriber-1_0.wsdl");
      } catch (MalformedURLException var2) {
         Logger.getLogger(RecipePrescriberService.class.getName()).log(Level.INFO, "Can not initialize the default wsdl from {0}", "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-prescriber-1_0.wsdl");
      }

      WSDL_LOCATION = url;
   }
}
