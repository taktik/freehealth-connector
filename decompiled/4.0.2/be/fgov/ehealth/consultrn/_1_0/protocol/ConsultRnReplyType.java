package be.fgov.ehealth.consultrn._1_0.protocol;

import be.fgov.ehealth.commons._1_0.protocol.ResponseType;
import be.fgov.ehealth.consultrn._1_0.core.ErrorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRnReplyType",
   propOrder = {"errorInformations"}
)
@XmlSeeAlso({SearchPhoneticReply.class, PersonHistoryFamilyCompositionReply.class, PersonHistoryNationalityReply.class, PersonHistoryAddressReply.class, PersonHistoryDeceaseReply.class, PersonHistoryCivilStateReply.class, PersonHistoryBirthReply.class, PersonHistoryGenderReply.class, PersonHistoryNameReply.class, MonitoringReply.class, ModifyPersonReply.class, CreatePersonReply.class, SearchBySSINReply.class, FamilyCompositionReply.class, DeleteInscriptionReply.class, InsertInscriptionReply.class})
public class ConsultRnReplyType extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ErrorInformation"
   )
   protected List<ErrorType> errorInformations;

   public ConsultRnReplyType() {
   }

   public List<ErrorType> getErrorInformations() {
      if (this.errorInformations == null) {
         this.errorInformations = new ArrayList();
      }

      return this.errorInformations;
   }
}
