package be.business.connector.core.utils;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class MessageQueueHelper {
   private static final Logger LOG = Logger.getLogger(MessageQueueHelper.class);

   public static void unlockLockedFilesOnQueue() {
      PropertyHandler propertyHandler = PropertyHandler.getInstance();
      if (propertyHandler.hasProperty("MESSAGE_QUEUE_FOLDER")) {
         String messageQueueFolderPath = propertyHandler.getProperty("MESSAGE_QUEUE_FOLDER");
         File messageQueueFolder = new File(messageQueueFolderPath);
         if (messageQueueFolder.exists()) {
            String lockedFileSuffix = "_LOCK";
            SuffixFileFilter suffixFileFilter = new SuffixFileFilter(lockedFileSuffix);
            Integer numberOfMinutes = propertyHandler.getIntegerProperty("locked.file.retention", "2");
            AgeFileFilter ageFileFilter = new AgeFileFilter(System.currentTimeMillis() - (long)(numberOfMinutes * 60 * 1000));
            Collection<File> lockedFiles = FileUtils.listFiles(messageQueueFolder, FileFilterUtils.and(new IOFileFilter[]{suffixFileFilter, ageFileFilter}), TrueFileFilter.INSTANCE);
            Iterator var9 = lockedFiles.iterator();

            while(var9.hasNext()) {
               File file = (File)var9.next();
               String lockedFileName = file.getAbsolutePath();
               File unlockedFile = new File(StringUtils.remove(lockedFileName, lockedFileSuffix));
               file.setLastModified((new Date()).getTime());
               Boolean succesFullyUnlocked = file.renameTo(unlockedFile);
               if (succesFullyUnlocked) {
                  LOG.info("File: " + lockedFileName + " successfully unlocked.");
               }
            }
         } else {
            LOG.info("No directory found on location: " + messageQueueFolderPath + ". No files unlocked");
         }
      } else {
         LOG.info("No MESSAGE_QUEUE_FOLDER property in properties file. No files unlocked.");
      }

   }
}
