package be.fgov.ehealth.aa.complextype.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DescriptionType"
)
@XmlRootElement(
   name = "Description"
)
public class Description extends BaseNameType implements Serializable {
   private static final long serialVersionUID = 1L;

   public Description() {
   }
}
