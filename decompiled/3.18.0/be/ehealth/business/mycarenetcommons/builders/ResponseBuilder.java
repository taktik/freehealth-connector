package be.ehealth.business.mycarenetcommons.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;
import be.ehealth.business.mycarenetdomaincommons.exception.InvalidBlobContentConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendResponseType;

public interface ResponseBuilder {
   String PROJECT_NAME_KEY = "projectname";

   String getResponse(SendResponseType var1) throws TechnicalConnectorException;

   String getResponse(be.fgov.ehealth.mycarenet.commons.protocol.v2.SendResponseType var1) throws TechnicalConnectorException;

   String getResponse(SendResponseType var1, boolean var2) throws TechnicalConnectorException;

   void validateHash(Blob var1) throws TechnicalConnectorException, InvalidBlobContentConnectorException;

   /** @deprecated */
   @Deprecated
   void validateXades(SendResponseType var1, Blob var2) throws TechnicalConnectorException, InvalidBlobContentConnectorException;

   void validateXades(SendResponseType var1) throws TechnicalConnectorException, InvalidBlobContentConnectorException;

   String getResponse(be.fgov.ehealth.mycarenet.commons.protocol.v2.SendResponseType var1, boolean var2) throws TechnicalConnectorException;
}
