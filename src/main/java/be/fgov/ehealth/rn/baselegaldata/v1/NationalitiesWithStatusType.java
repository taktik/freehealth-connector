package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalitiesWithStatusType",
   propOrder = {"nationalities"}
)
public class NationalitiesWithStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nationality"
   )
   protected List nationalities;
   @XmlAttribute(
      name = "Status"
   )
   protected String status;

   public List getNationalities() {
      if (this.nationalities == null) {
         this.nationalities = new ArrayList();
      }

      return this.nationalities;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }
}
