package be.fgov.ehealth.ehbox.publication.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.ehbox.core.v3.EhboxIdentifierType;
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
   name = "",
   propOrder = {"absentFrom", "absentTo", "substitutes"}
)
public class Recipient extends EhboxIdentifierType implements Serializable {
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
   @XmlElement(
      name = "Substitute"
   )
   protected List<Substitute> substitutes;

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

   public List<Substitute> getSubstitutes() {
      if (this.substitutes == null) {
         this.substitutes = new ArrayList();
      }

      return this.substitutes;
   }
}
