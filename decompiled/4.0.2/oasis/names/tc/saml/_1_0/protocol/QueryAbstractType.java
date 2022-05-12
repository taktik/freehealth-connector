package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QueryAbstractType"
)
@XmlSeeAlso({SubjectQueryAbstractType.class})
public abstract class QueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;

   public QueryAbstractType() {
   }
}
