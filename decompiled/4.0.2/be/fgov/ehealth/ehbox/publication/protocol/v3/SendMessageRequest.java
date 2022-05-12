package be.fgov.ehealth.ehbox.publication.protocol.v3;

import be.fgov.ehealth.ehbox.core.v3.BoxIdType;
import be.fgov.ehealth.ehbox.core.v3.MetaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PublicationMessageType",
   propOrder = {"boxId", "destinationContexts", "contentContext", "metas", "copyMailTos"}
)
@XmlRootElement(
   name = "SendMessageRequest"
)
public class SendMessageRequest implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "BoxId"
   )
   protected BoxIdType boxId;
   @XmlElement(
      name = "DestinationContext",
      required = true
   )
   protected List<DestinationContextType> destinationContexts;
   @XmlElement(
      name = "ContentContext",
      required = true
   )
   protected ContentContextType contentContext;
   @XmlElement(
      name = "Meta"
   )
   protected List<MetaType> metas;
   @XmlElement(
      name = "CopyMailTo"
   )
   protected List<String> copyMailTos;
   @XmlAttribute(
      name = "PublicationId"
   )
   protected String publicationId;

   public SendMessageRequest() {
   }

   public BoxIdType getBoxId() {
      return this.boxId;
   }

   public void setBoxId(BoxIdType value) {
      this.boxId = value;
   }

   public List<DestinationContextType> getDestinationContexts() {
      if (this.destinationContexts == null) {
         this.destinationContexts = new ArrayList();
      }

      return this.destinationContexts;
   }

   public ContentContextType getContentContext() {
      return this.contentContext;
   }

   public void setContentContext(ContentContextType value) {
      this.contentContext = value;
   }

   public List<MetaType> getMetas() {
      if (this.metas == null) {
         this.metas = new ArrayList();
      }

      return this.metas;
   }

   public List<String> getCopyMailTos() {
      if (this.copyMailTos == null) {
         this.copyMailTos = new ArrayList();
      }

      return this.copyMailTos;
   }

   public String getPublicationId() {
      return this.publicationId;
   }

   public void setPublicationId(String value) {
      this.publicationId = value;
   }
}
