package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SealedMessageRequestType",
   propOrder = {"routingParameters", "singleMessageSealed", "authorizationParameters"}
)
public class SealedMessageRequestType extends RequestType {
   @XmlElement(
      name = "RoutingParameters"
   )
   protected RoutingParametersType routingParameters;
   @XmlElement(
      name = "SingleMessageSealed",
      required = true
   )
   protected byte[] singleMessageSealed;
   @XmlElement(
      name = "AuthorizationParameters"
   )
   protected AuthorizationParametersType authorizationParameters;

   public RoutingParametersType getRoutingParameters() {
      return this.routingParameters;
   }

   public void setRoutingParameters(RoutingParametersType var1) {
      this.routingParameters = var1;
   }

   public byte[] getSingleMessageSealed() {
      return this.singleMessageSealed;
   }

   public void setSingleMessageSealed(byte[] var1) {
      this.singleMessageSealed = (byte[])var1;
   }

   public AuthorizationParametersType getAuthorizationParameters() {
      return this.authorizationParameters;
   }

   public void setAuthorizationParameters(AuthorizationParametersType var1) {
      this.authorizationParameters = var1;
   }
}
