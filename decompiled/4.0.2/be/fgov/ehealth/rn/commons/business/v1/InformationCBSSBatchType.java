package be.fgov.ehealth.rn.commons.business.v1;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InformationCBSSBatchType",
   propOrder = {"fileId", "fileCreationTimestamp"}
)
public class InformationCBSSBatchType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FileId",
      required = true
   )
   @XmlSchemaType(
      name = "unsignedLong"
   )
   protected BigInteger fileId;
   @XmlElement(
      name = "FileCreationTimestamp",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime fileCreationTimestamp;

   public InformationCBSSBatchType() {
   }

   public BigInteger getFileId() {
      return this.fileId;
   }

   public void setFileId(BigInteger value) {
      this.fileId = value;
   }

   public DateTime getFileCreationTimestamp() {
      return this.fileCreationTimestamp;
   }

   public void setFileCreationTimestamp(DateTime value) {
      this.fileCreationTimestamp = value;
   }
}
