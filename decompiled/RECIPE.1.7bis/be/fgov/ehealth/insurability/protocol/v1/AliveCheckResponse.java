package be.fgov.ehealth.insurability.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.insurability.core.v1.CommonOutputType;
import be.fgov.ehealth.insurability.core.v1.RecordCommonOutputType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AliveCheckResponseType",
   propOrder = {"commonOutput", "recordCommonOutput", "value"}
)
@XmlRootElement(
   name = "AliveCheckResponse"
)
public class AliveCheckResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonOutput"
   )
   protected CommonOutputType commonOutput;
   @XmlElement(
      name = "RecordCommonOutput"
   )
   protected RecordCommonOutputType recordCommonOutput;
   @XmlElement(
      name = "Value"
   )
   protected Object value;

   public CommonOutputType getCommonOutput() {
      return this.commonOutput;
   }

   public void setCommonOutput(CommonOutputType value) {
      this.commonOutput = value;
   }

   public RecordCommonOutputType getRecordCommonOutput() {
      return this.recordCommonOutput;
   }

   public void setRecordCommonOutput(RecordCommonOutputType value) {
      this.recordCommonOutput = value;
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue(Object value) {
      this.value = value;
   }
}
