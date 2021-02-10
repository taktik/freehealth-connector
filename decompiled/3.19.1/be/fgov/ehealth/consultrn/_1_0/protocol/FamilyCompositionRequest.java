package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyCompositionRequestType",
   propOrder = {"ssin"}
)
@XmlRootElement(
   name = "FamilyCompositionRequest"
)
public class FamilyCompositionRequest extends ConsultRnRequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SSIN",
      required = true
   )
   protected String ssin;

   public String getSSIN() {
      return this.ssin;
   }

   public void setSSIN(String value) {
      this.ssin = value;
   }
}
