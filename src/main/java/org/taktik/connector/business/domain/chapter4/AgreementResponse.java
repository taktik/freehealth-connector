package org.taktik.connector.business.domain.chapter4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/06/13
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class AgreementResponse implements Serializable {

    private boolean acknowledged;
    private Collection<Problem> warnings;
    private Collection<Problem> errors;
    private byte[] content;

    List<AgreementTransaction> transactions = new ArrayList<AgreementTransaction>();

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public void setWarnings(Collection<Problem> warnings) {
        this.warnings = warnings;
    }

    public void setErrors(Collection<Problem> errors) {
        this.errors = errors;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public Collection<Problem> getWarnings() {
        return warnings;
    }

    public Collection<Problem> getErrors() {
        return errors;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public AgreementTransaction addTransaction(AgreementTransaction t) {
        transactions.add(t);
        return t;
    }

    public List<AgreementTransaction> getTransactions() {
        return transactions;
    }

}
