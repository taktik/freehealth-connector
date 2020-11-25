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
   name = "ExecutorServices",
   wsdlLocation = "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/ExecutorServices.wsdl",
   targetNamespace = "http://services.recipe.be"
)
public class ExecutorServices_Service extends Service {
   public static final URL WSDL_LOCATION;
   public static final QName SERVICE = new QName("http://services.recipe.be", "ExecutorServices");
   public static final QName ExecutorServicesPort = new QName("http://services.recipe.be", "ExecutorServicesPort");

   public ExecutorServices_Service(URL wsdlLocation) {
      super(wsdlLocation, SERVICE);
   }

   public ExecutorServices_Service(URL wsdlLocation, QName serviceName) {
      super(wsdlLocation, serviceName);
   }

   public ExecutorServices_Service() {
      super(WSDL_LOCATION, SERVICE);
   }

   public ExecutorServices_Service(WebServiceFeature... features) {
      super(WSDL_LOCATION, SERVICE, features);
   }

   public ExecutorServices_Service(URL wsdlLocation, WebServiceFeature... features) {
      super(wsdlLocation, SERVICE, features);
   }

   public ExecutorServices_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
      super(wsdlLocation, serviceName, features);
   }

   @WebEndpoint(
      name = "ExecutorServicesPort"
   )
   public ExecutorServices getExecutorServicesPort() {
      return (ExecutorServices)super.getPort(ExecutorServicesPort, ExecutorServices.class);
   }

   @WebEndpoint(
      name = "ExecutorServicesPort"
   )
   public ExecutorServices getExecutorServicesPort(WebServiceFeature... features) {
      return (ExecutorServices)super.getPort(ExecutorServicesPort, ExecutorServices.class, features);
   }

   static {
      URL url = null;

      try {
         url = new URL("file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/ExecutorServices.wsdl");
      } catch (MalformedURLException var2) {
         Logger.getLogger(ExecutorServices_Service.class.getName()).log(Level.INFO, "Can not initialize the default wsdl from {0}", "file:/C:/Workspace/BusinessConnector/business-connector/be-ehealth-recipe-client/src/main/resources/META-INF/recipe-central-system/WSDL/ExecutorServices.wsdl");
      }

      WSDL_LOCATION = url;
   }
}
