package be.fgov.ehealth.seals.core.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ChoiceEncodedDataErrorType",
   propOrder = {"error", "encodedData"}
)
public class ChoiceEncodedDataErrorType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Error"
   )
   protected ErrorType error;
   @XmlElement(
      name = "EncodedData"
   )
   protected EncodedDataType encodedData;

   public ErrorType getError() {
      return this.error;
   }

   public void setError(ErrorType value) {
      this.error = value;
   }

   public EncodedDataType getEncodedData() {
      return this.encodedData;
   }

   public void setEncodedData(EncodedDataType value) {
      this.encodedData = value;
   }
}
