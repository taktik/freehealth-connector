package oasis.names.tc.dss._1_0.core.schema;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DocumentType",
   propOrder = {"attachmentReference", "base64Data", "escapedXML", "base64XML", "inlineXML"}
)
public class DocumentType extends DocumentBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AttachmentReference"
   )
   protected AttachmentReference attachmentReference;
   @XmlElement(
      name = "Base64Data"
   )
   protected Base64Data base64Data;
   @XmlElement(
      name = "EscapedXML"
   )
   protected String escapedXML;
   @XmlElement(
      name = "Base64XML"
   )
   protected byte[] base64XML;
   @XmlElement(
      name = "InlineXML"
   )
   protected InlineXMLType inlineXML;

   public AttachmentReference getAttachmentReference() {
      return this.attachmentReference;
   }

   public void setAttachmentReference(AttachmentReference value) {
      this.attachmentReference = value;
   }

   public Base64Data getBase64Data() {
      return this.base64Data;
   }

   public void setBase64Data(Base64Data value) {
      this.base64Data = value;
   }

   public String getEscapedXML() {
      return this.escapedXML;
   }

   public void setEscapedXML(String value) {
      this.escapedXML = value;
   }

   public byte[] getBase64XML() {
      return this.base64XML;
   }

   public void setBase64XML(byte[] value) {
      this.base64XML = value;
   }

   public InlineXMLType getInlineXML() {
      return this.inlineXML;
   }

   public void setInlineXML(InlineXMLType value) {
      this.inlineXML = value;
   }
}
