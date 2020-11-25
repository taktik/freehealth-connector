package be.ehealth.businessconnector.chapterIV.wrapper.impl;

import be.ehealth.businessconnector.chapterIV.wrapper.WrappedXmlObject;

public abstract class AbstractWrapper<T> implements WrappedXmlObject<T> {
   private static final long serialVersionUID = 5163661323196218914L;
   private T xmlObject;

   public AbstractWrapper(T xmlObject) {
      this.xmlObject = xmlObject;
   }

   public T getXmlObject() {
      return this.xmlObject;
   }
}
