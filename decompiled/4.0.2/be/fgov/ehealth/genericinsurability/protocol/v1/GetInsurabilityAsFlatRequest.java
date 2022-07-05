package be.fgov.ehealth.genericinsurability.protocol.v1;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetInsurabilityAsFlatRequest"
)
public class GetInsurabilityAsFlatRequest extends GetInsurabilityAsXmlOrFlatRequestType {
   private static final long serialVersionUID = 1L;

   public GetInsurabilityAsFlatRequest() {
   }
}
