package be.business.connector.core.services;

public class GenericWebserviceRequest {
   private Object request;
   private Class<?> requestType;
   private String endpoint;
   private String serviceName;
   private boolean addLoggingHandler;
   private boolean addSoapFaultHandler;
   private boolean addMustUnderstandHandler;
   private boolean addInsurabilityHandler;
   private String soapAction;

   public Object getRequest() {
      return this.request;
   }

   public void setRequest(Object request) {
      this.request = request;
   }

   public Class<?> getRequestType() {
      return this.requestType;
   }

   public void setRequestType(Class<?> requestType) {
      this.requestType = requestType;
   }

   public String getEndpoint() {
      return this.endpoint;
   }

   public void setEndpoint(String endpoint) {
      this.endpoint = endpoint;
   }

   public String getServiceName() {
      return this.serviceName;
   }

   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   public boolean isAddLoggingHandler() {
      return this.addLoggingHandler;
   }

   public void setAddLoggingHandler(boolean addLoggingHandler) {
      this.addLoggingHandler = addLoggingHandler;
   }

   public boolean isAddSoapFaultHandler() {
      return this.addSoapFaultHandler;
   }

   public void setAddSoapFaultHandler(boolean addSoapFaultHandler) {
      this.addSoapFaultHandler = addSoapFaultHandler;
   }

   public boolean isAddMustUnderstandHandler() {
      return this.addMustUnderstandHandler;
   }

   public void setAddMustUnderstandHandler(boolean addMustUnderstandHandler) {
      this.addMustUnderstandHandler = addMustUnderstandHandler;
   }

   public boolean isAddInsurabilityHandler() {
      return this.addInsurabilityHandler;
   }

   public void setAddInsurabilityHandler(boolean addInsurabilityHandler) {
      this.addInsurabilityHandler = addInsurabilityHandler;
   }

   public String getSoapAction() {
      return this.soapAction;
   }

   public void setSoapAction(String soapAction) {
      this.soapAction = soapAction;
   }
}
