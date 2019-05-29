package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.AttributeStatement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IAMAttributeStatement"
)
public class IAMAttributeStatement extends AttributeStatement implements Serializable {
   private static final long serialVersionUID = 1L;
}
