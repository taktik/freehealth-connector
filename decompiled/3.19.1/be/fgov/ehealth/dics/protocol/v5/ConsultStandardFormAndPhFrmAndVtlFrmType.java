package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultStandardFormAndPhFrmAndVtlFrmType",
   propOrder = {"pharmaceuticalFormCodes", "virtualFormCodes"}
)
public class ConsultStandardFormAndPhFrmAndVtlFrmType extends ConsultStandardFormType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PharmaceuticalFormCode"
   )
   protected List<String> pharmaceuticalFormCodes;
   @XmlElement(
      name = "VirtualFormCode"
   )
   protected List<String> virtualFormCodes;

   public List<String> getPharmaceuticalFormCodes() {
      if (this.pharmaceuticalFormCodes == null) {
         this.pharmaceuticalFormCodes = new ArrayList();
      }

      return this.pharmaceuticalFormCodes;
   }

   public List<String> getVirtualFormCodes() {
      if (this.virtualFormCodes == null) {
         this.virtualFormCodes = new ArrayList();
      }

      return this.virtualFormCodes;
   }
}
