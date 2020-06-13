package be.fgov.ehealth.mycarenet.memberdata.protocol.v1;

import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse;
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MemberDataAcknowledge extends MemberDataMessage implements Serializable {
    String major;
    String minor;
    String message;
    Date date;


    public MemberDataAcknowledge(String major, String minor, String message) {
        this.major = major;
        this.minor = minor;
        this.message = message;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
