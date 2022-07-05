package be.fgov.ehealth.consultrn.commons.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenderType",
   propOrder = {"genderCode", "startDate"}
)
public class GenderType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenderCode",
      required = true
   )
   protected String genderCode;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;

   public GenderType() {
   }

   public String getGenderCode() {
      return this.genderCode;
   }

   public void setGenderCode(String value) {
      this.genderCode = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }
}
