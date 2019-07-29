package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "internalPostResponse",
   propOrder = {"_return"}
)
@XmlRootElement(
   name = "internalPostResponse"
)
public class InternalPostResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "return",
      required = true
   )
   protected PostResponseReturn _return;

   public PostResponseReturn getReturn() {
      return this._return;
   }

   public void setReturn(PostResponseReturn value) {
      this._return = value;
   }
}
