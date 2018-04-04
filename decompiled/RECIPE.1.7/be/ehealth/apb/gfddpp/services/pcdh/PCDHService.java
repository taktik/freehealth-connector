package be.ehealth.apb.gfddpp.services.pcdh;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
   name = "PCDHService",
   targetNamespace = "urn:be:fgov:ehealth:gfddpp:protocol:v1",
   wsdlLocation = "https://services-int.ehealth.fgov.be/GFDDPP/PCDH/v1?WSDL"
)
public class PCDHService extends Service {
   private static final URL PCDHSERVICE_WSDL_LOCATION;
   private static final Logger logger = Logger.getLogger(PCDHService.class.getName());

   public PCDHService(URL var1, QName var2) {
      super(var1, var2);
   }

   public PCDHService() {
      super(PCDHSERVICE_WSDL_LOCATION, new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "PCDHService"));
   }

   @WebEndpoint(
      name = "PCDHServiceSOAP11"
   )
   public PCDHPortType getPCDHServiceSOAP11() {
      return (PCDHPortType)super.getPort(new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "PCDHServiceSOAP11"), PCDHPortType.class);
   }

   @WebEndpoint(
      name = "PCDHServiceSOAP11"
   )
   public PCDHPortType getPCDHServiceSOAP11(WebServiceFeature... var1) {
      return (PCDHPortType)super.getPort(new QName("urn:be:fgov:ehealth:gfddpp:protocol:v1", "PCDHServiceSOAP11"), PCDHPortType.class, var1);
   }

   static {
      URL var0 = null;

      try {
         URL var1 = PCDHService.class.getResource(".");
         var0 = new URL(var1, "https://services-int.ehealth.fgov.be/GFDDPP/PCDH/v1?WSDL");
      } catch (MalformedURLException var2) {
         logger.warning("Failed to create URL for the wsdl Location: 'https://services-int.ehealth.fgov.be/GFDDPP/PCDH/v1?WSDL', retrying as a local file");
         logger.warning(var2.getMessage());
      }

      PCDHSERVICE_WSDL_LOCATION = var0;
   }
}
