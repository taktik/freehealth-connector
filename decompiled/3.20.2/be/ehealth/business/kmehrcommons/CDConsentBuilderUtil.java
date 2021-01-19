package be.ehealth.business.kmehrcommons;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENTschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENTvalues;

public final class CDConsentBuilderUtil {
   private CDConsentBuilderUtil() {
   }

   public static CDCONSENT createCDConsent(String version, CDCONSENTvalues value) {
      CDCONSENT cd = new CDCONSENT();
      cd.setS(CDCONSENTschemes.CD_CONSENTTYPE);
      cd.setSV(version);
      cd.setValue(value);
      return cd;
   }
}
