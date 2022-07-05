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
   name = "GetListOfActualMedicinalProductsResponseType",
   propOrder = {"amps"}
)
@XmlRootElement(
   name = "GetListOfActualMedicinalProductsResponse"
)
public class GetListOfActualMedicinalProductsResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Amp"
   )
   protected List<AmpListType> amps;

   public GetListOfActualMedicinalProductsResponse() {
   }

   public List<AmpListType> getAmps() {
      if (this.amps == null) {
         this.amps = new ArrayList();
      }

      return this.amps;
   }
}
