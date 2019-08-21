package be.cin.nip.async.generic;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Query",
   propOrder = {"max"}
)
@XmlSeeAlso({MsgQuery.class})
public class Query implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Max",
      defaultValue = "100"
   )
   protected Integer max;
   @XmlAttribute(
      name = "Include"
   )
   protected Boolean include;

   public Integer getMax() {
      return this.max;
   }

   public void setMax(Integer value) {
      this.max = value;
   }

   public boolean isInclude() {
      return this.include == null ? true : this.include;
   }

   public void setInclude(Boolean value) {
      this.include = value;
   }
}
