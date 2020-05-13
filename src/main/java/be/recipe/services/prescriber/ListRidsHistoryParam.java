package be.recipe.services.prescriber;

import be.recipe.services.core.Page;
import be.recipe.services.core.PartyIdentification;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistoryParam",
   propOrder = {"symmKey", "activeResults", "page", "session"}
)
@XmlRootElement(
   name = "listRidsHistoryParam"
)
public class ListRidsHistoryParam extends PartyIdentification {
   @XmlElement(
      required = true
   )
   protected byte[] symmKey;
   protected Boolean activeResults;
   protected Page page;
   protected byte[] session;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] value) {
      this.symmKey = value;
   }

   public Boolean isActiveResults() {
      return this.activeResults;
   }

   public void setActiveResults(Boolean value) {
      this.activeResults = value;
   }

   public Page getPage() {
      return this.page;
   }

   public void setPage(Page value) {
      this.page = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
   }

   public Boolean getActiveResults() {
      return this.activeResults;
   }

}
