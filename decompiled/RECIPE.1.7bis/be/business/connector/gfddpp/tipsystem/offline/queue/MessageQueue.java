package be.business.connector.gfddpp.tipsystem.offline.queue;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.PropertyHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

public class MessageQueue<T extends Serializable> implements Queue<T> {
   private static final String MESSAGE_QUEUE_FOLDER_PROPERTY_NAME = "MESSAGE_QUEUE_FOLDER";
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
   private static final String FILE_NAME_SEPARATOR = "_";
   public static final String FILE_LOCK_SUFFIX = "_LOCK";
   private static final Logger LOG = Logger.getLogger(MessageQueue.class);
   private String folder;
   private File queueFolder;
   private int fileCounter = 0;
   private PropertyHandler property;
   private EncryptionUtils encryptionUtils;
   private PublicKey publicKey;

   public MessageQueue(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      this.property = propertyHandler;
      this.encryptionUtils = encryptionUtils;
      this.initialize();
   }

   private void initialize() throws IntegrationModuleException {
      if (this.property.hasProperty("MESSAGE_QUEUE_FOLDER")) {
         this.setMessageQueuePath(this.property.getProperty("MESSAGE_QUEUE_FOLDER"));
         this.queueFolder = new File(this.getMessageQueuePath());
         this.publicKey = this.encryptionUtils.getPublicKey();
         boolean var1 = this.queueFolder.exists();
         if (!var1) {
            boolean created = this.queueFolder.mkdirs();
            if (!created) {
               throw new RuntimeException("Not able to create folder " + this.getMessageQueuePath());
            }
         }

      } else {
         throw new RuntimeException("MESSAGE_QUEUE_FOLDER not found in properties file !");
      }
   }

   private void setMessageQueuePath(String folder) {
      this.folder = folder;
   }

   public boolean isEmpty() {
      return ArrayUtils.isEmpty(this.queueFolder.list());
   }

   public boolean add(T e) {
      return this.offer(e);
   }

   public boolean offer(T e) {
      return this.save(e);
   }

   public T remove() {
      if (!this.isEmpty()) {
         File first = this.getFirstFile();
         T result = this.load(first);
         boolean deleted = first.delete();

         assert deleted;

         return result;
      } else {
         throw new NoSuchElementException();
      }
   }

   public T poll() {
      return !this.isEmpty() ? this.remove() : null;
   }

   public T element() {
      if (!this.isEmpty()) {
         return this.load(this.getFirstFile());
      } else {
         throw new NoSuchElementException();
      }
   }

   public T elementAndLockItem() {
      if (!this.isEmpty()) {
         return this.load(this.getFirstFileAndLock());
      } else {
         throw new NoSuchElementException();
      }
   }

   public T peek() {
      return !this.isEmpty() ? this.load(this.getFirstFile()) : null;
   }

   public int size() {
      return this.queueFolder.list().length;
   }

   public boolean contains(Object o) {
      throw new UnsupportedOperationException();
   }

   public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public void clear() {
      throw new UnsupportedOperationException();
   }

   public boolean containsAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public Iterator<T> iterator() {
      throw new UnsupportedOperationException();
   }

   public Object[] toArray() {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException();
   }

   public <T> T[] toArray(T[] a) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(Collection<? extends T> c) {
      boolean result = true;

      Serializable element;
      for(Iterator var4 = c.iterator(); var4.hasNext(); result &= this.offer(element)) {
         element = (Serializable)var4.next();
      }

      return result;
   }

   private boolean save(T clearObject) {
      byte[] serialiazed = this.serialize(clearObject);
      byte[] sealedObject = null;
      FileOutputStream saveFile = null;
      ObjectOutputStream save = null;

      byte[] sealedObject;
      try {
         sealedObject = this.seal(serialiazed);
      } catch (IntegrationModuleException var16) {
         LOG.info(var16.getLocalizedMessage());
         return false;
      }

      try {
         saveFile = new FileOutputStream(this.getMessageQueuePath() + File.separator + this.generateFileName());
         save = new ObjectOutputStream(saveFile);
         save.writeObject(sealedObject);
         save.close();
         saveFile.close();
         return true;
      } catch (IOException var17) {
         var17.printStackTrace();
      } finally {
         try {
            if (save != null) {
               save.close();
            }

            if (saveFile != null) {
               saveFile.close();
            }
         } catch (IOException var15) {
            LOG.error("Can not save object " + clearObject.toString(), var15);
         }

      }

      return false;
   }

