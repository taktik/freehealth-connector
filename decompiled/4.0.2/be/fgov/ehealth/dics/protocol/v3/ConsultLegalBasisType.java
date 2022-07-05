package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit.LegalBasisKeyType;
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
   propOrder = {"title", "type", "effectiveOn", "legalReferences"}
)
public class ConsultLegalBasisType extends LegalBasisKeyType implements Serializable {
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
      name = "LegalReference"
   )
   protected List<ConsultRecursiveLegalReferenceType> legalReferences;
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

   public ConsultLegalBasisType() {
   }

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

   public List<ConsultRecursiveLegalReferenceType> getLegalReferences() {
      if (this.legalReferences == null) {
         this.legalReferences = new ArrayList();
      }

      return this.legalReferences;
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
