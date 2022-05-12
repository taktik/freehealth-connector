package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
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
   name = "SubstituteType",
   propOrder = {"absentFrom", "absentTo"}
)
public class SubstituteType extends BoxIdType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AbsentFrom",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime absentFrom;
   @XmlElement(
      name = "AbsentTo",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime absentTo;

   public SubstituteType() {
   }

   public DateTime getAbsentFrom() {
      return this.absentFrom;
   }

   public void setAbsentFrom(DateTime value) {
      this.absentFrom = value;
   }

   public DateTime getAbsentTo() {
      return this.absentTo;
   }

   public void setAbsentTo(DateTime value) {
      this.absentTo = value;
   }
}
