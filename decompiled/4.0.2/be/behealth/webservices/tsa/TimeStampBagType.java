package be.behealth.webservices.tsa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TimeStampBagType",
   propOrder = {"journalEntries"}
)
public class TimeStampBagType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "JournalEntry",
      required = true
   )
   protected List<JournalEntryType> journalEntries;
   @XmlAttribute(
      name = "Hash",
      required = true
   )
   protected HashType hash;
   @XmlAttribute(
      name = "SequenceNumber",
      required = true
   )
   protected long sequenceNumber;

   public TimeStampBagType() {
   }

   public List<JournalEntryType> getJournalEntries() {
      if (this.journalEntries == null) {
         this.journalEntries = new ArrayList();
      }

      return this.journalEntries;
   }

   public HashType getHash() {
      return this.hash;
   }

   public void setHash(HashType value) {
      this.hash = value;
   }

   public long getSequenceNumber() {
      return this.sequenceNumber;
   }

   public void setSequenceNumber(long value) {
      this.sequenceNumber = value;
   }
}
