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
   name = "GetListOfActualMedicinalProductPackagesResponseType",
   propOrder = {"ampps"}
)
@XmlRootElement(
   name = "GetListOfActualMedicinalProductPackagesResponse"
)
public class GetListOfActualMedicinalProductPackagesResponse extends ListConsultationResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ampp"
   )
   protected List<AmppListType> ampps;

   public List<AmppListType> getAmpps() {
      if (this.ampps == null) {
         this.ampps = new ArrayList();
      }

      return this.ampps;
   }
}
