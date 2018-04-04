package org.taktik.connector.business.domain.chapter4;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDERROR;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 12/06/13
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class Problem implements Serializable {
    private List<CDERROR> cds;
    private String description;
    private String url;

    public Problem() {
    }

    public Problem(List<CDERROR> cds, String description, String url) {
        this.cds = cds;
        this.description = description;
        this.url = url;
    }

    public List<CDERROR> getCds() {
        return cds;
    }

    public void setCds(List<CDERROR> cds) {
        this.cds = cds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
