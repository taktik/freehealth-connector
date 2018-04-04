package org.taktik.connector.business.domain.consent;

import be.fgov.ehealth.hubservices.core.v2.ConsentType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.taktik.connector.business.domain.Error;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 23/06/14
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class ConsentMessage implements Serializable {
    protected boolean complete;
    private List<Error> errors = new ArrayList<>();
    private ConsentType consent;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public ConsentType getConsent() {
        return consent;
    }

    public void setConsent(ConsentType consent) {
        this.consent = consent;
    }
}
