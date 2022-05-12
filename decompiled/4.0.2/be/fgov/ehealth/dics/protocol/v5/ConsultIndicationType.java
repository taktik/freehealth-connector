package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultIndicationType",
   propOrder = {"description", "snomedCT"}
)
public class ConsultIndicationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Description",
      required = true
   )
   protected String description;
   @XmlElement(
      name = "SnomedCT",
      required = true
   )
   protected String snomedCT;
   @XmlAttribute(
      name = "code",
      required = true
   )
   protected String code;

   public ConsultIndicationType() {
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String value) {
      this.description = value;
   }

   public String getSnomedCT() {
      return this.snomedCT;
   }

   public void setSnomedCT(String value) {
      this.snomedCT = value;
   }

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }
}
