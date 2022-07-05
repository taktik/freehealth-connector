package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.wsconsent.builders.HcPartyInfoBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.utils.SessionUtil;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public class HcPartyInfoBuilderImpl implements HcPartyInfoBuilder {
   public HcPartyInfoBuilderImpl() {
   }

   public List<HcpartyType> getHcpPartiesForAuthor() throws TechnicalConnectorException {
      ArrayList<HcpartyType> result = new ArrayList();
      HcpartyType professionalHcParty = HcPartyUtil.createProfessionalParty(SessionUtil.getNiss(), SessionUtil.getNihii11().toString(), HcPartyUtil.getAuthorKmehrQuality());
      professionalHcParty.setFirstname(SessionUtil.getFirstname());
      professionalHcParty.setFamilyname(SessionUtil.getLastname());
      result.add(professionalHcParty);
      return result;
   }
}
