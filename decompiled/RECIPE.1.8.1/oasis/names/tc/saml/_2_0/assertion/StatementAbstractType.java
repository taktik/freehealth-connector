package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StatementAbstractType"
)
@XmlSeeAlso({AttributeStatement.class, AuthzDecisionStatement.class, AuthnStatement.class})
public abstract class StatementAbstractType {
}
