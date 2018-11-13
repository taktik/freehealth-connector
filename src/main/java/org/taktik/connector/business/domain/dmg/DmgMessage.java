package org.taktik.connector.business.domain.dmg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */
public class DmgMessage implements Serializable {

    private CommonOutput commonOutput;
    private MycarenetConversation mycarenetConversation;
    protected boolean complete;
    private List<MycarenetError> errors = new ArrayList<>();
    private String io;
    private String appliesTo;
    private String reference;
    private String valueHash;

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

    public List<MycarenetError> getErrors() {
        return errors;
    }

    public void setErrors(List<MycarenetError> errors) {
        this.errors = errors;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getValueHash() {
        return valueHash;
    }

    public void setValueHash(String valueHash) {
        this.valueHash = valueHash;
    }

    public void setCommonOutput(CommonOutput commonOutput){
        this.commonOutput = commonOutput;
    }

    public CommonOutput getCommonOutput(){
        return this.commonOutput;
    }

    public void setMycarenetConversation(MycarenetConversation mycarenetConversation){
        this.mycarenetConversation = mycarenetConversation;
    }

    public MycarenetConversation getMycarenetConversation(){
        return this.mycarenetConversation;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }
}
