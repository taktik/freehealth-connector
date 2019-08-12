package be.fgov.ehealth.seals.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ChoiceDecodedDataErrorType",
   propOrder = {"error", "decodedData"}
)
public class ChoiceDecodedDataErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "DecodedData"
   )
   protected DecodedDataType decodedData;

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public DecodedDataType getDecodedData() {
      return this.decodedData;
   }

   public void setDecodedData(DecodedDataType value) {
      this.decodedData = value;
   }
}
