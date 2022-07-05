package be.fgov.ehealth.genericinsurability.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetInsurabilityRequest"
)
public class GetInsurabilityRequest extends GetInsurabilityAsXmlOrFlatRequestType {
   private static final long serialVersionUID = 1L;

   public GetInsurabilityRequest() {
   }
}
