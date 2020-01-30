package be.apb.gfddpp.assuralia;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _AssuraliaRequestParameters_QNAME = new QName("https://gfddpp.services.be/", "AssuraliaRequestParameters");
   private static final QName _SingleMessageMap_QNAME = new QName("https://gfddpp.services.be/", "SingleMessageMap");

   public AssuraliaRequestParameters createAssuraliaRequestParameters() {
      return new AssuraliaRequestParameters();
   }

   public SingleMessageMap createSingleMessageMap() {
      return new SingleMessageMap();
   }

   public SignatureType createSignatureType() {
      return new SignatureType();
   }

   @XmlElementDecl(
      namespace = "https://gfddpp.services.be/",
      name = "AssuraliaRequestParameters"
   )
   public JAXBElement<AssuraliaRequestParameters> createAssuraliaRequestParameters(AssuraliaRequestParameters value) {
      return new JAXBElement(_AssuraliaRequestParameters_QNAME, AssuraliaRequestParameters.class, (Class)null, value);
   }

   @XmlElementDecl(
      namespace = "https://gfddpp.services.be/",
      name = "SingleMessageMap"
   )
   public JAXBElement<SingleMessageMap> createSingleMessageMap(SingleMessageMap value) {
      return new JAXBElement(_SingleMessageMap_QNAME, SingleMessageMap.class, (Class)null, value);
   }
}
