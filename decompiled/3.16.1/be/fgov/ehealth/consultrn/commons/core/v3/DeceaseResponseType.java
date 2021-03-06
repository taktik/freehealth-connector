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
   name = "DeceaseResponseType",
   propOrder = {"deceaseDate", "deceasePlace"}
)
public class DeceaseResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DeceaseDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime deceaseDate;
   @XmlElement(
      name = "DeceasePlace"
   )
   protected WhereResponseType deceasePlace;

   public DateTime getDeceaseDate() {
      return this.deceaseDate;
   }

   public void setDeceaseDate(DateTime value) {
      this.deceaseDate = value;
   }

   public WhereResponseType getDeceasePlace() {
      return this.deceasePlace;
   }

   public void setDeceasePlace(WhereResponseType value) {
      this.deceasePlace = value;
   }
}
