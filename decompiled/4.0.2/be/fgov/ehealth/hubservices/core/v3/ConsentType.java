package be.fgov.ehealth.hubservices.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import be.fgov.ehealth.standards.kmehr.schema.v1.AuthorType;
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
   name = "ConsentType",
   propOrder = {"patient", "cds", "signdate", "revokedate", "author"}
)
@XmlRootElement(
   name = "ConsentType"
)
public class ConsentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected PatientIdType patient;
   @XmlElement(
      name = "cd",
      required = true
   )
   protected List<CDCONSENT> cds;
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

   public ConsentType() {
   }

   public PatientIdType getPatient() {
      return this.patient;
   }

   public void setPatient(PatientIdType value) {
      this.patient = value;
   }

   public List<CDCONSENT> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
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
