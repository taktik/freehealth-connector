package be.cin.nippin.memberdata.saml.extension;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public AttributeQueryList createAttributeQueryList() {
      return new AttributeQueryList();
   }

   public ResponseList createResponseList() {
      return new ResponseList();
   }

   public Facet createFacet() {
      return new Facet();
   }

   public Dimension createDimension() {
      return new Dimension();
   }

   public ExtensionsType createExtensionsType() {
      return new ExtensionsType();
   }
}
