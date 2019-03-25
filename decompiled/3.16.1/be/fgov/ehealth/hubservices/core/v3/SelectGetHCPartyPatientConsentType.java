package be.fgov.ehealth.hubservices.core.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDTHERAPEUTICLINK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SelectGetHCPartyPatientConsentType",
   propOrder = {"patientsAndHcparties", "cds", "begindate", "enddate"}
)
@XmlRootElement(
   name = "SelectGetHCPartyPatientConsentType"
)
public class SelectGetHCPartyPatientConsentType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElements({@XmlElement(
   name = "patient",
   type = PatientIdType.class
), @XmlElement(
   name = "hcparty",
   type = HCPartyIdType.class
)})
   protected List<Serializable> patientsAndHcparties;
   @XmlElement(
      name = "cd"
   )
   protected List<CDTHERAPEUTICLINK> cds;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime begindate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime enddate;

   public List<Serializable> getPatientsAndHcparties() {
      if (this.patientsAndHcparties == null) {
         this.patientsAndHcparties = new ArrayList();
      }

      return this.patientsAndHcparties;
   }

   public List<CDTHERAPEUTICLINK> getCds() {
      if (this.cds == null) {
         this.cds = new ArrayList();
      }

      return this.cds;
   }

   public DateTime getBegindate() {
      return this.begindate;
   }

   public void setBegindate(DateTime value) {
      this.begindate = value;
   }

   public DateTime getEnddate() {
      return this.enddate;
   }

   public void setEnddate(DateTime value) {
      this.enddate = value;
   }
}
