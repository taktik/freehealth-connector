package be.fgov.ehealth.standards.kmehr.schema.v1;

import be.fgov.ehealth.standards.kmehr.cd.v1.LOCAL;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "localitemattributeType",
   propOrder = {"ids", "cd", "content"}
)
public class LocalitemattributeType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "id",
      nillable = true
   )
   protected List<IDKMEHR> ids;
   @XmlElement(
      required = true,
      nillable = true
   )
   protected LOCAL cd;
   @XmlElement(
      required = true
   )
   protected ContentlocalitemattributeType content;

   public LocalitemattributeType() {
   }

   public List<IDKMEHR> getIds() {
      if (this.ids == null) {
         this.ids = new ArrayList();
      }

      return this.ids;
   }

   public LOCAL getCd() {
      return this.cd;
   }

   public void setCd(LOCAL value) {
      this.cd = value;
   }

   public ContentlocalitemattributeType getContent() {
      return this.content;
   }

   public void setContent(ContentlocalitemattributeType value) {
      this.content = value;
   }
}
