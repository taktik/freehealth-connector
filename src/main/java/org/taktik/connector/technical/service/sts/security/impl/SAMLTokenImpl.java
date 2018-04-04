package org.taktik.connector.technical.service.sts.security.impl;

import org.taktik.connector.technical.service.sts.security.Credential;
import org.w3c.dom.Element;

/** @deprecated */
@Deprecated
public class SAMLTokenImpl extends SAMLHolderOfKeyToken {
   public SAMLTokenImpl(Element assertion, Credential credential) {
      super(assertion, credential);
   }
}
