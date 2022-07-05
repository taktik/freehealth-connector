package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContentSpecificationType",
   propOrder = {"applicationName", "contentType", "isImportant", "isEncrypted"}
)
@XmlSeeAlso({be.fgov.ehealth.ehbox.publication.protocol.v3.ContentSpecificationType.class})
public class ContentSpecificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ApplicationName"
   )
   protected String applicationName;
   @XmlElement(
      name = "ContentType",
      required = true
   )
   protected String contentType;
   @XmlElement(
      name = "IsImportant",
      defaultValue = "false"
   )
   protected boolean isImportant;
   @XmlElement(
      name = "IsEncrypted",
      defaultValue = "false"
   )
   protected boolean isEncrypted;

   public ContentSpecificationType() {
   }

   public String getApplicationName() {
      return this.applicationName;
   }

   public void setApplicationName(String value) {
      this.applicationName = value;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public boolean isIsImportant() {
      return this.isImportant;
   }

   public void setIsImportant(boolean value) {
      this.isImportant = value;
   }

   public boolean isIsEncrypted() {
      return this.isEncrypted;
   }

   public void setIsEncrypted(boolean value) {
      this.isEncrypted = value;
   }
}
