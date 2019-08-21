package be.fgov.ehealth.consultrn.commons.core.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityType",
   propOrder = {"nationalityCode", "nationalityDescriptions", "startDate", "endDate"}
)
public class NationalityType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NationalityCode",
      required = true
   )
   protected String nationalityCode;
   @XmlElement(
      name = "NationalityDescription"
   )
   protected List<NameType> nationalityDescriptions;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public String getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(String value) {
      this.nationalityCode = value;
   }

   public List<NameType> getNationalityDescriptions() {
      if (this.nationalityDescriptions == null) {
         this.nationalityDescriptions = new ArrayList();
      }

      return this.nationalityDescriptions;
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
