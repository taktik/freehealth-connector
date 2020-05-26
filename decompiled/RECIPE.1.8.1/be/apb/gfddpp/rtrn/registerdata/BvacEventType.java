package be.apb.gfddpp.rtrn.registerdata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "bvacEventType",
   propOrder = {"sguid", "bvacDocument"}
)
public class BvacEventType {
   @XmlElement(
      required = true
   )
   protected String sguid;
   @XmlElement(
      required = true
   )
   protected BvacDocument bvacDocument;

   public String getSguid() {
      return this.sguid;
   }

   public void setSguid(String value) {
      this.sguid = value;
   }

   public BvacDocument getBvacDocument() {
      return this.bvacDocument;
   }

   public void setBvacDocument(BvacDocument value) {
      this.bvacDocument = value;
   }
}
