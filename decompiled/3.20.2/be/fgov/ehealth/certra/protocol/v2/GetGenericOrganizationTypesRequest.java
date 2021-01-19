package be.fgov.ehealth.certra.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetGenericOrganizationTypesRequestType"
)
@XmlRootElement(
   name = "GetGenericOrganizationTypesRequest"
)
public class GetGenericOrganizationTypesRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
