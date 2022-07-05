package be.cin.encrypted;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"value"}
)
public class BusinessContent implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;
   @XmlAttribute(
      name = "ContentType"
   )
   protected String contentType;
   @XmlAttribute(
      name = "ContentEncoding"
   )
   protected String contentEncoding;

   public BusinessContent() {
   }

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public String getContentType() {
      return this.contentType == null ? "application/octet-stream" : this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }

   public String getContentEncoding() {
      return this.contentEncoding == null ? "none" : this.contentEncoding;
   }

   public void setContentEncoding(String value) {
      this.contentEncoding = value;
   }
}
