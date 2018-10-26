package org.taktik.connector.business.domain.dmg;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 17/06/14
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class DmgsList extends DmgMessage implements Serializable {
    List<DmgAcknowledge> acks = new ArrayList();

    List<DmgsList> lists = new ArrayList();
    List<DmgInscription> inscriptions = new ArrayList();
    List<DmgClosure> closures = new ArrayList();
    List<DmgExtension> extensions = new ArrayList();

    Date date;

    public void setMessages(@NotNull List<? extends DmgMessage> messages) {
        this.lists = messages.stream().filter(m -> m instanceof DmgsList).map(m -> (DmgsList) m).collect(Collectors.toList());
        this.inscriptions = messages.stream().filter(m -> m instanceof DmgInscription).map(m -> (DmgInscription) m).collect(Collectors.toList());
        this.closures = messages.stream().filter(m -> m instanceof DmgClosure).map(m -> (DmgClosure) m).collect(Collectors.toList());
        this.extensions = messages.stream().filter(m -> m instanceof DmgExtension).map(m -> (DmgExtension) m).collect(Collectors.toList());
    }

    public List<? extends DmgMessage> getMessages() {
        return ListUtils.union(ListUtils.union(this.lists, this.inscriptions), ListUtils.union(this.closures, this.extensions));
    }

    public List<DmgsList> getLists() {
        return lists;
    }

    public void setLists(List<DmgsList> lists) {
        this.lists = lists;
    }

    public List<DmgAcknowledge> getAcks() {
        return acks;
    }

    public void setAcks(List<DmgAcknowledge> acks) {
        this.acks = acks;
    }

    public List<DmgInscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<DmgInscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<DmgClosure> getClosures() {
        return closures;
    }

    public void setClosures(List<DmgClosure> closures) {
        this.closures = closures;
    }

    public List<DmgExtension> getExtensions() {
        return extensions;
    }

    public void setExtensions(List<DmgExtension> extensions) {
        this.extensions = extensions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
