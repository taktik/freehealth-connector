package be.apb.standards.gfddpp.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "dataSpecificParametersMedicationSchemeEntries",
   propOrder = {"clientMessageId", "paginationIndex"}
)
public class DataSpecificParametersMedicationSchemeEntries {
   protected String clientMessageId;
   protected int paginationIndex;

   public String getClientMessageId() {
      return this.clientMessageId;
   }

   public void setClientMessageId(String value) {
      this.clientMessageId = value;
   }

   public int getPaginationIndex() {
      return this.paginationIndex;
   }

   public void setPaginationIndex(int value) {
      this.paginationIndex = value;
   }
}
