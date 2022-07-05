package be.cin.types.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DetailType",
   propOrder = {"detailCode", "detailSource", "location", "message"}
)
public class DetailType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DetailCode",
      required = true
   )
   protected String detailCode;
   @XmlElement(
      name = "DetailSource",
      required = true
   )
   protected String detailSource;
   @XmlElement(
      name = "Location"
   )
   protected String location;
   @XmlElement(
      name = "Message"
   )
   protected StringLangType message;

   public DetailType() {
   }

   public String getDetailCode() {
      return this.detailCode;
   }

   public void setDetailCode(String value) {
      this.detailCode = value;
   }

   public String getDetailSource() {
      return this.detailSource;
   }

   public void setDetailSource(String value) {
      this.detailSource = value;
   }

   public String getLocation() {
      return this.location;
   }

   public void setLocation(String value) {
      this.location = value;
   }

   public StringLangType getMessage() {
      return this.message;
   }

   public void setMessage(StringLangType value) {
      this.message = value;
   }
}
