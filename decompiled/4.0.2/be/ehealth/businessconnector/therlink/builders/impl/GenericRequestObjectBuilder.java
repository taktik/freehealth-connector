package be.ehealth.businessconnector.therlink.builders.impl;

import be.ehealth.business.kmehrcommons.HcPartyUtil;
import be.ehealth.businessconnector.therlink.domain.HcParty;
import be.ehealth.businessconnector.therlink.exception.TherLinkBusinessConnectorException;
import be.ehealth.businessconnector.therlink.mappers.HcPartyMapper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericRequestObjectBuilder extends AbstractRequestObjectBuilderImpl {
   public static final String PROJECT_NAME = "therlink";

   public GenericRequestObjectBuilder() throws TherLinkBusinessConnectorException, TechnicalConnectorException, InstantiationException {
   }

   public List<HcParty> getAuthorHcParties() throws TechnicalConnectorException, TherLinkBusinessConnectorException, InstantiationException {
      return this.map(HcPartyUtil.createAuthorHcParties("therlink"));
   }

   private List<HcParty> map(List<HcpartyType> authorHcParties) {
      ArrayList<HcParty> mappedList = new ArrayList();
      Iterator var3 = authorHcParties.iterator();

      while(var3.hasNext()) {
         HcpartyType hcpartyType = (HcpartyType)var3.next();
         mappedList.add(HcPartyMapper.mapHcParty(hcpartyType));
      }

      return mappedList;
   }
}
