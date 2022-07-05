package be.fgov.ehealth.dics.core.v4.virtual.common;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ObjectFactory() {
   }

   public CommentedClassificationKeyType createCommentedClassificationKeyType() {
      return new CommentedClassificationKeyType();
   }

   public VmpGroupType createVmpGroupType() {
      return new VmpGroupType();
   }

   public VmpGroupKeyType createVmpGroupKeyType() {
      return new VmpGroupKeyType();
   }

   public VtmKeyType createVtmKeyType() {
      return new VtmKeyType();
   }

   public VirtualIngredientKeyType createVirtualIngredientKeyType() {
      return new VirtualIngredientKeyType();
   }

   public RealVirtualIngredientKeyType createRealVirtualIngredientKeyType() {
      return new RealVirtualIngredientKeyType();
   }
}
