package org.taktik.connector.business.chapterIV.wrapper.impl;

import org.taktik.connector.business.chapterIV.wrapper.WrappedXmlObject;

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
