package be.fgov.ehealth.samcivics.schemas.v2;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import java.math.BigInteger;
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
   name = "FindPublicCNKRequestType",
   propOrder = {"cnk", "deliveryEnvironment", "version", "startDate"}
)
@XmlRootElement(
   name = "FindPublicCNKRequest"
)
public class FindPublicCNKRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CNK"
   )
   protected long cnk;
   @XmlElement(
      name = "DeliveryEnvironment",
      required = true
   )
   protected String deliveryEnvironment;
   @XmlElement(
      name = "Version"
   )
   protected BigInteger version;
   @XmlElement(
      name = "StartDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;

   public FindPublicCNKRequest() {
   }

   public long getCNK() {
      return this.cnk;
   }

   public void setCNK(long value) {
      this.cnk = value;
   }

   public String getDeliveryEnvironment() {
      return this.deliveryEnvironment;
   }

   public void setDeliveryEnvironment(String value) {
      this.deliveryEnvironment = value;
   }

   public BigInteger getVersion() {
      return this.version;
   }

   public void setVersion(BigInteger value) {
      this.version = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }
}
