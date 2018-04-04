package org.taktik.connector.business.domain.therlink;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.taktik.connector.business.domain.Error;
import org.taktik.connector.business.therlink.domain.TherapeuticLink;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 23/06/14
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class TherapeuticLinkMessage implements Serializable {
    protected boolean complete;
    private List<Error> errors = new ArrayList<>();
    private TherapeuticLink therapeuticLink;

    public TherapeuticLinkMessage() {
    }

    public TherapeuticLinkMessage(TherapeuticLink therapeuticLink) {
        this.therapeuticLink = therapeuticLink;
        this.complete = true;
    }

    public TherapeuticLink getTherapeuticLink() {
        return therapeuticLink;
    }

    public void setTherapeuticLink(TherapeuticLink therapeuticLink) {
        this.therapeuticLink = therapeuticLink;
    }

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


}
