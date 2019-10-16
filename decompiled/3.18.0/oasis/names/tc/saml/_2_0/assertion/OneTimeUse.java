package oasis.names.tc.saml._2_0.assertion;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OneTimeUseType"
)
@XmlRootElement(
   name = "OneTimeUse"
)
public class OneTimeUse extends ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
