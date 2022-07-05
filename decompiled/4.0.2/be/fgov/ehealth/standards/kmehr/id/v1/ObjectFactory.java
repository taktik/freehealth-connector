package be.fgov.ehealth.standards.kmehr.id.v1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public IDKMEHR createIDKMEHR() {
      return new IDKMEHR();
   }

   public IDHCPARTY createIDHCPARTY() {
      return new IDHCPARTY();
   }

   public IDPATIENT createIDPATIENT() {
      return new IDPATIENT();
   }

   public INSS createINSS() {
      return new INSS();
   }

   public IDINSURANCE createIDINSURANCE() {
      return new IDINSURANCE();
   }

   public IDPROFESSION createIDPROFESSION() {
      return new IDPROFESSION();
   }
}
