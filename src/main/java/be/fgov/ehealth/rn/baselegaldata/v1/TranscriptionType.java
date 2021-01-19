package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TranscriptionType",
   propOrder = {"transcriptionDate", "transcriptionLocation"}
)
public class TranscriptionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TranscriptionDate"
   )
   protected String transcriptionDate;
   @XmlElement(
      name = "TranscriptionLocation"
   )
   protected LocationType transcriptionLocation;

   public String getTranscriptionDate() {
      return this.transcriptionDate;
   }

   public void setTranscriptionDate(String value) {
      this.transcriptionDate = value;
   }

   public LocationType getTranscriptionLocation() {
      return this.transcriptionLocation;
   }

   public void setTranscriptionLocation(LocationType value) {
      this.transcriptionLocation = value;
   }
}
