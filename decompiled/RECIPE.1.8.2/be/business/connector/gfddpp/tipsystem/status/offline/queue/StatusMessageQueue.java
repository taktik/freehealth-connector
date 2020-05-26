package be.business.connector.gfddpp.tipsystem.status.offline.queue;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PropertyHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.apache.log4j.Logger;

public class StatusMessageQueue implements Queue<StatusMessageType> {
   private static final String STATUS_MESSAGE_QUEUE_FOLDER_PROPERTY_NAME = "STATUS_MESSAGE_QUEUE_FOLDER";
   private static final String FILE_NAME_SEPARATOR = "_";
   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
   private String folder;
   private File queueFolder;
   private int fileCounter = 0;
   private PropertyHandler property;
   private EncryptionUtils encryptionUtils;
   private PublicKey publicKey;
   private JaxContextCentralizer jaxContext = JaxContextCentralizer.getInstance();
   private static final Logger LOG = Logger.getLogger(StatusMessageQueue.class);

   public StatusMessageQueue() {
   }

   public StatusMessageQueue(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) throws IntegrationModuleException {
      this.property = propertyHandler;
      this.encryptionUtils = encryptionUtils;

      try {
         this.initialize();
      } catch (Exception var4) {
         throw new IntegrationModuleException(I18nHelper.getLabel("error.creation.statusMessageQueue"), var4);
      }
   }

   private void initialize() throws IntegrationModuleException {
      if (this.property.hasProperty("STATUS_MESSAGE_QUEUE_FOLDER")) {
         this.setStatusMessageQueuePath(this.property.getProperty("STATUS_MESSAGE_QUEUE_FOLDER"));
         this.queueFolder = new File(this.getStatusMessageQueuePath());
         this.publicKey = this.encryptionUtils.getPublicKey();
         boolean var1 = this.queueFolder.exists();
         if (!var1) {
            boolean created = this.queueFolder.mkdirs();
            if (!created) {
               throw new RuntimeException("Not able to create folder " + this.getStatusMessageQueuePath());
            }
         }

      } else {
         throw new RuntimeException("MESSAGE_QUEUE_FOLDER not found in properties file !");
      }
   }

   private void setStatusMessageQueuePath(String folder) {
      this.folder = folder;
   }

   public String getStatusMessageQueuePath() {
      return this.folder;
   }

   public int size() {
      return this.queueFolder.list().length;
   }

   public boolean isEmpty() {
      FileFilter directoryFilter = new FileFilter() {
         public boolean accept(File file) {
            return !file.isDirectory();
         }
      };
      return (new ArrayList(Arrays.asList(this.queueFolder.listFiles(directoryFilter)))).isEmpty();
   }

   public boolean contains(Object o) {
      throw new UnsupportedOperationException();
   }

   public Iterator<StatusMessageType> iterator() {
      throw new UnsupportedOperationException();
   }

   public Object[] toArray() {
      throw new UnsupportedOperationException();
   }

   public <T> T[] toArray(T[] a) {
      throw new UnsupportedOperationException();
   }

   public boolean remove(Object o) {
      throw new UnsupportedOperationException();
   }

   public boolean containsAll(Collection<?> c) {
      throw new UnsupportedOperationException();
   }

