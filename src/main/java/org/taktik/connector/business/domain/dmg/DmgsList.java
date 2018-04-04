package org.taktik.connector.business.domain.dmg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class DmgsList extends DmgMessage implements Serializable {
    String oa;
    List<DmgInscription> inscriptions = new ArrayList<DmgInscription>();
    Date date;

    public String getOa() {
        return oa;
    }

    public void setOa(String oa) {
        this.oa = oa;
    }

    public List<DmgInscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<DmgInscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
