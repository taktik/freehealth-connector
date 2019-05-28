package oasis.names.tc.dss._1_0.core.schema;

import be.ehealth.technicalconnector.adapter.XmlDateTimeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "VerificationTimeInfoType",
   propOrder = {"verificationTime", "additionalTimeInfos"}
)
@XmlRootElement(
   name = "VerificationTimeInfo"
)
public class VerificationTimeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "VerificationTime",
      required = true,
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateTimeAdapter.class)
   @XmlSchemaType(
      name = "dateTime"
   )
   protected DateTime verificationTime;
   @XmlElement(
      name = "AdditionalTimeInfo"
   )
   protected List<AdditionalTimeInfo> additionalTimeInfos;

   public DateTime getVerificationTime() {
      return this.verificationTime;
   }

   public void setVerificationTime(DateTime value) {
      this.verificationTime = value;
   }

   public List<AdditionalTimeInfo> getAdditionalTimeInfos() {
      if (this.additionalTimeInfos == null) {
         this.additionalTimeInfos = new ArrayList();
      }

      return this.additionalTimeInfos;
   }
}
