package be.ehealth.businessconnector.ehbox.api.domain;

import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorException;
import be.ehealth.businessconnector.ehbox.api.domain.exception.EhboxBusinessConnectorExceptionValues;
import be.ehealth.technicalconnector.enumeration.MimeType;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsMessage<T> extends DocumentMessage<T> {
   private static final long serialVersionUID = -6444617438670238149L;
   private static final Logger LOG = LoggerFactory.getLogger(NewsMessage.class);

   public NewsMessage() {
      Document document = new Document();
      document.setMimeType(MimeType.plaintext.getValue());
      this.setDocument(document);
   }

   /** @deprecated */
   @Deprecated
   public String getNewsTitle() {
      return this.getDocument() == null ? null : this.getDocument().getTitle();
   }

   /** @deprecated */
   @Deprecated
   public void setNewsTitle(String title) throws EhboxBusinessConnectorException {
      if (this.getDocument() == null) {
         LOG.debug("\t## News title can not be null : throwing Ehbox business connector exception");
         throw new EhboxBusinessConnectorException(EhboxBusinessConnectorExceptionValues.INVALID_EHBOX_NEWS_NEWSTITLE, (Throwable)null, new Object[0]);
      } else {
         this.getDocument().setTitle(title);
      }
   }

   /** @deprecated */
   @Deprecated
   public void setNewsContent(byte[] content) {
      this.getDocument().setContent(content);
   }

   /** @deprecated */
   @Deprecated
   public final void setContent(Byte[] content) {
      this.getDocument().setContent(ArrayUtils.toPrimitive(content));
   }

   /** @deprecated */
   @Deprecated
   public final Document getNews() {
      return this.getDocument();
   }

   /** @deprecated */
   @Deprecated
   public final void setNews(Document news) {
      this.setDocument(news);
   }
}
