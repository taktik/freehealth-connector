package be.ehealth.technicalconnector.service.sts.security.impl;

import be.ehealth.technicalconnector.service.sts.security.Credential;
import org.w3c.dom.Element;

/** @deprecated */
@Deprecated
public class SAMLTokenImpl extends SAMLHolderOfKeyToken {
   public SAMLTokenImpl(Element assertion, Credential credential) {
      super(assertion, credential);
   }
}
