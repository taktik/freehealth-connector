package be.fgov.ehealth.standards.kmehr.mycarenet.schema.v1;

import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDDRUGCNK;
import be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1.CDINNCLUSTER;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"intendedcd", "deliveredcd", "intendedname", "deliveredname"}
)
public class Substanceproduct implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected CDINNCLUSTER intendedcd;
   protected CDDRUGCNK deliveredcd;
   @XmlElement(
      required = true
   )
   protected String intendedname;
   protected String deliveredname;

   public Substanceproduct() {
   }

   public CDINNCLUSTER getIntendedcd() {
      return this.intendedcd;
   }

   public void setIntendedcd(CDINNCLUSTER value) {
      this.intendedcd = value;
   }

   public CDDRUGCNK getDeliveredcd() {
      return this.deliveredcd;
   }

   public void setDeliveredcd(CDDRUGCNK value) {
      this.deliveredcd = value;
   }

   public String getIntendedname() {
      return this.intendedname;
   }

   public void setIntendedname(String value) {
      this.intendedname = value;
   }

   public String getDeliveredname() {
      return this.deliveredname;
   }

   public void setDeliveredname(String value) {
      this.deliveredname = value;
   }
}
