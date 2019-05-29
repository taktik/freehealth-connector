package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "GetListOfVirtualMedicinalProductsRequest"
)
public class GetListOfVirtualMedicinalProductsRequest extends ListConsultationRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
}
