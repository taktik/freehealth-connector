package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "HistoryType",
   propOrder = {"source", "modificationDate", "effectuationDate"}
)
@XmlSeeAlso({NameHistoryType.class, GenderHistoryType.class, BirthHistoryType.class, CivilstateHistoryType.class, DeceaseHistoryType.class, AddressHistoryType.class, NationalityHistoryType.class, FamilyCompositionHistoryType.class})
public class HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Source"
   )
   @XmlSchemaType(
      name = "string"
   )
   protected OriginType source;
   @XmlElement(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlElement(
      name = "EffectuationDate"
   )
   protected String effectuationDate;

   public HistoryType() {
   }

   public OriginType getSource() {
      return this.source;
   }

   public void setSource(OriginType value) {
      this.source = value;
   }

   public String getModificationDate() {
      return this.modificationDate;
   }

   public void setModificationDate(String value) {
      this.modificationDate = value;
   }

   public String getEffectuationDate() {
      return this.effectuationDate;
   }

   public void setEffectuationDate(String value) {
      this.effectuationDate = value;
   }
}
