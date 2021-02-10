package be.ehealth.businessconnector.wsconsent.builders;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.standards.kmehr.schema.v1.HcpartyType;
import java.util.List;

public interface HcPartyInfoBuilder {
   List<HcpartyType> getHcpPartiesForAuthor() throws TechnicalConnectorException;
}
