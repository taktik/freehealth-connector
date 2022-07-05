package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2005._05.xmlmime.Base64Binary;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TAckResponse",
   propOrder = {"tAck", "xadesT"}
)
public class TAckResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TAck",
      required = true
   )
   protected TAck tAck;
   @XmlElement(
      name = "Xades-t",
      required = true
   )
   protected Base64Binary xadesT;

   public TAckResponse() {
   }

   public TAck getTAck() {
      return this.tAck;
   }

   public void setTAck(TAck value) {
      this.tAck = value;
   }

   public Base64Binary getXadesT() {
      return this.xadesT;
   }

   public void setXadesT(Base64Binary value) {
      this.xadesT = value;
   }
}
