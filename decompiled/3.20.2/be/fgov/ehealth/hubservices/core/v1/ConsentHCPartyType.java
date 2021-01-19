package be.fgov.ehealth.hubservices.core.v1;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
import java.io.Serializable;
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
   name = "ConsentHCPartyType",
   propOrder = {"hcparty", "signdate", "revokedate", "author"}
)
@XmlRootElement(
   name = "ConsentHCPartyType"
)
public class ConsentHCPartyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected HCPartyIdType hcparty;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime signdate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime revokedate;
   protected AuthorType author;

   public HCPartyIdType getHcparty() {
      return this.hcparty;
   }

   public void setHcparty(HCPartyIdType value) {
      this.hcparty = value;
   }

   public DateTime getSigndate() {
      return this.signdate;
   }

   public void setSigndate(DateTime value) {
      this.signdate = value;
   }

   public DateTime getRevokedate() {
      return this.revokedate;
   }

   public void setRevokedate(DateTime value) {
      this.revokedate = value;
   }

   public AuthorType getAuthor() {
      return this.author;
   }

   public void setAuthor(AuthorType value) {
      this.author = value;
   }
}
