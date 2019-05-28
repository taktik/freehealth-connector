package org.taktik.connector.business.mycarenet.attestv2.domain;

import org.taktik.connector.business.kmehrcommons.util.KmehrIdGenerator;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;

public final class InputReference {
   private String inputReference;

   public InputReference() throws TechnicalConnectorException {
      this.inputReference = IdGeneratorFactory.getIdGenerator("kmehr").generateId();
   }

   public InputReference(String inputReference) {
      this.inputReference = inputReference;
   }

   public String getInputReference() {
      return this.inputReference;
   }

   static {
      IdGeneratorFactory.registerDefaultImplementation("kmehr", KmehrIdGenerator.class);
   }
}
