package be.fgov.ehealth.standards.kmehr.schema.v1;

import org.taktik.connector.technical.adapter.XmlDateNoTzAdapter;
import be.fgov.ehealth.standards.kmehr.id.v1.IDINSURANCE;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "insuranceType",
   propOrder = {"id", "membership", "siscard", "begindate", "enddate", "cg1", "cg2", "socialfranchiseperiod1", "socialfranchiseperiod2", "personalparts", "thirdpayercontract", "begindatepayment", "approvalnumber"}
)
public class InsuranceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      required = true
   )
   protected IDINSURANCE id;
   @XmlElement(
      required = true
   )
   protected String membership;
   protected String siscard;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime begindate;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime enddate;
   protected String cg1;
   protected String cg2;
   protected String socialfranchiseperiod1;
   protected String socialfranchiseperiod2;
   @XmlElement(
      name = "personalpart"
   )
   protected List<Personalpart> personalparts;
   protected Thirdpayercontract thirdpayercontract;
   @XmlElement(
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateNoTzAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime begindatepayment;
   protected String approvalnumber;

   public IDINSURANCE getId() {
      return this.id;
   }

   public void setId(IDINSURANCE value) {
      this.id = value;
   }

   public String getMembership() {
      return this.membership;
   }

   public void setMembership(String value) {
      this.membership = value;
   }

   public String getSiscard() {
      return this.siscard;
   }

   public void setSiscard(String value) {
      this.siscard = value;
   }

   public DateTime getBegindate() {
      return this.begindate;
   }

   public void setBegindate(DateTime value) {
      this.begindate = value;
   }

   public DateTime getEnddate() {
      return this.enddate;
   }

   public void setEnddate(DateTime value) {
      this.enddate = value;
   }

   public String getCg1() {
      return this.cg1;
   }

   public void setCg1(String value) {
      this.cg1 = value;
   }

   public String getCg2() {
      return this.cg2;
   }

   public void setCg2(String value) {
      this.cg2 = value;
   }

   public String getSocialfranchiseperiod1() {
      return this.socialfranchiseperiod1;
   }

   public void setSocialfranchiseperiod1(String value) {
      this.socialfranchiseperiod1 = value;
   }

   public String getSocialfranchiseperiod2() {
      return this.socialfranchiseperiod2;
   }

   public void setSocialfranchiseperiod2(String value) {
      this.socialfranchiseperiod2 = value;
   }

   public List<Personalpart> getPersonalparts() {
      if (this.personalparts == null) {
         this.personalparts = new ArrayList();
      }

      return this.personalparts;
   }

   public Thirdpayercontract getThirdpayercontract() {
      return this.thirdpayercontract;
   }

   public void setThirdpayercontract(Thirdpayercontract value) {
      this.thirdpayercontract = value;
   }

   public DateTime getBegindatepayment() {
      return this.begindatepayment;
   }

   public void setBegindatepayment(DateTime value) {
      this.begindatepayment = value;
   }

   public String getApprovalnumber() {
      return this.approvalnumber;
   }

   public void setApprovalnumber(String value) {
      this.approvalnumber = value;
   }
}
