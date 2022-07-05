package org.w3._2005._05.xmlmime;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "hexBinary",
   propOrder = {"value"}
)
public class HexBinary implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlJavaTypeAdapter(HexBinaryAdapter.class)
   @XmlSchemaType(
      name = "hexBinary"
   )
   protected byte[] value;
   @XmlAttribute(
      name = "contentType",
      namespace = "http://www.w3.org/2005/05/xmlmime"
   )
   protected String contentType;

   public HexBinary() {
   }

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String value) {
      this.contentType = value;
   }
}
