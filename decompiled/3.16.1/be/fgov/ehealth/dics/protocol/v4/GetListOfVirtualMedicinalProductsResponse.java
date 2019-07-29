package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetListOfVirtualMedicinalProductsResponseType",
   propOrder = {"vmps"}
)
@XmlRootElement(
   name = "GetListOfVirtualMedicinalProductsResponse"
)
public class GetListOfVirtualMedicinalProductsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Vmp"
   )
   protected List<VmpListType> vmps;

   public List<VmpListType> getVmps() {
      if (this.vmps == null) {
         this.vmps = new ArrayList();
      }

      return this.vmps;
   }
}
