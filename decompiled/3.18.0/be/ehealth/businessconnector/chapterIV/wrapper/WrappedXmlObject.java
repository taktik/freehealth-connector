package be.ehealth.businessconnector.chapterIV.wrapper;

import java.io.Serializable;

public interface WrappedXmlObject<T> extends Serializable {
   T getXmlObject();
}
