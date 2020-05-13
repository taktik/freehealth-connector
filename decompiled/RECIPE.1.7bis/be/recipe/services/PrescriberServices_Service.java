package be.recipe.services;

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
   name = "PrescriberServices",
   wsdlLocation = "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/PrescriberServices.wsdl",
   targetNamespace = "http://services.recipe.be"
)
public class PrescriberServices_Service extends Service {
   public static final URL WSDL_LOCATION;
   public static final QName SERVICE = new QName("http://services.recipe.be", "PrescriberServices");
   public static final QName PrescriberServicesPort = new QName("http://services.recipe.be", "PrescriberServicesPort");

   public PrescriberServices_Service(URL wsdlLocation) {
      super(wsdlLocation, SERVICE);
   }

   public PrescriberServices_Service(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public PrescriberServices_Service() {
      super(WSDL_LOCATION, SERVICE);
   }

   public PrescriberServices_Service(WebServiceFeature... features) {
      super(WSDL_LOCATION, SERVICE, features);
   }

   public PrescriberServices_Service(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, SERVICE, features);
   }

   public PrescriberServices_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "PrescriberServicesPort"
   )
   public PrescriberServices getPrescriberServicesPort() {
      return (PrescriberServices)super.getPort(PrescriberServicesPort, PrescriberServices.class);
   }

   @WebEndpoint(
      name = "PrescriberServicesPort"
   )
   public PrescriberServices getPrescriberServicesPort(WebServiceFeature... features) {
      return (PrescriberServices)super.getPort(PrescriberServicesPort, PrescriberServices.class, features);
   }

   static {
      URL url = null;

      try {
         url = new URL("file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/PrescriberServices.wsdl");
      } catch (MalformedURLException var2) {
         Logger.getLogger(PrescriberServices_Service.class.getName()).log(Level.INFO, "Can not initialize the default wsdl from {0}", "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/PrescriberServices.wsdl");
      }

      WSDL_LOCATION = url;
   }
}
