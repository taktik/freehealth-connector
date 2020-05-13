package be.apb.gfddpp.rtrn.registerdata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "pharmaceuticalCareEventType",
   propOrder = {"sguid", "dispensation"}
)
public class PharmaceuticalCareEventType {
   @XmlElement(
      required = true
   )
   protected String sguid;
   @XmlElement(
      required = true
   )
   protected List<Dispensation> dispensation;

   public String getSguid() {
      return this.sguid;
   }

   public void setSguid(String value) {
      this.sguid = value;
   }

   public List<Dispensation> getDispensation() {
      if (this.dispensation == null) {
         this.dispensation = new ArrayList();
      }

      return this.dispensation;
   }
}
