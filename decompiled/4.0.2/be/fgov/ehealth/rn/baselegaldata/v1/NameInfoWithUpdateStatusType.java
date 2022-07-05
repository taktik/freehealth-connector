package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameInfoWithUpdateStatusType"
)
public class NameInfoWithUpdateStatusType extends AbstractOptionalNameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Status"
   )
   protected String status;

   public NameInfoWithUpdateStatusType() {
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String value) {
      this.status = value;
   }
}
