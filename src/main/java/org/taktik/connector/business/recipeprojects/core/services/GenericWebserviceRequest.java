package org.taktik.connector.business.recipeprojects.core.services;

/**
 * @author Liesje Demuynck.
 */
public class GenericWebserviceRequest {
    private Object request;
    private Class<?> requestType;
    private String endpoint;
    private String serviceName;
    private boolean addLoggingHandler;
    private boolean addSoapFaultHandler;
    private boolean addMustUnderstandHandler;
    private boolean addInsurabilityHandler;

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Class<?> getRequestType() {
        return requestType;
    }

    public void setRequestType(Class<?> requestType) {
        this.requestType = requestType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isAddLoggingHandler() {
        return addLoggingHandler;
    }

    public void setAddLoggingHandler(boolean addLoggingHandler) {
        this.addLoggingHandler = addLoggingHandler;
    }

    public boolean isAddSoapFaultHandler() {
        return addSoapFaultHandler;
    }

    public void setAddSoapFaultHandler(boolean addSoapFaultHandler) {
        this.addSoapFaultHandler = addSoapFaultHandler;
    }

    public boolean isAddMustUnderstandHandler() {
        return addMustUnderstandHandler;
    }

    public void setAddMustUnderstandHandler(boolean addMustUnderstandHandler) {
        this.addMustUnderstandHandler = addMustUnderstandHandler;
    }

    public boolean isAddInsurabilityHandler() {
        return addInsurabilityHandler;
    }

    public void setAddInsurabilityHandler(boolean addInsurabilityHandler) {
        this.addInsurabilityHandler = addInsurabilityHandler;
    }

}
