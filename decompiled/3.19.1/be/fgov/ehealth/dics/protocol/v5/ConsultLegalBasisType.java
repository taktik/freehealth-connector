package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultLegalBasisType",
   propOrder = {"title", "type", "effectiveOn", "additionalFields", "legalReferences"}
)
public class ConsultLegalBasisType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Title",
      required = true
   )
   protected ConsultTextType title;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "EffectiveOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime effectiveOn;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "LegalReference"
   )
   protected List<ConsultLegalReferenceType> legalReferences;
   @XmlAttribute(
      name = "Key",
      required = true
   )
   protected String key;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultTextType getTitle() {
      return this.title;
   }

   public void setTitle(ConsultTextType value) {
      this.title = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public DateTime getEffectiveOn() {
      return this.effectiveOn;
   }

   public void setEffectiveOn(DateTime value) {
      this.effectiveOn = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultLegalReferenceType> getLegalReferences() {
      if (this.legalReferences == null) {
         this.legalReferences = new ArrayList();
      }

      return this.legalReferences;
   }

   public String getKey() {
      return this.key;
   }

   public void setKey(String value) {
      this.key = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
