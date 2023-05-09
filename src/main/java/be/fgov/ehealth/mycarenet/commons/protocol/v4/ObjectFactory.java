package be.fgov.ehealth.mycarenet.commons.protocol.v4;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
  public be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType createSendRequestType() {
    return new SendRequestType();
  }

  public be.fgov.ehealth.mycarenet.commons.protocol.v4.SendResponseType createSendResponseType() {
    return new SendResponseType();
  }

  public be.fgov.ehealth.mycarenet.commons.protocol.v4.ResponseReturnType createResponseReturnType() {
    return new ResponseReturnType();
  }
}
