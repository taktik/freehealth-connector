package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import be.cin.nip.async.generic.MsgResponse;
import org.taktik.connector.business.memberdatav2.domain.MemberDataBuilderResponse;
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.icure.cin.saml.extensions.ResponseList;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberDataList implements Serializable {
    private MycarenetConversation mycarenetConversation;
    private List<MemberDataAcknowledge> acks = new ArrayList();
    private List<MemberDataMessage> memberDataMessageList;
    private Date date;

    public MycarenetConversation getMycarenetConversation() {
        return mycarenetConversation;
    }

    public void setMycarenetConversation(MycarenetConversation mycarenetConversation) {
        this.mycarenetConversation = mycarenetConversation;
    }

    public List<MemberDataAcknowledge> getAcks() {
        return acks;
    }

    public void setAcks(List<MemberDataAcknowledge> acks) {
        this.acks = acks;
    }

    public List<MemberDataMessage> getMemberDataMessageList() {
        return memberDataMessageList;
    }

    public void setMemberDataMessageList(List<MemberDataMessage> memberDataMessageList) {
        this.memberDataMessageList = memberDataMessageList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
