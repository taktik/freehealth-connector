package be.ehealth.businessconnector.consultrn.session;

import be.ehealth.businessconnector.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException;
import be.ehealth.businessconnector.consultrn.exception.manageperson.ConsultrnRegisterPersonException;
import be.ehealth.businessconnector.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply;
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest;
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse;

public interface ConsultrnService {
   SearchBySSINReply search(SearchBySSINRequest var1) throws ConsultrnIdentifyPersonException, TechnicalConnectorException;

   SearchPhoneticReply search(SearchPhoneticRequest var1) throws ConsultrnPhoneticSearchException, TechnicalConnectorException;

   RegisterPersonResponse registerPerson(RegisterPersonRequest var1) throws ConsultrnRegisterPersonException, TechnicalConnectorException, ConsultrnRegisterExistingPersonException;
}
