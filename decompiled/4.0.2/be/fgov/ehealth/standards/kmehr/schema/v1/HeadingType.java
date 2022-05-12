package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHEADING;
import be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "headingType",
   propOrder = {"confidentiality", "ids", "cds", "heading", "item", "text", "lnk"}
)
public class HeadingType implements Serializable {
   private static final long serialVersionUID = 1L;
   protected ConfidentialityType confidentiality;
   @XmlElement(
      name = "id",
      required = true
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDHEADING> cds;
   protected List<HeadingType> heading;
   protected List<ItemType> item;
   protected List<TextType> text;
   protected List<LnkType> lnk;

   public HeadingType() {
   }

   public ConfidentialityType getConfidentiality() {
      return this.confidentiality;
   }

   public void setConfidentiality(ConfidentialityType value) {
      this.confidentiality = value;
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public List<CDHEADING> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public List<HeadingType> getHeading() {
      if (this.heading == null) {
         this.heading = new ArrayList();
      }

      return this.heading;
   }

   public List<ItemType> getItem() {
      if (this.item == null) {
         this.item = new ArrayList();
      }

      return this.item;
   }

   public List<TextType> getText() {
      if (this.text == null) {
         this.text = new ArrayList();
      }

      return this.text;
   }

   public List<LnkType> getLnk() {
      if (this.lnk == null) {
         this.lnk = new ArrayList();
      }

      return this.lnk;
   }
}
