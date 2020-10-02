package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HasWadaClassificationType",
   propOrder = {"wadaName", "wadaCode"}
)
public class HasWadaClassificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "WadaName"
   )
   protected String wadaName;
   @XmlElement(
      name = "WadaCode"
   )
   protected String wadaCode;

   public String getWadaName() {
      return this.wadaName;
   }

   public void setWadaName(String value) {
      this.wadaName = value;
   }

   public String getWadaCode() {
      return this.wadaCode;
   }

   public void setWadaCode(String value) {
      this.wadaCode = value;
   }
}
