package be.fgov.ehealth.dics.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit.AttachmentKeyType;
import java.io.Serializable;
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
   propOrder = {"name", "templateUrl", "appendix", "formCategory"}
)
public class ConsultAttachmentType extends AttachmentKeyType implements Serializable {
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
