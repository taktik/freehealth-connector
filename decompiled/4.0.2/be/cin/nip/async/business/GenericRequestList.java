package be.cin.nip.async.business;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GenericRequestListType",
   propOrder = {"genericRequests"}
)
@XmlRootElement(
   name = "GenericRequestList"
)
public class GenericRequestList implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GenericRequest",
      required = true
   )
   protected List<GenericRequest> genericRequests;
   @XmlAttribute(
      name = "timestamp",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime timestamp;

   public GenericRequestList() {
   }

   public List<GenericRequest> getGenericRequests() {
      if (this.genericRequests == null) {
         this.genericRequests = new ArrayList();
      }

      return this.genericRequests;
   }

   public DateTime getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(DateTime value) {
      this.timestamp = value;
   }
}
