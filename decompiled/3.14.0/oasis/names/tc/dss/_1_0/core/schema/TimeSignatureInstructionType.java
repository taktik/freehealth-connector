package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TimeSignatureInstructionType"
)
public class TimeSignatureInstructionType extends UpdateSignatureInstructionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "TimeStampTheGivenSignature"
   )
   protected Boolean timeStampTheGivenSignature;

   public boolean isTimeStampTheGivenSignature() {
      return this.timeStampTheGivenSignature == null ? false : this.timeStampTheGivenSignature.booleanValue();
   }

   public void setTimeStampTheGivenSignature(Boolean value) {
      this.timeStampTheGivenSignature = value;
   }
}
