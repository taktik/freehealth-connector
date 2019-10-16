package be.fgov.ehealth.consultrn._1_0.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NameHistoryType",
   propOrder = {"name"}
)
public class NameHistoryType extends HistoryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Name",
      required = true
   )
   protected NameType name;

   public NameType getName() {
      return this.name;
   }

   public void setName(NameType value) {
      this.name = value;
   }
}
