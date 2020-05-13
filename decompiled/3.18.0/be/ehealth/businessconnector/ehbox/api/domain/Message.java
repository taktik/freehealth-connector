package be.ehealth.businessconnector.ehbox.api.domain;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.idgenerator.IdGeneratorFactory;
import be.ehealth.technicalconnector.utils.DateUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

public abstract class Message<T> implements Serializable {
   private static final long serialVersionUID = -2042911788291478868L;
   private static final String PROP_IDGENERATOR = "be.ehealth.businessconnector.ehbox.api.domain.message.idgenerator";
   private String id;
   private String publicationId;
   private Addressee sender;
   /** @deprecated */
   @Deprecated
   private Addressee mandatee;
   private List<Addressee> destinations;
   private boolean important;
   private boolean encrypted;
   private boolean usePublicationReceipt;
   private boolean useReceivedReceipt;
   private boolean useReadReceipt;
   private T original;
   private boolean hasAnnex;
   private boolean hasFreeInformations;
   private DateTime publicationDate;
   private DateTime expirationDate;
   private String size;
   private Map<String, String> customMetas;

   public Message() {
      this.generatePublicationId();
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public final String getPublicationId() {
      return this.publicationId;
   }

   public final void generatePublicationId() {
      try {
         this.publicationId = IdGeneratorFactory.getIdGenerator(ConfigFactory.getConfigValidator().getProperty("be.ehealth.businessconnector.ehbox.api.domain.message.idgenerator", "nano")).generateId();
      } catch (TechnicalConnectorException var5) {
         long time = System.nanoTime();
         String identifier = Long.toString(time, 36).toUpperCase();
         this.publicationId = identifier;
      }

   }

   public final void setPublicationId(String id) {
      this.publicationId = id;
   }

   public final Addressee getSender() {
      return this.sender;
   }

   public final void setSender(Addressee sender) {
      this.sender = sender;
   }

   /** @deprecated */
   @Deprecated
   public final Addressee getMandatee() {
      return this.mandatee;
   }

   /** @deprecated */
   @Deprecated
   public final void setMandatee(Addressee mandatee) {
      this.mandatee = mandatee;
   }

   public final List<Addressee> getDestinations() {
      if (this.destinations == null) {
         this.destinations = new ArrayList();
      }

      return this.destinations;
   }

   public final void setDestinations(List<Addressee> destinations) {
      this.destinations = destinations;
   }

   public final boolean isImportant() {
      return this.important;
   }

   public final void setImportant(boolean important) {
      this.important = important;
   }

   public final boolean isEncrypted() {
      return this.encrypted;
   }

   public final void setEncrypted(boolean encrypted) {
      this.encrypted = encrypted;
   }

   public String toString() {
      return "Message [id=" + this.publicationId + ", sender=" + this.sender + ", destinations=" + this.destinations + ", important=" + this.important + ", encrypted=" + this.encrypted + "]";
   }

   public void setOriginal(T original) {
      this.original = original;
   }

   public T getOriginal() {
      return this.original;
   }

   public final void setHasAnnex(boolean hasAnnex) {
      this.hasAnnex = hasAnnex;
   }

   public final boolean isHasAnnex() {
      return this.hasAnnex;
   }

   public final void setHasFreeInformations(boolean hasFreeInformations) {
      this.hasFreeInformations = hasFreeInformations;
   }

   public final boolean isHasFreeInformations() {
      return this.hasFreeInformations;
   }

   public final Map<String, String> getCustomMetas() {
      if (this.customMetas == null) {
         this.customMetas = new HashMap();
      }

      return this.customMetas;
   }

   /** @deprecated */
   @Deprecated
   public final void setExpirationDate(Date expirationDate) {
      this.expirationDate = DateUtils.convert(expirationDate);
   }

   public final void setExpirationDateTime(DateTime expirationDate) {
      this.expirationDate = expirationDate;
   }

   /** @deprecated */
   @Deprecated
   public final Date getExpirationDate() {
      return this.expirationDate.toDate();
   }

   public final DateTime getExpirationDateTime() {
      return this.expirationDate;
   }

   public final void setPublicationDateTime(DateTime publicationDate) {
      this.publicationDate = publicationDate;
   }

   public final DateTime getPublicationDateTime() {
      return this.publicationDate;
   }

   /** @deprecated */
   @Deprecated
   public final void setPublicationDate(Date publicationDate) {
      this.publicationDate = DateUtils.convert(publicationDate);
   }

   /** @deprecated */
   @Deprecated
   public final Date getPublicationDate() {
      return this.publicationDate.toDate();
   }

   public void setSize(String size) {
      this.size = size;
   }

   public String getSize() {
      return this.size;
   }

   public boolean isUsePublicationReceipt() {
      return this.usePublicationReceipt;
   }

   public void setUsePublicationReceipt(boolean usePublicationReceipt) {
      this.usePublicationReceipt = usePublicationReceipt;
   }

   public boolean isUseReceivedReceipt() {
      return this.useReceivedReceipt;
   }

   public void setUseReceivedReceipt(boolean useReceivedReceipt) {
      this.useReceivedReceipt = useReceivedReceipt;
   }

   public boolean isUseReadReceipt() {
      return this.useReadReceipt;
   }

   public void setUseReadReceipt(boolean useReadReceipt) {
      this.useReadReceipt = useReadReceipt;
   }
}
