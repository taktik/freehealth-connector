package org.taktik.connector.business.domain.dmg;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class DmgAcknowledge extends DmgMessage implements Serializable {
    String major;
    String minor;
    String message;
    Date date;

    public DmgAcknowledge() {
    }

    public DmgAcknowledge(String major, String minor, String message) {
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
