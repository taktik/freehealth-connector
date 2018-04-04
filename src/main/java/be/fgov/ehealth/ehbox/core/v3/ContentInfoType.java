package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContentInfoType",
   propOrder = {"encryptableINSSPatient", "title", "mimeType", "hasFreeInformations", "hasAnnex"}
)
public class ContentInfoType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptableINSSPatient"
   )
   protected byte[] encryptableINSSPatient;
   @XmlElement(
      name = "Title",
      required = true
   )
   protected String title;
   @XmlElement(
      name = "MimeType",
      required = true
   )
   protected String mimeType;
   @XmlElement(
      name = "HasFreeInformations"
   )
   protected boolean hasFreeInformations;
   @XmlElement(
      name = "HasAnnex"
   )
   protected boolean hasAnnex;

   public byte[] getEncryptableINSSPatient() {
      return this.encryptableINSSPatient;
   }

   public void setEncryptableINSSPatient(byte[] value) {
      this.encryptableINSSPatient = value;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String value) {
      this.title = value;
   }

   public String getMimeType() {
      return this.mimeType;
   }

   public void setMimeType(String value) {
      this.mimeType = value;
   }

   public boolean isHasFreeInformations() {
      return this.hasFreeInformations;
   }

   public void setHasFreeInformations(boolean value) {
      this.hasFreeInformations = value;
   }

   public boolean isHasAnnex() {
      return this.hasAnnex;
   }

   public void setHasAnnex(boolean value) {
      this.hasAnnex = value;
   }
}
