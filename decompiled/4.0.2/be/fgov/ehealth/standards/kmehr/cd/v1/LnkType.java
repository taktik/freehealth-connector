package be.fgov.ehealth.standards.kmehr.cd.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "lnkType",
   propOrder = {"value"}
)
public class LnkType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected byte[] value;
   @XmlAttribute(
      name = "TYPE",
      required = true
   )
   protected CDLNKvalues type;
   @XmlAttribute(
      name = "MEDIATYPE"
   )
   protected CDMEDIATYPEvalues mediatype;
   @XmlAttribute(
      name = "URL"
   )
   protected String url;
   @XmlAttribute(
      name = "SIZE"
   )
   protected String size;

   public LnkType() {
   }

   public byte[] getValue() {
      return this.value;
   }

   public void setValue(byte[] value) {
      this.value = value;
   }

   public CDLNKvalues getTYPE() {
      return this.type;
   }

   public void setTYPE(CDLNKvalues value) {
      this.type = value;
   }

   public CDMEDIATYPEvalues getMEDIATYPE() {
      return this.mediatype;
   }

   public void setMEDIATYPE(CDMEDIATYPEvalues value) {
      this.mediatype = value;
   }

   public String getURL() {
      return this.url;
   }

   public void setURL(String value) {
      this.url = value;
   }

   public String getSIZE() {
      return this.size;
   }

   public void setSIZE(String value) {
      this.size = value;
   }
}
