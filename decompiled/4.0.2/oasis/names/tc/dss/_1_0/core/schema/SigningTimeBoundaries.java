package oasis.names.tc.dss._1_0.core.schema;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
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
   name = "",
   propOrder = {"lowerBoundary", "upperBoundary"}
)
public class SigningTimeBoundaries implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "LowerBoundary",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime lowerBoundary;
   @XmlElement(
      name = "UpperBoundary",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime upperBoundary;

   public SigningTimeBoundaries() {
   }

   public DateTime getLowerBoundary() {
      return this.lowerBoundary;
   }

   public void setLowerBoundary(DateTime value) {
      this.lowerBoundary = value;
   }

   public DateTime getUpperBoundary() {
      return this.upperBoundary;
   }

   public void setUpperBoundary(DateTime value) {
      this.upperBoundary = value;
   }
}
