package be.ehealth.businessconnector.therlink.util;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.mappers.HcPartyMapper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;

public final class ConfigReader {
   public static HcParty getCareProvider() throws TechnicalConnectorException {
      HcpartyType hcpartyFromConfig = HcPartyUtil.createSingleHcpartyFromConfig(TherlinkConfigKeys.THERLINK_CAREPROVIDER_HCPARTY.getKey());
      return HcPartyMapper.mapHcParty(hcpartyFromConfig);
   }
}
