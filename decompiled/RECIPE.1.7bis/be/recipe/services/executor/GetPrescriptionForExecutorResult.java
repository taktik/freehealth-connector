package be.recipe.services.executor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class GetPrescriptionForExecutorResult {
   private byte[] getPrescriptionForExecutorResultSealed;
   private GetPrescriptionForExecutorUnsealedResultPart getPrescriptionForExecutorUnsealedResultPart;

   public GetPrescriptionForExecutorUnsealedResultPart getGetPrescriptionForExecutorUnsealedResultPart() {
      return this.getPrescriptionForExecutorUnsealedResultPart;
   }

   public byte[] getGetPrescriptionForExecutorResultSealed() {
      return this.getPrescriptionForExecutorResultSealed;
   }

   public void setGetPrescriptionForExecutorResultSealed(byte[] getPrescriptionForExecutorResultSealed) {
      this.getPrescriptionForExecutorResultSealed = getPrescriptionForExecutorResultSealed;
   }

   public void setGetPrescriptionForExecutorUnsealedResultPart(GetPrescriptionForExecutorUnsealedResultPart getPrescriptionForExecutorUnsealedResultPart) {
      this.getPrescriptionForExecutorUnsealedResultPart = getPrescriptionForExecutorUnsealedResultPart;
   }
}
