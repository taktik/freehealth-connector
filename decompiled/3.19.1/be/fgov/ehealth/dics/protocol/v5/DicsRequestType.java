package be.fgov.ehealth.dics.protocol.v5;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DicsRequestType"
)
@XmlSeeAlso({FindAmpRequestType.class, FindNonMedicinalProductRequest.class, FindAmppRequest.class, FindCompoundingFormulaRequest.class, FindCompoundingIngredientRequest.class, FindCommentedClassificationRequest.class, FindVtmRequest.class, FindVmpGroupRequest.class, FindReferencesRequest.class, FindReimbursementRequest.class, FindLegislationTextRequest.class, FindVmpRequest.class, FindCompanyRequest.class})
public class DicsRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
