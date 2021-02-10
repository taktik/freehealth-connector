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
   name = "ConsultLegalReferenceType",
   propOrder = {"title", "type", "firstPublishedOn", "lastModifiedOn", "legalReferenceTraces", "additionalFields", "formalInterpretations", "legalTexts", "legalReferences"}
)
public class ConsultLegalReferenceType implements Serializable {
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
      name = "FirstPublishedOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime firstPublishedOn;
   @XmlElement(
      name = "LastModifiedOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime lastModifiedOn;
   @XmlElement(
      name = "LegalReferenceTrace"
   )
   protected List<ConsultLegalReferenceTraceType> legalReferenceTraces;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "FormalInterpretation"
   )
   protected List<ConsultFormalInterpretationType> formalInterpretations;
   @XmlElement(
      name = "LegalText"
   )
   protected List<ConsultLegalTextType> legalTexts;
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

   public DateTime getFirstPublishedOn() {
      return this.firstPublishedOn;
   }

   public void setFirstPublishedOn(DateTime value) {
      this.firstPublishedOn = value;
   }

   public DateTime getLastModifiedOn() {
      return this.lastModifiedOn;
   }

   public void setLastModifiedOn(DateTime value) {
      this.lastModifiedOn = value;
   }

   public List<ConsultLegalReferenceTraceType> getLegalReferenceTraces() {
      if (this.legalReferenceTraces == null) {
         this.legalReferenceTraces = new ArrayList();
      }

      return this.legalReferenceTraces;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultFormalInterpretationType> getFormalInterpretations() {
      if (this.formalInterpretations == null) {
         this.formalInterpretations = new ArrayList();
      }

      return this.formalInterpretations;
   }

   public List<ConsultLegalTextType> getLegalTexts() {
      if (this.legalTexts == null) {
         this.legalTexts = new ArrayList();
      }

      return this.legalTexts;
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
