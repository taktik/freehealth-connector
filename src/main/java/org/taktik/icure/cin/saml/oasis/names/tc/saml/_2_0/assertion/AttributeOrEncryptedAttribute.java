package org.taktik.icure.cin.saml.oasis.names.tc.saml._2_0.assertion;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Attribute.class, name = "Attribute"),
    @JsonSubTypes.Type(value = EncryptedElementType.class, name = "EncryptedAttribute")
})
public interface AttributeOrEncryptedAttribute extends Serializable {

}
