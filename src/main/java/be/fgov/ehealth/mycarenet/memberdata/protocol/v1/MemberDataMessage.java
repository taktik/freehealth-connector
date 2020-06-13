package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse;
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberDataMessage implements Serializable {
    private CommonOutput commonOutput;
    private MycarenetConversation mycarenetConversation;
    protected boolean complete;
    private List<MycarenetError> errors = new ArrayList<>();
    private String io;
    private String appliesTo;
    private String reference;
    private String valueHash;


    public CommonOutput getCommonOutput() {
        return commonOutput;
    }

    public void setCommonOutput(CommonOutput commonOutput) {
        this.commonOutput = commonOutput;
    }

    public MycarenetConversation getMycarenetConversation() {
        return mycarenetConversation;
    }

    public void setMycarenetConversation(MycarenetConversation mycarenetConversation) {
        this.mycarenetConversation = mycarenetConversation;
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

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
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
}
