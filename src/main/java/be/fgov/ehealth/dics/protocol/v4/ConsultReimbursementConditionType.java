package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.reimbursementlaw.submit.ReimbursementConditionKeyType;
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
   propOrder = {"expression", "attachments"}
)
public class ConsultReimbursementConditionType extends ReimbursementConditionKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Expression",
      required = true
   )
   protected String expression;
   @XmlElement(
      name = "Attachment"
   )
   protected List<ConsultAttachmentType> attachments;
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

   public String getExpression() {
      return this.expression;
   }

   public void setExpression(String value) {
      this.expression = value;
   }

   public List<ConsultAttachmentType> getAttachments() {
      if (this.attachments == null) {
         this.attachments = new ArrayList();
      }

      return this.attachments;
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
