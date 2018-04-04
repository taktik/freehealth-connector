package org.taktik.connector.business.chapterIV.wrapper;

import java.io.Serializable;

public interface WrappedXmlObject<T> extends Serializable {
   T getXmlObject();
}
