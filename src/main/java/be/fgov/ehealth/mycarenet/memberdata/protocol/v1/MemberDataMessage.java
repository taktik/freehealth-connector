package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.cin.types.v1.FaultType;
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse;
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberDataMessage implements Serializable {
    private CommonOutput commonOutput;
    protected boolean complete;
    private List<MycarenetError> errors;
    private List<FaultType> genericErrors;
    private List<MemberDataResponse> memberDataResponse;
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

    public List<FaultType> getGenericErrors() {
        return genericErrors;
    }

    public void setGenericErrors(List<FaultType> genericErrors) {
        this.genericErrors = genericErrors;
    }

    public List<MemberDataResponse> getMemberDataResponse() {
        return memberDataResponse;
    }

    public void setMemberDataResponse(List<MemberDataResponse> memberDataResponse) {
        this.memberDataResponse = memberDataResponse;
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
