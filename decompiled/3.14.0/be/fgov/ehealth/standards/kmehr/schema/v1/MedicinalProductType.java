package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "medicinalProductType",
   propOrder = {"intendedcds", "deliveredcds", "intendedname", "deliveredname"}
)
public class MedicinalProductType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "intendedcd",
      required = true
   )
   protected List<CDDRUGCNK> intendedcds;
   @XmlElement(
      name = "deliveredcd"
   )
   protected List<CDDRUGCNK> deliveredcds;
   @XmlElement(
      required = true
   )
   protected String intendedname;
   protected Object deliveredname;

   public List<CDDRUGCNK> getIntendedcds() {
      if (this.intendedcds == null) {
         this.intendedcds = new ArrayList();
      }

      return this.intendedcds;
   }

   public List<CDDRUGCNK> getDeliveredcds() {
      if (this.deliveredcds == null) {
         this.deliveredcds = new ArrayList();
      }

      return this.deliveredcds;
   }

   public String getIntendedname() {
      return this.intendedname;
   }

   public void setIntendedname(String value) {
      this.intendedname = value;
   }

   public Object getDeliveredname() {
      return this.deliveredname;
   }

   public void setDeliveredname(Object value) {
      this.deliveredname = value;
   }
}
