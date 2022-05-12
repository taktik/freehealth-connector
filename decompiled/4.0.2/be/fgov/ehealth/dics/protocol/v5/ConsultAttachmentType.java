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
   name = "ConsultAttachmentType",
   propOrder = {"name", "templateUrl", "appendix", "formCategory", "mandatory", "additionalFields"}
)
public class ConsultAttachmentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected ConsultTextType name;
   @XmlElement(
      name = "TemplateUrl",
      required = true
   )
   protected String templateUrl;
   @XmlElement(
      name = "Appendix",
      required = true
   )
   protected AppendixType appendix;
   @XmlElement(
      name = "FormCategory",
      required = true
   )
   protected FormCategoryType formCategory;
   @XmlElement(
      name = "Mandatory"
   )
   protected Boolean mandatory;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   protected int sequenceNr;
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

   public ConsultAttachmentType() {
   }

   public ConsultTextType getName() {
      return this.name;
   }

   public void setName(ConsultTextType value) {
      this.name = value;
   }

   public String getTemplateUrl() {
      return this.templateUrl;
   }

   public void setTemplateUrl(String value) {
      this.templateUrl = value;
   }

   public AppendixType getAppendix() {
      return this.appendix;
   }

   public void setAppendix(AppendixType value) {
      this.appendix = value;
   }

   public FormCategoryType getFormCategory() {
      return this.formCategory;
   }

   public void setFormCategory(FormCategoryType value) {
      this.formCategory = value;
   }

   public Boolean isMandatory() {
      return this.mandatory;
   }

   public void setMandatory(Boolean value) {
      this.mandatory = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public int getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(int value) {
      this.sequenceNr = value;
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
