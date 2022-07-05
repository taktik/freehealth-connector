package be.fgov.ehealth.dics.protocol.v4;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v4.reimbursementlaw.submit.ParameterValueType;
import be.fgov.ehealth.dics.core.v4.reimbursementlaw.submit.ReimbursementTermKeyType;
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
   name = "ConsultReimbursementTermType",
   propOrder = {"valueUnit", "parameterName"}
)
public class ConsultReimbursementTermType extends ReimbursementTermKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ValueUnit",
      required = true
   )
   protected ParameterValueType valueUnit;
   @XmlElement(
      name = "ParameterName",
      required = true
   )
   protected String parameterName;
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

   public ConsultReimbursementTermType() {
   }

   public ParameterValueType getValueUnit() {
      return this.valueUnit;
   }

   public void setValueUnit(ParameterValueType value) {
      this.valueUnit = value;
   }

   public String getParameterName() {
      return this.parameterName;
   }

   public void setParameterName(String value) {
      this.parameterName = value;
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
