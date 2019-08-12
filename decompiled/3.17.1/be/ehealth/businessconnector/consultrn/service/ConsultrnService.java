package be.ehealth.businessconnector.consultrn.service;

import be.ehealth.businessconnector.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import be.ehealth.businessconnector.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public interface ConsultrnService {
   SearchBySSINReply search(SAMLToken var1, SearchBySSINRequest var2) throws TechnicalConnectorException, ConsultrnIdentifyPersonException;

   SearchPhoneticReply search(SAMLToken var1, SearchPhoneticRequest var2) throws TechnicalConnectorException, ConsultrnPhoneticSearchException;

   RegisterPersonResponse registerPerson(SAMLToken var1, RegisterPersonRequest var2) throws TechnicalConnectorException, ConsultrnRegisterPersonException, ConsultrnRegisterExistingPersonException;
}
