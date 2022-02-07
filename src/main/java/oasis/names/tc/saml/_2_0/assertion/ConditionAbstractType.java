package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConditionAbstractType"
)
@XmlSeeAlso({ProxyRestriction.class, OneTimeUse.class, AudienceRestriction.class})
public abstract class ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
