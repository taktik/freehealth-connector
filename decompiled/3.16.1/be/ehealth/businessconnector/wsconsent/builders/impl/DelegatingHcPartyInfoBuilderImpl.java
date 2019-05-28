package be.ehealth.businessconnector.wsconsent.builders.impl;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.wsconsent.builders.HcPartyInfoBuilder;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.List;

public class DelegatingHcPartyInfoBuilderImpl implements HcPartyInfoBuilder {
   public List<HcpartyType> getHcpPartiesForAuthor() throws TechnicalConnectorException {
      return HcPartyUtil.createAuthorHcParties("wsconsent");
   }
}
