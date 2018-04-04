package oasis.names.tc.dss._1_0.core.schema;

import org.taktik.connector.technical.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UseVerificationTimeType",
   propOrder = {"any", "specificTime", "currentTime"}
)
public class UseVerificationTimeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAnyElement(
      lax = true
   )
   protected Object any;
   @XmlElement(
      name = "SpecificTime",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime specificTime;
   @XmlElement(
      name = "CurrentTime"
   )
   protected Object currentTime;

   public Object getAny() {
      return this.any;
   }

   public void setAny(Object value) {
      this.any = value;
   }

   public DateTime getSpecificTime() {
      return this.specificTime;
   }

   public void setSpecificTime(DateTime value) {
      this.specificTime = value;
   }

   public Object getCurrentTime() {
      return this.currentTime;
   }

   public void setCurrentTime(Object value) {
      this.currentTime = value;
   }
}