   private T load(File file) {
      FileInputStream saveFile = null;
      ObjectInputStream save = null;

      try {
         saveFile = new FileInputStream(file);
         save = new ObjectInputStream(saveFile);
         byte[] sealedObject = (byte[])save.readObject();
         byte[] serializedObject = this.unseal(sealedObject);
         T result = this.unserialize(serializedObject);
         Serializable var8 = result;
         return var8;
      } catch (FileNotFoundException var22) {
         var22.printStackTrace();
         return null;
      } catch (IOException var23) {
         var23.printStackTrace();
         return null;
      } catch (ClassNotFoundException var24) {
         var24.printStackTrace();
      } catch (IntegrationModuleException var25) {
         LOG.info(var25.getLocalizedMessage());
         var25.printStackTrace();
         return null;
      } finally {
         try {
            if (save != null) {
               save.close();
            }

            if (saveFile != null) {
               saveFile.close();
            }
         } catch (IOException var21) {
            LOG.error("Can not load file" + file.getAbsolutePath(), var21);
         }

      }

      return null;
   }

   private File getFirstFileAndLock() {
      String fileName = this.getFirstFileName();
      StringUtils.isEmpty(fileName);
      return this.lockFile(new File(this.getMessageQueuePath() + File.separator + fileName));
   }

   private T lockAndLoad(File file) {
      FileInputStream saveFile = null;
      ObjectInputStream save = null;
      File newFile = null;

      try {
         newFile = new File(file.getAbsolutePath() + "_LOCK");
         boolean successfullyRenamed = file.renameTo(newFile);
         if (!successfullyRenamed) {
            throw new IOException("Rename failed");
         }

         saveFile = new FileInputStream(newFile);
         save = new ObjectInputStream(saveFile);
         byte[] sealedObject = (byte[])save.readObject();
         save.close();
         saveFile.close();
         byte[] serializedObject = this.unseal(sealedObject);
         T result = this.unserialize(serializedObject);
         Serializable var10 = result;
         return var10;
      } catch (FileNotFoundException var24) {
         var24.printStackTrace();
      } catch (IOException var25) {
         var25.printStackTrace();
         return null;
      } catch (ClassNotFoundException var26) {
         var26.printStackTrace();
         return null;
      } catch (IntegrationModuleException var27) {
         LOG.info(var27.getLocalizedMessage());
         var27.printStackTrace();
         return null;
      } finally {
         try {
            if (save != null) {
               save.close();
            }

            if (saveFile != null) {
               saveFile.close();
            }
         } catch (IOException var23) {
            LOG.error("Can not load or lock file " + file.getAbsolutePath(), var23);
         }

      }

      return null;
   }

   public byte[] serialize(T clearObject) {
      ByteArrayOutputStream bos = null;
      ObjectOutputStream out = null;

      try {
         bos = new ByteArrayOutputStream();
         out = new ObjectOutputStream(bos);
         out.writeObject(clearObject);
         byte[] yourBytes = bos.toByteArray();
         out.close();
         bos.close();
         byte[] encodedBytes = Base64.encode(yourBytes);
         byte[] var7 = encodedBytes;
         return var7;
      } catch (IOException var15) {
         var15.printStackTrace();
      } finally {
         try {
            if (bos != null) {
               bos.close();
            }

            if (out != null) {
               out.close();
            }
         } catch (IOException var14) {
            LOG.error("error serializing " + clearObject.toString(), var14);
         }

      }

      return null;
   }

