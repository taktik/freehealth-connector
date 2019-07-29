package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getResponse",
   propOrder = {"_return"}
)
@XmlRootElement(
   name = "getResponse"
)
public class GetResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "return",
      required = true
   )
   protected Responses _return;

   public Responses getReturn() {
      return this._return;
   }

   public void setReturn(Responses value) {
      this._return = value;
   }
}
