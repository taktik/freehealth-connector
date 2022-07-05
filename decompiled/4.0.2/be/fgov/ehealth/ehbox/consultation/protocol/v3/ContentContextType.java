package be.fgov.ehealth.ehbox.consultation.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.ContentSpecificationType;
import be.fgov.ehealth.ehbox.core.v3.CustomMetaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ContentContextType",
   propOrder = {"content", "contentSpecification", "customMetas"}
)
public class ContentContextType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Content",
      required = true
   )
   protected ConsultationContentType content;
   @XmlElement(
      name = "ContentSpecification",
      required = true
   )
   protected ContentSpecificationType contentSpecification;
   @XmlElement(
      name = "CustomMeta"
   )
   protected List<CustomMetaType> customMetas;

   public ContentContextType() {
   }

   public ConsultationContentType getContent() {
      return this.content;
   }

   public void setContent(ConsultationContentType value) {
      this.content = value;
   }

   public ContentSpecificationType getContentSpecification() {
      return this.contentSpecification;
   }

   public void setContentSpecification(ContentSpecificationType value) {
      this.contentSpecification = value;
   }

   public List<CustomMetaType> getCustomMetas() {
      if (this.customMetas == null) {
         this.customMetas = new ArrayList();
      }

      return this.customMetas;
   }
}
