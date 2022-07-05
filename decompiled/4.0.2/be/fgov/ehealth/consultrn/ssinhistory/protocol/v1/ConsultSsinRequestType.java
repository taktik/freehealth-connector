package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultSsinRequestType",
   propOrder = {"ssin"}
)
public class ConsultSsinRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Ssin",
      required = true
   )
   protected String ssin;

   public ConsultSsinRequestType() {
   }

   public String getSsin() {
      return this.ssin;
   }

   public void setSsin(String value) {
      this.ssin = value;
   }
}
