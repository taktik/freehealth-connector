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
   name = "BirthResponseType",
   propOrder = {"birthDate", "birthPlace"}
)
public class BirthResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BirthDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime birthDate;
   @XmlElement(
      name = "BirthPlace"
   )
   protected WhereResponseType birthPlace;

   public DateTime getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(DateTime value) {
      this.birthDate = value;
   }

   public WhereResponseType getBirthPlace() {
      return this.birthPlace;
   }

   public void setBirthPlace(WhereResponseType value) {
      this.birthPlace = value;
   }
}
