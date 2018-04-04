package org.taktik.connector.business.domain.vitalink;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataEntry implements Serializable {
    private Map<String,String> metadata = new HashMap<String, String>();
    private String businessDataString;
    private String dataEntryURI;
    private String reference;
    private Integer nodeVersion;

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getBusinessDataString() {
        return businessDataString;
    }

    public void setBusinessDataString(String businessDataString) {
        this.businessDataString = businessDataString;
    }

    public String getDataEntryURI() {
        return dataEntryURI;
    }

    public void setDataEntryURI(String dataEntryURI) {
        this.dataEntryURI = dataEntryURI;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getNodeVersion() {
        return nodeVersion;
    }

    public void setNodeVersion(Integer nodeVersion) {
        this.nodeVersion = nodeVersion;
    }
}