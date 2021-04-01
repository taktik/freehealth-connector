package be.fgov.ehealth.timestamping.protocol.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "JournalEntryType",
   propOrder = {"hash", "ref"}
)
public class JournalEntryType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Hash",
      required = true
   )
   protected String hash;
   @XmlElement(
      name = "Ref"
   )
   protected long ref;

   public String getHash() {
      return this.hash;
   }

   public void setHash(String value) {
      this.hash = value;
   }

   public long getRef() {
      return this.ref;
   }

   public void setRef(long value) {
      this.ref = value;
   }
}
