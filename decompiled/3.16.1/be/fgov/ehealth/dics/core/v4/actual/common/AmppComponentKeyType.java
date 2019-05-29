package be.fgov.ehealth.dics.core.v4.actual.common;

import be.fgov.ehealth.dics.protocol.v4.ConsultAmppComponentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AmppComponentKeyType"
)
@XmlSeeAlso({ConsultAmppComponentType.class})
public class AmppComponentKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "AmpcSequenceNr"
   )
   protected Short ampcSequenceNr;
   @XmlAttribute(
      name = "SequenceNr",
      required = true
   )
   protected short sequenceNr;

   public Short getAmpcSequenceNr() {
      return this.ampcSequenceNr;
   }

   public void setAmpcSequenceNr(Short value) {
      this.ampcSequenceNr = value;
   }

   public short getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(short value) {
      this.sequenceNr = value;
   }
}
