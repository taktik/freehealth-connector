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
   name = "ConsultReimbursementConditionType",
   propOrder = {"expression", "additionalFields", "attachments"}
)
public class ConsultReimbursementConditionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Expression",
      required = true
   )
   protected String expression;
   @XmlElement(
      name = "AdditionalFields"
   )
   protected List<AdditionalFieldsType> additionalFields;
   @XmlElement(
      name = "Attachment"
   )
   protected List<ConsultAttachmentType> attachments;
   @XmlAttribute(
      name = "LegalTextRelativePath",
      required = true
   )
   protected String legalTextRelativePath;
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

   public ConsultReimbursementConditionType() {
   }

   public String getExpression() {
      return this.expression;
   }

   public void setExpression(String value) {
      this.expression = value;
   }

   public List<AdditionalFieldsType> getAdditionalFields() {
      if (this.additionalFields == null) {
         this.additionalFields = new ArrayList();
      }

      return this.additionalFields;
   }

   public List<ConsultAttachmentType> getAttachments() {
      if (this.attachments == null) {
         this.attachments = new ArrayList();
      }

      return this.attachments;
   }

   public String getLegalTextRelativePath() {
      return this.legalTextRelativePath;
   }

   public void setLegalTextRelativePath(String value) {
      this.legalTextRelativePath = value;
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
