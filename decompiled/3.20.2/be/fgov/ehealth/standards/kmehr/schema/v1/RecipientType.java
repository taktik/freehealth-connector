package be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipientType",
   propOrder = {"hcparties", "dummyFriend"}
)
public class RecipientType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "hcparty",
      required = true
   )
   protected List<HcpartyType> hcparties;
   protected String dummyFriend;

   public List<HcpartyType> getHcparties() {
      if (this.hcparties == null) {
         this.hcparties = new ArrayList();
      }

      return this.hcparties;
   }

   public String getDummyFriend() {
      return this.dummyFriend;
   }

   public void setDummyFriend(String value) {
      this.dummyFriend = value;
   }
}
