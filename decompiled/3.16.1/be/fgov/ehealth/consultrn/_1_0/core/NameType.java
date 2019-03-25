package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameType",
   propOrder = {"first", "middle", "last"}
)
public class NameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "First"
   )
   protected String first;
   @XmlElement(
      name = "Middle"
   )
   protected String middle;
   @XmlElement(
      name = "Last",
      required = true
   )
   protected String last;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public String getFirst() {
      return this.first;
   }

   public void setFirst(String value) {
      this.first = value;
   }

   public String getMiddle() {
      return this.middle;
   }

   public void setMiddle(String value) {
      this.middle = value;
   }

   public String getLast() {
      return this.last;
   }

   public void setLast(String value) {
      this.last = value;
   }

   public String getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(String value) {
      this.modificationDate = value;
   }

   public OriginType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OriginType value) {
      this.origin = value;
   }
}
