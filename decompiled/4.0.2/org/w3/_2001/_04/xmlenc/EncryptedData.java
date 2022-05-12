package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedDataType"
)
@XmlRootElement(
   name = "EncryptedData"
)
public class EncryptedData extends EncryptedType implements Serializable {
   private static final long serialVersionUID = 1L;

   public EncryptedData() {
   }
}
