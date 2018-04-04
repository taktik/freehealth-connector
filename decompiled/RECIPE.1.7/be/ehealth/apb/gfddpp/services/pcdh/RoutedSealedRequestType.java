package be.ehealth.apb.gfddpp.services.pcdh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RoutedSealedRequestType",
   propOrder = {"routingParameters", "requestParametersSealed", "authorizationParameters"}
)
public class RoutedSealedRequestType extends RequestType {
   @XmlElement(
      name = "RoutingParameters"
   )
   protected RoutingParametersType routingParameters;
   @XmlElement(
      name = "RequestParametersSealed",
      required = true
   )
   protected byte[] requestParametersSealed;
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

   public byte[] getRequestParametersSealed() {
      return this.requestParametersSealed;
   }

   public void setRequestParametersSealed(byte[] var1) {
      this.requestParametersSealed = (byte[])var1;
   }

   public AuthorizationParametersType getAuthorizationParameters() {
      return this.authorizationParameters;
   }

   public void setAuthorizationParameters(AuthorizationParametersType var1) {
      this.authorizationParameters = var1;
   }
}
