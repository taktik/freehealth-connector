package org.taktik.freehealth.middleware.drugs.dto;

import java.io.Serializable;

/**
 * Created by aduchate on 14/07/11, 08:40
 */
public class IamId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String lang;
    private Integer id;

    public IamId() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IamId iamId = (IamId) o;

        if (id != null ? !id.equals(iamId.id) : iamId.id != null) return false;
        if (lang != null ? !lang.equals(iamId.lang) : iamId.lang != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lang != null ? lang.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
