package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CivilStateInfoWithSourceType"
)
public class CivilStateInfoWithSourceType extends CivilStateInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Source"
   )
   protected SourceType source;

   public SourceType getSource() {
      return this.source;
   }

   public void setSource(SourceType value) {
      this.source = value;
   }
}
