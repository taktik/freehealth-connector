package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.fgov.ehealth.hubservices.core.v2.HCPartyIdType;
import java.util.ArrayList;
import java.util.List;

public class TherlinkBuilderUtil {
   public static List<HcParty> putInList(HcParty hcParty) {
      List<HcParty> result = new ArrayList();
      result.add(hcParty);
      return result;
   }

   public static HCPartyIdType retrieveFirstHCPartyIdTypeInList(List<HCPartyIdType> hcparties) {
      return hcparties != null && !hcparties.isEmpty() ? (HCPartyIdType)hcparties.get(0) : null;
   }
}
