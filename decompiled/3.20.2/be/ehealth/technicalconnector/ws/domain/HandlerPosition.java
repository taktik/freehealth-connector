package be.ehealth.technicalconnector.ws.domain;

public enum HandlerPosition {
   BEFORE("BeforeSecurityChain"),
   SECURITY("SecurityChain"),
   AFTER("AfterSecurityChain");

   private String name;

   private HandlerPosition(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
