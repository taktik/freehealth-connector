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
   name = "RecipePatientService",
   wsdlLocation = "file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-patient-4_0.wsdl",
   targetNamespace = "urn:be:fgov:ehealth:recipe:protocol:v4"
)
public class RecipePatientService extends Service {
   public static final URL WSDL_LOCATION;
   public static final QName SERVICE = new QName("urn:be:fgov:ehealth:recipe:protocol:v4", "RecipePatientService");
   public static final QName RecipePatientServiceSOAP11 = new QName("urn:be:fgov:ehealth:recipe:protocol:v4", "RecipePatientServiceSOAP11");

   public RecipePatientService(URL wsdlLocation) {
      super(wsdlLocation, SERVICE);
   }

   public RecipePatientService(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public RecipePatientService() {
      super(WSDL_LOCATION, SERVICE);
   }

   public RecipePatientService(WebServiceFeature... features) {
      super(WSDL_LOCATION, SERVICE, features);
   }

   public RecipePatientService(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, SERVICE, features);
   }

   public RecipePatientService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "RecipePatientServiceSOAP11"
   )
   public RecipePatientPortType getRecipePatientServiceSOAP11() {
      return (RecipePatientPortType)super.getPort(RecipePatientServiceSOAP11, RecipePatientPortType.class);
   }

   @WebEndpoint(
      name = "RecipePatientServiceSOAP11"
   )
   public RecipePatientPortType getRecipePatientServiceSOAP11(WebServiceFeature... features) {
      return (RecipePatientPortType)super.getPort(RecipePatientServiceSOAP11, RecipePatientPortType.class, features);
   }

   static {
      URL url = null;

      try {
         url = new URL("file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-patient-4_0.wsdl");
      } catch (MalformedURLException var2) {
         Logger.getLogger(RecipePatientService.class.getName()).log(Level.INFO, "Can not initialize the default wsdl from {0}", "file:/C:/projects/development/recipe-business-connector_1.8.0/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/ehealth-recipe-services/WSDL/RecipeWebService-patient-4_0.wsdl");
      }

      WSDL_LOCATION = url;
   }
}
