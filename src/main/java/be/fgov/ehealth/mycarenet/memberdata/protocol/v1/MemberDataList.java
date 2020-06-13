package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.cin.nippin.memberdata.saml.extension.ResponseList;
import org.taktik.connector.business.memberdatav2.domain.MemberDataBuilderResponse;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDataList extends MemberDataMessage implements Serializable {
    List<MemberDataAcknowledge> acks = new ArrayList();
    List<MemberDataBuilderResponse> memberDataListResponse = new ArrayList();
    Date date;

    public List<MemberDataAcknowledge> getAcks() {
        return acks;
    }

    public void setAcks(List<MemberDataAcknowledge> acks) {
        this.acks = acks;
    }

    public List<MemberDataBuilderResponse> getMemberDataListResponse() {
        return memberDataListResponse;
    }

    public void setMemberDataListResponse(List<MemberDataBuilderResponse> memberDataListResponse) {
        this.memberDataListResponse = memberDataListResponse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
