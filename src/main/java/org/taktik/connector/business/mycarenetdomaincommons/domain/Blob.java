package org.taktik.connector.business.mycarenetdomaincommons.domain;

import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.util.Arrays;

public class Blob {
   private byte[] content;
   private String contentType;
   private String messageName;
   private String messageVersion;
   private byte[] hashValue;
   private byte[] xadesValue;
   private String contentEncryption;
   private String id;
   private boolean isHashTagRequired = true;
   private String contentEncoding;

   public byte[] getContent() {
      return Arrays.clone(this.content);
   }

   public void setContent(byte[] content) {
      this.content = Arrays.clone(content);
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public byte[] getHashValue() {
      return Arrays.clone(this.hashValue);
   }

   public void setHashValue(byte[] hashValue) {
      this.hashValue = Arrays.clone(hashValue);
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getContentEncoding() {
      return this.contentEncoding;
   }

   public void setContentEncoding(String contentEncoding) {
      this.contentEncoding = contentEncoding;
   }

   public String getMessageName() {
      return this.messageName;
   }

   public void setMessageName(String messageName) {
      this.messageName = messageName;
   }

   public String getMessageVersion() {
      return this.messageVersion;
   }

   public void setMessageVersion(String messageVersion) {
      this.messageVersion = messageVersion;
   }

   public boolean isHashTagRequired() {
      return this.isHashTagRequired;
   }

   public void setHashTagRequired(boolean isHashTagRequired) {
      this.isHashTagRequired = isHashTagRequired;
   }

   public byte[] getXadesValue() {
      return Arrays.clone(this.xadesValue);
   }

   public void setXadesValue(byte[] xadesValue) {
      this.xadesValue = ArrayUtils.clone(xadesValue);
   }

   public String getContentEncryption() {
      return this.contentEncryption;
   }

   public void setContentEncryption(String contentEncryption) {
      this.contentEncryption = contentEncryption;
   }
}
