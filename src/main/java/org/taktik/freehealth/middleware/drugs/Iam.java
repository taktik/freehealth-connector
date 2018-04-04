package org.taktik.freehealth.middleware.drugs;

import org.taktik.freehealth.middleware.drugs.dto.IamId;

/**
 * Created by aduchate on 14/07/11, 08:41
 */
public class Iam {
    private IamId iamId;
    private String atc1;
    private String atc2;
    private String description;
    private String management;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAtc2() {
        return atc2;
    }

    public void setAtc2(String atc2) {
        this.atc2 = atc2;
    }

    public String getAtc1() {
        return atc1;
    }

    public void setAtc1(String atc1) {
        this.atc1 = atc1;
    }

    public IamId getIamId() {
        return iamId;
    }

    public void setIamId(IamId iamId) {
        this.iamId = iamId;
    }
}
