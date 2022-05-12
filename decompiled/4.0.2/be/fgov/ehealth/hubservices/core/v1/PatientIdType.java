package be.fgov.ehealth.hubservices.core.v1;

import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT;
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
   name = "PatientIdType",
   propOrder = {"ids"}
)
@XmlRootElement(
   name = "PatientIdType"
)
public class PatientIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDPATIENT> ids;

   public PatientIdType() {
   }

   public List<IDPATIENT> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }
}
