package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReimbursementResponseType",
   propOrder = {"reimbursementContexts"}
)
@XmlRootElement(
   name = "FindReimbursementResponse"
)
public class FindReimbursementResponse extends StatusResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReimbursementContexts"
   )
   protected List<ConsultReimbursementContextType> reimbursementContexts;
   @XmlAttribute(
      name = "SearchDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public List<ConsultReimbursementContextType> getReimbursementContexts() {
      if (this.reimbursementContexts == null) {
         this.reimbursementContexts = new ArrayList();
      }

      return this.reimbursementContexts;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
