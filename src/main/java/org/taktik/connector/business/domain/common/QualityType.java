package org.taktik.connector.business.domain.common;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 07/12/12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
public class QualityType implements Serializable {
    String quality;
    IdentifierType identifierType;

    public QualityType() {
    }

    public QualityType(String quality, IdentifierType identfierType) {
        this.quality = quality;
        this.identifierType = identfierType;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }
}
