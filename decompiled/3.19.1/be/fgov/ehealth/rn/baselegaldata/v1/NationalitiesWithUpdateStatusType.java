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
   name = "NationalitiesWithUpdateStatusType",
   propOrder = {"nationalities"}
)
public class NationalitiesWithUpdateStatusType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Nationality"
   )
   protected List<NationalityInfoBaseType> nationalities;
   @XmlAttribute(
      name = "Status"
   )
   protected UpdateDatagroupStatusType status;

   public List<NationalityInfoBaseType> getNationalities() {
      if (this.nationalities == null) {
         this.nationalities = new ArrayList();
      }

      return this.nationalities;
   }

   public UpdateDatagroupStatusType getStatus() {
      return this.status;
   }

   public void setStatus(UpdateDatagroupStatusType value) {
      this.status = value;
   }
}