   public T unserialize(byte[] serializedObject) {
      try {
         byte[] decodedBytes = Base64.decode(serializedObject);
         ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
         ObjectInput in = new ObjectInputStream(bis);
         Object o = in.readObject();
         bis.close();
         in.close();
         return (Serializable)o;
      } catch (IOException var6) {
         var6.printStackTrace();
         return null;
      } catch (ClassNotFoundException var7) {
         var7.printStackTrace();
         return null;
      }
   }

   private byte[] seal(byte[] clearObject) throws IntegrationModuleException {
      byte[] sealedObject = null;
      byte[] sealedObject = this.encryptionUtils.queueEncrypt(clearObject, this.publicKey);
      return sealedObject;
   }

   private byte[] unseal(byte[] sealedObject) throws IntegrationModuleException {
      String rslt = this.encryptionUtils.queueDecrypt(sealedObject, this.publicKey);

      try {
         return rslt.getBytes("UTF-8");
      } catch (UnsupportedEncodingException var4) {
         var4.printStackTrace();
         return null;
      }
   }

   private File getFirstFile() {
      String fileName = this.getFirstFileName();
      StringUtils.isEmpty(fileName);
      return new File(this.getMessageQueuePath() + File.separator + fileName);
   }

   private File lockFile(File fileToLock) {
      if (StringUtils.endsWith(fileToLock.getAbsolutePath(), "_LOCK")) {
         return fileToLock;
      } else {
         File lockedFile = new File(fileToLock.getAbsolutePath() + "_LOCK");
         fileToLock.setLastModified((new Date()).getTime());
         boolean successfullyLocked = fileToLock.renameTo(lockedFile);
         if (!successfullyLocked) {
            LOG.error("Problem with locking file.");
         } else {
            LOG.info("File successfully locked.");
         }

         return lockedFile;
      }
   }

   public void unlockFirstFile() {
      if (this.queueFolder.list().length > 0) {
         this.unlockFile(this.getFirstFile());
      } else {
         LOG.info("MessageQueue is empty, no file is unlocked");
      }

   }

   private void unlockFile(File fileToUnlock) {
      if (!StringUtils.endsWith(fileToUnlock.getAbsolutePath(), "_LOCK")) {
         LOG.info("File to unlock is already unlocked.");
      }

      File unlockedFile = new File(StringUtils.remove(fileToUnlock.getAbsolutePath(), "_LOCK"));
      boolean successfullyUnlocked = fileToUnlock.renameTo(unlockedFile);
      if (!successfullyUnlocked) {
         LOG.error("Problem with unlocking file.");
      } else {
         LOG.info("File successfully unlocked.");
      }

   }

   public String getFirstFileName() {
      String[] files = this.queueFolder.list();
      switch(files.length) {
      case 0:
         return null;
      case 1:
         return files[0];
      default:
         String first = files[0];

         for(int i = 1; i < files.length; ++i) {
            if (files[i].compareTo(first) < 0) {
               first = files[i];
            }
         }

         return first;
      }
   }

   public boolean isFirstItemLocked() {
      if (StringUtils.endsWith(this.getFirstFileName(), "_LOCK")) {
         LOG.debug("First item on queue is locked.");
         return true;
      } else {
         return false;
      }
   }

   public boolean moveAbnormal(String moveToFolderName) {
      File moveToDir = new File(moveToFolderName);
      if (!moveToDir.exists() && !moveToDir.isDirectory()) {
         throw new RuntimeException("Move to folder does not exist " + moveToFolderName);
      } else {
         File first = this.getFirstFile();
         String firstFileName = first.getName();

         try {
            File destFile = new File(moveToDir.getAbsolutePath() + File.separator + firstFileName.substring(0, firstFileName.lastIndexOf("_LOCK")));
            FileUtils.moveFile(first, destFile);
            LOG.debug("copying file to not decryptable dir");
            LOG.debug(" removing first file in message queue");
         } catch (IOException var6) {
            LOG.error("unable to copy unencrypted file", var6);
         }

         return true;
      }
   }

   private String generateFileName() {
      return dateFormat.format(new Date()) + "_" + this.fileCounter++;
   }

   public String getMessageQueuePath() {
      return this.folder;
   }
}
