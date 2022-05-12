package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoWithSourceType"
)
public class NationalityInfoWithSourceType extends NationalityInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Source"
   )
   protected String source;

   public NationalityInfoWithSourceType() {
   }

   public String getSource() {
      return this.source;
   }

   public void setSource(String value) {
      this.source = value;
   }
}
