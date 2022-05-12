package be.fgov.ehealth.dics.protocol.v3;

import be.ehealth.technicalconnector.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.actual.common.DmppKeyType;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultDmppType",
   propOrder = {"price", "cheap", "cheapest", "reimbursable"}
)
public class ConsultDmppType extends DmppKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Price",
      namespace = "urn:be:fgov:ehealth:dics:core:v3:actual:common"
   )
   protected BigDecimal price;
   @XmlElement(
      name = "Cheap",
      namespace = "urn:be:fgov:ehealth:dics:core:v3:actual:common"
   )
   protected Boolean cheap;
   @XmlElement(
      name = "Cheapest",
      namespace = "urn:be:fgov:ehealth:dics:core:v3:actual:common"
   )
   protected Boolean cheapest;
   @XmlElement(
      name = "Reimbursable",
      namespace = "urn:be:fgov:ehealth:dics:core:v3:actual:common"
   )
   protected boolean reimbursable;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultDmppType() {
   }

   public BigDecimal getPrice() {
      return this.price;
   }

   public void setPrice(BigDecimal value) {
      this.price = value;
   }

   public Boolean isCheap() {
      return this.cheap;
   }

   public void setCheap(Boolean value) {
      this.cheap = value;
   }

   public Boolean isCheapest() {
      return this.cheapest;
   }

   public void setCheapest(Boolean value) {
      this.cheapest = value;
   }

   public boolean isReimbursable() {
      return this.reimbursable;
   }

   public void setReimbursable(boolean value) {
      this.reimbursable = value;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
