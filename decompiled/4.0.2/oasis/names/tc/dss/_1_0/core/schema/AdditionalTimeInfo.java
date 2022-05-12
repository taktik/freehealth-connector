package oasis.names.tc.dss._1_0.core.schema;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AdditionalTimeInfoType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "AdditionalTimeInfo"
)
public class AdditionalTimeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime value;
   @XmlAttribute(
      name = "Type",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;
   @XmlAttribute(
      name = "Ref"
   )
   protected String ref;

   public AdditionalTimeInfo() {
   }

   public DateTime getValue() {
      return this.value;
   }

   public void setValue(DateTime value) {
      this.value = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getRef() {
      return this.ref;
   }

   public void setRef(String value) {
      this.ref = value;
   }
}
