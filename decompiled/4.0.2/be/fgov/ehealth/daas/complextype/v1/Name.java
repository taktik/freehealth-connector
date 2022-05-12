package be.fgov.ehealth.daas.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameType"
)
@XmlRootElement(
   name = "Name"
)
public class Name extends BaseNameType implements Serializable {
   private static final long serialVersionUID = 1L;

   public Name() {
   }
}
