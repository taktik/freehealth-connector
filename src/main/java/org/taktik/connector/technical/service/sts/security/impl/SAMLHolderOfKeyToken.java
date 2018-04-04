package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.service.sts.security.Credential;
import org.w3c.dom.Element;

public class SAMLHolderOfKeyToken extends AbstractSAMLToken {
   public SAMLHolderOfKeyToken(Element assertion, Credential credential) {
      super(assertion, credential);
   }
}
