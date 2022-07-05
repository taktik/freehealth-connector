package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindReimbursementResponseType",
   propOrder = {"reimbursementContexts"}
)
@XmlRootElement(
   name = "FindReimbursementResponse"
)
public class FindReimbursementResponse extends DicsResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ReimbursementContexts"
   )
   protected List<ConsultReimbursementContextType> reimbursementContexts;

   public FindReimbursementResponse() {
   }

   public List<ConsultReimbursementContextType> getReimbursementContexts() {
      if (this.reimbursementContexts == null) {
         this.reimbursementContexts = new ArrayList();
      }

      return this.reimbursementContexts;
   }
}
