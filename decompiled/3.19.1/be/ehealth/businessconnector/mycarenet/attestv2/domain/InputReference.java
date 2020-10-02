package be.ehealth.businessconnector.mycarenet.attestv2.domain;

import be.ehealth.business.kmehrcommons.util.KmehrIdGenerator;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;

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
