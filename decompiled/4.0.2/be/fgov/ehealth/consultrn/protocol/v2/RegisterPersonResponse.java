package be.fgov.ehealth.consultrn.protocol.v2;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.consultrn.core.v2.ResultType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RegisterPersonResponseType",
   propOrder = {"result"}
)
@XmlRootElement(
   name = "RegisterPersonResponse"
)
public class RegisterPersonResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Result"
   )
   protected ResultType result;

   public RegisterPersonResponse() {
   }

   public ResultType getResult() {
      return this.result;
   }

   public void setResult(ResultType value) {
      this.result = value;
   }
}
