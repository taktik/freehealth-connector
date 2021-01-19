package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameInfoWithStatusAndSourceType"
)
public class NameInfoWithStatusAndSourceType extends AbstractOptionalNameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "Status"
   )
   protected DatagroupStatusType status;
   @XmlAttribute(
      name = "Source"
   )
   protected SourceType source;

   public DatagroupStatusType getStatus() {
      return this.status;
   }

   public void setStatus(DatagroupStatusType value) {
      this.status = value;
   }

   public SourceType getSource() {
      return this.source;
   }

   public void setSource(SourceType value) {
      this.source = value;
   }
}
