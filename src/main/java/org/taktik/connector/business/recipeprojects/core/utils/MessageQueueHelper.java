package org.taktik.connector.business.recipeprojects.core.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;
import java.util.Date;

/**
 * @author Liesje Demuynck.
 */
public class MessageQueueHelper {

    private final static Logger LOG = LogManager.getLogger(MessageQueueHelper.class);

    public static void unlockLockedFilesOnQueue() {
    final PropertyHandler propertyHandler = PropertyHandler.getInstance();
        if (propertyHandler.hasProperty("MESSAGE_QUEUE_FOLDER")) {
            String messageQueueFolderPath = propertyHandler.getProperty("MESSAGE_QUEUE_FOLDER");
            File messageQueueFolder = new File(messageQueueFolderPath);
            if (messageQueueFolder.exists()) {
                String lockedFileSuffix = "_LOCK";
                SuffixFileFilter suffixFileFilter = new SuffixFileFilter(lockedFileSuffix);

                Integer numberOfMinutes = propertyHandler.getIntegerProperty("locked.file.retention", "2");

                AgeFileFilter ageFileFilter = new AgeFileFilter(System.currentTimeMillis() - (numberOfMinutes * 60 * 1000));

                Collection<File> lockedFiles = FileUtils.listFiles(messageQueueFolder, FileFilterUtils.and(suffixFileFilter, ageFileFilter), TrueFileFilter.INSTANCE);
                for (File file : lockedFiles) {
                    String lockedFileName = file.getAbsolutePath();
                    File unlockedFile = new File(StringUtils.remove(lockedFileName, lockedFileSuffix));
                    file.setLastModified(new Date().getTime());
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
