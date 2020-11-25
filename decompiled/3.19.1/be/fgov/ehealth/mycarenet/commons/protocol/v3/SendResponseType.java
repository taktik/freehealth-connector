package be.fgov.ehealth.mycarenet.commons.protocol.v3;

import be.fgov.ehealth.commons.protocol.v2.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendResponseType",
   propOrder = {"_return"}
)
public class SendResponseType extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Return",
      required = true
   )
   protected ResponseReturnType _return;

   public ResponseReturnType getReturn() {
      return this._return;
   }

   public void setReturn(ResponseReturnType value) {
      this._return = value;
   }
}
