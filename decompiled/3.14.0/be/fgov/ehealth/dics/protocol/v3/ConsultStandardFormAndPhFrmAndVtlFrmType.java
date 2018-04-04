package be.fgov.ehealth.dics.protocol.v3;

import be.fgov.ehealth.dics.core.v3.refdata.PharmaceuticalFormKeyType;
import be.fgov.ehealth.dics.core.v3.refdata.VirtualFormKeyType;
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
   propOrder = {"pharmaceuticalForms", "virtualForms"}
)
public class ConsultStandardFormAndPhFrmAndVtlFrmType extends ConsultStandardFormType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PharmaceuticalForm"
   )
   protected List<PharmaceuticalFormKeyType> pharmaceuticalForms;
   @XmlElement(
      name = "VirtualForm"
   )
   protected List<VirtualFormKeyType> virtualForms;

   public List<PharmaceuticalFormKeyType> getPharmaceuticalForms() {
      if (this.pharmaceuticalForms == null) {
         this.pharmaceuticalForms = new ArrayList();
      }

      return this.pharmaceuticalForms;
   }

   public List<VirtualFormKeyType> getVirtualForms() {
      if (this.virtualForms == null) {
         this.virtualForms = new ArrayList();
      }

      return this.virtualForms;
   }
}
