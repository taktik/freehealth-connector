package org.w3._2005._05.xmlmime;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public Base64Binary createBase64Binary() {
      return new Base64Binary();
   }

   public HexBinary createHexBinary() {
      return new HexBinary();
   }
}
