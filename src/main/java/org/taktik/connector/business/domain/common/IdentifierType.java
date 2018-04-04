package org.taktik.connector.business.domain.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 02/12/13
 * Time: 15:07
 */
public class IdentifierType implements Serializable {
    private static Map<String, org.taktik.connector.technical.utils.IdentifierType> ehMapping = new HashMap<String, org.taktik.connector.technical.utils.IdentifierType>();
    private static Map<String, IdentifierType> icMapping = new HashMap<String, IdentifierType>();
    private static IdentifierType register(String code, org.taktik.connector.technical.utils.IdentifierType ehIt) {
        IdentifierType it = new IdentifierType(code);
        ehMapping.put(code, ehIt);
        icMapping.put(code, it);
        return it;
    }

    public static final IdentifierType CBE = register("CBE", org.taktik.connector.technical.utils.IdentifierType.CBE);
    public static final IdentifierType SSIN = register("SSIN",org.taktik.connector.technical.utils.IdentifierType.SSIN);
    public static final IdentifierType NIHII = register("NIHII",org.taktik.connector.technical.utils.IdentifierType.NIHII);
    public static final IdentifierType NIHII11 = register("NIHII11",org.taktik.connector.technical.utils.IdentifierType.NIHII11);
    public static final IdentifierType NIHII_PHARMACY = register("NIHII_PHARMACY",org.taktik.connector.technical.utils.IdentifierType.NIHII_PHARMACY);
    public static final IdentifierType NIHII_LABO = register("NIHII_LABO",org.taktik.connector.technical.utils.IdentifierType.NIHII_LABO);
    public static final IdentifierType NIHII_RETIREMENT = register("NIHII_RETIREMENT",org.taktik.connector.technical.utils.IdentifierType.NIHII_RETIREMENT);
    public static final IdentifierType NIHII_OTD_PHARMACY = register("NIHII_OTD_PHARMACY",org.taktik.connector.technical.utils.IdentifierType.NIHII_OTD_PHARMACY);
    public static final IdentifierType NIHII_HOSPITAL = register("NIHII_HOSPITAL",org.taktik.connector.technical.utils.IdentifierType.NIHII_HOSPITAL);
    public static final IdentifierType NIHII_GROUPOFNURSES = register("NIHII_GROUPOFNURSES",org.taktik.connector.technical.utils.IdentifierType.NIHII_GROUPOFNURSES);
    public static final IdentifierType HUB = register("HUB",org.taktik.connector.technical.utils.IdentifierType.HUB);

    private String code;

    public static IdentifierType fromEhType(org.taktik.connector.technical.utils.IdentifierType ehIt) {
        for (Map.Entry<String, org.taktik.connector.technical.utils.IdentifierType> e:ehMapping.entrySet()) {
            if (e.getValue().equals(ehIt)) return icMapping.get(e.getKey());
        }
        return null;
    }

    public IdentifierType() {
    }

    public IdentifierType(String code) {
        this.code = code;
    }

    public org.taktik.connector.technical.utils.IdentifierType toEhType() {
        return ehMapping.get(this.getCode());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdentifierType that = (IdentifierType) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }


}
