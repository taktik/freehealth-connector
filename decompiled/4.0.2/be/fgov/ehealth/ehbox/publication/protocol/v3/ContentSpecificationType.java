package be.fgov.ehealth.ehbox.publication.protocol.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContentSpecificationType",
   propOrder = {"publicationReceipt", "receivedReceipt", "readReceipt"}
)
public class ContentSpecificationType extends be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PublicationReceipt",
      defaultValue = "false"
   )
   protected boolean publicationReceipt;
   @XmlElement(
      name = "ReceivedReceipt",
      defaultValue = "false"
   )
   protected boolean receivedReceipt;
   @XmlElement(
      name = "ReadReceipt",
      defaultValue = "false"
   )
   protected boolean readReceipt;

   public ContentSpecificationType() {
   }

   public boolean isPublicationReceipt() {
      return this.publicationReceipt;
   }

   public void setPublicationReceipt(boolean value) {
      this.publicationReceipt = value;
   }

   public boolean isReceivedReceipt() {
      return this.receivedReceipt;
   }

   public void setReceivedReceipt(boolean value) {
      this.receivedReceipt = value;
   }

   public boolean isReadReceipt() {
      return this.readReceipt;
   }

   public void setReadReceipt(boolean value) {
      this.readReceipt = value;
   }
}
