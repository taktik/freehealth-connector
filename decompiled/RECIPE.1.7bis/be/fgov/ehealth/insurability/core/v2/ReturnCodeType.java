package be.fgov.ehealth.insurability.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ReturnCodeType",
   propOrder = {"major", "minor", "detail"}
)
public class ReturnCodeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Major",
      required = true
   )
   protected String major;
   @XmlElement(
      name = "Minor",
      required = true
   )
   protected String minor;
   @XmlElement(
      name = "Detail",
      required = true
   )
   protected String detail;

   public String getMajor() {
      return this.major;
   }

   public void setMajor(String value) {
      this.major = value;
   }

   public String getMinor() {
      return this.minor;
   }

   public void setMinor(String value) {
      this.minor = value;
   }

   public String getDetail() {
      return this.detail;
   }

   public void setDetail(String value) {
      this.detail = value;
   }
}
