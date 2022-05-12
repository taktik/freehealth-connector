package be.fgov.ehealth.globalmedicalfile.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.globalmedicalfile.core.v1.BlobType;
import be.fgov.ehealth.globalmedicalfile.core.v1.CommonInputType;
import be.fgov.ehealth.globalmedicalfile.core.v1.RoutingType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2005._05.xmlmime.Base64Binary;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendRequestType",
   propOrder = {"commonInput", "routing", "detail", "xadesT"}
)
public class SendRequestType extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "CommonInput",
      required = true
   )
   protected CommonInputType commonInput;
   @XmlElement(
      name = "Routing"
   )
   protected RoutingType routing;
   @XmlElement(
      name = "Detail",
      required = true
   )
   protected BlobType detail;
   @XmlElement(
      name = "XadesT"
   )
   protected Base64Binary xadesT;

   public SendRequestType() {
   }

   public CommonInputType getCommonInput() {
      return this.commonInput;
   }

   public void setCommonInput(CommonInputType value) {
      this.commonInput = value;
   }

   public RoutingType getRouting() {
      return this.routing;
   }

   public void setRouting(RoutingType value) {
      this.routing = value;
   }

   public BlobType getDetail() {
      return this.detail;
   }

   public void setDetail(BlobType value) {
      this.detail = value;
   }

   public Base64Binary getXadesT() {
      return this.xadesT;
   }

   public void setXadesT(Base64Binary value) {
      this.xadesT = value;
   }
}
