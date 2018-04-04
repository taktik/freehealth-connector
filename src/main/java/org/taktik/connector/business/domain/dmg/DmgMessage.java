package org.taktik.connector.business.domain.dmg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.taktik.connector.business.domain.Error;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */
public class DmgMessage implements Serializable {
    protected boolean complete;
    private List<Error> errors = new ArrayList<>();

    public DmgMessage() {
    }

    public DmgMessage(boolean complete) {
        this.complete = complete;
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
