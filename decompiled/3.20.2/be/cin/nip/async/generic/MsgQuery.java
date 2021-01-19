package be.cin.nip.async.generic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MsgQuery",
   propOrder = {"messageNames"}
)
public class MsgQuery extends Query implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlList
   @XmlElement(
      name = "MessageNames",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected List<String> messageNames;

   public List<String> getMessageNames() {
      if (this.messageNames == null) {
         this.messageNames = new ArrayList();
      }

      return this.messageNames;
   }
}
