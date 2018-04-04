package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
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
   propOrder = {"ooOId", "startDate", "endDate", "substitutes"}
)
public class OoO implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "OoOId",
      required = true
   )
   protected String ooOId;
   @XmlElement(
      name = "StartDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlElement(
      name = "EndDate",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;
   @XmlElement(
      name = "Substitute"
   )
   protected List<BoxIdType> substitutes;

   public String getOoOId() {
      return this.ooOId;
   }

   public void setOoOId(String value) {
      this.ooOId = value;
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

   public List<BoxIdType> getSubstitutes() {
      if (this.substitutes == null) {
         this.substitutes = new ArrayList();
      }

      return this.substitutes;
   }
}