   public boolean addAll(Collection<? extends StatusMessageType> c) {
      boolean result = true;

      StatusMessageType element;
      for(Iterator var4 = c.iterator(); var4.hasNext(); result &= this.offer(element)) {
         element = (StatusMessageType)var4.next();
      }

      return result;
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

   public boolean add(StatusMessageType statusMessage) {
      byte[] serialiazed;
      try {
         serialiazed = this.jaxContext.toXml(StatusMessageType.class, statusMessage).getBytes();
      } catch (GFDDPPException var8) {
         LOG.info(var8.getLocalizedMessage());
         return false;
      }

      Object var3 = null;

      byte[] sealedObject;
      try {
         sealedObject = this.seal(serialiazed);
      } catch (IntegrationModuleException var7) {
         LOG.info(var7.getLocalizedMessage());
         return false;
      }

      try {
         FileOutputStream saveFile = new FileOutputStream(this.getStatusMessageQueuePath() + File.separator + this.generateFileName());
         ObjectOutputStream save = new ObjectOutputStream(saveFile);
         save.writeObject(sealedObject);
         save.flush();
         save.close();
         saveFile.close();
         return true;
      } catch (IOException var6) {
         var6.printStackTrace();
         return false;
      }
   }

   public boolean offer(StatusMessageType e) {
      return this.add(e);
   }

   public StatusMessageType remove() {
      if (!this.isEmpty()) {
         File first = this.getFirst();
         StatusMessageType result = this.load(first);
         boolean deleted = first.delete();

         assert deleted;

         return result;
      } else {
         throw new NoSuchElementException();
      }
   }

   public StatusMessageType poll() {
      return !this.isEmpty() ? this.remove() : null;
   }

   public StatusMessageType element() {
      return null;
   }

   public StatusMessageType peek() {
      return !this.isEmpty() ? this.load(this.getFirst()) : null;
   }

   public String getMessageQueuePath() {
      return this.folder;
   }

   private String generateFileName() {
      return dateFormat.format(new Date()) + "_" + this.fileCounter++;
   }

   private File getFirst() {
      FileFilter directoryFilter = new FileFilter() {
         public boolean accept(File file) {
            return !file.isDirectory();
         }
      };
      File[] files = this.queueFolder.listFiles(directoryFilter);
      switch(files.length) {
      case 0:
         return null;
      case 1:
         return files[0];
      default:
         File first = files[0];

         for(int i = 1; i < files.length; ++i) {
            if (files[i].compareTo(first) < 0) {
               first = files[i];
            }
         }

         return first;
      }
   }

   private StatusMessageType load(File file) {
      try {
         FileInputStream saveFile = new FileInputStream(file);
         ObjectInputStream save = new ObjectInputStream(saveFile);
         Object var4 = null;

         byte[] sealedObject;
         try {
            sealedObject = (byte[])save.readObject();
         } catch (IOException var7) {
            var7.printStackTrace();
            LOG.error("IOException: the file:" + file.getName() + "will be deleted\n" + var7.getMessage());
            save.close();
            saveFile.close();
            file.delete();
            return null;
         }

         save.close();
         saveFile.close();
         byte[] serializedObject = this.unseal(sealedObject);
         StatusMessageType result = (StatusMessageType)this.jaxContext.toObject(StatusMessageType.class, serializedObject);
         return result;
      } catch (FileNotFoundException var8) {
         var8.printStackTrace();
         return null;
      } catch (IOException var9) {
         var9.printStackTrace();
         LOG.error("IOException: the file:" + file.getName() + "will be deleted\n" + var9.getMessage());
         return null;
      } catch (ClassNotFoundException var10) {
         var10.printStackTrace();
         return null;
      } catch (IntegrationModuleException var11) {
         var11.printStackTrace();
         return null;
      } catch (GFDDPPException var12) {
         var12.printStackTrace();
         return null;
      }
   }

   private byte[] unseal(byte[] sealedObject) throws IntegrationModuleException, GFDDPPException {
      try {
         return this.encryptionUtils.queueDecrypt(sealedObject, this.publicKey).getBytes("UTF-8");
      } catch (UnsupportedEncodingException var3) {
         LOG.error("UnsupportedEncodingException in unseal method", var3);
         throw new GFDDPPException("UnsupportedEncodingException in unseal method");
      }
   }

   private byte[] getBytes(InputStream inputStream) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();

         int ch;
         while((ch = inputStream.read()) >= 0) {
            baos.write(ch);
         }

         byte[] unsealedData = baos.toByteArray();
         return unsealedData;
      } catch (IOException var5) {
         var5.printStackTrace();
         return null;
      }
   }

   private byte[] seal(byte[] clearObject) throws IntegrationModuleException {
      byte[] sealedObject = null;
      byte[] sealedObject = this.encryptionUtils.queueEncrypt(clearObject, this.publicKey);
      return sealedObject;
   }
}
