package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"_return"}
)
@XmlRootElement(
   name = "postResponse"
)
public class PostResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "return",
      required = true
   )
   protected TAck _return;

   public TAck getReturn() {
      return this._return;
   }

   public void setReturn(TAck value) {
      this._return = value;
   }
}
