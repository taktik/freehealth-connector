package be.fgov.ehealth.consultrn._1_0.core;

import be.fgov.ehealth.commons._1_0.core.LocalisedString;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStateType",
   propOrder = {"code", "descriptions", "localisation", "partner"}
)
public class CivilStateType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Code",
      required = true
   )
   protected BigInteger code;
   @XmlElement(
      name = "Description"
   )
   protected List<LocalisedString> descriptions;
   @XmlElement(
      name = "Localisation"
   )
   protected WhereType localisation;
   @XmlElement(
      name = "Partner"
   )
   protected InhabitantType partner;
   @XmlAttribute(
      name = "ModificationDate"
   )
   protected String modificationDate;
   @XmlAttribute(
      name = "Origin"
   )
   protected OriginType origin;

   public BigInteger getCode() {
      return this.code;
   }

   public void setCode(BigInteger value) {
      this.code = value;
   }

   public List<LocalisedString> getDescriptions() {
      if (this.descriptions == null) {
         this.descriptions = new ArrayList();
      }

      return this.descriptions;
   }

   public WhereType getLocalisation() {
      return this.localisation;
   }

   public void setLocalisation(WhereType value) {
      this.localisation = value;
   }

   public InhabitantType getPartner() {
      return this.partner;
   }

   public void setPartner(InhabitantType value) {
      this.partner = value;
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
