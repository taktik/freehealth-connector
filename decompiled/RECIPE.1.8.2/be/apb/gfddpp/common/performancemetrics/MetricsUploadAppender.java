package be.apb.gfddpp.common.performancemetrics;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.utils.CommonIOUtils;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public abstract class MetricsUploadAppender extends FileAppender {
   private static final Logger LOG = Logger.getLogger(MetricsUploadAppender.class);
   private Calendar previousTime;

   public MetricsUploadAppender() throws GFDDPPException {
   }

   public void doAppend(LoggingEvent event) {
      if (event != null) {
         super.doAppend(event);
      }

      try {
         LOG.info("Metric upload started! " + this.previousTime);
         if (this.previousTime != null && !Calendar.getInstance().before(this.previousTime)) {
            LOG.info("Metrics upload did not run because the 15 minute after the previousTime is not passed yet.");
         } else {
            String fileName = this.getFile();
            this.previousTime = null;
            LOG.info("File to find: " + fileName);
            Path file = Paths.get(fileName);
            LOG.info("found file: " + file.getFileName());
            LOG.info("found file exists? " + Files.exists(file, new LinkOption[0]));
            if (Files.exists(file, new LinkOption[0])) {
               LOG.info("found file size:" + Files.size(file));
            }

            if (Files.exists(file, new LinkOption[0]) && Files.size(file) > 5000L) {
               this.uploadFile(CommonIOUtils.compress(Files.readAllBytes(file)));
               super.close();
               Files.write(file, "".getBytes(), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING});
               this.setFile(file.toAbsolutePath().toString(), this.getAppend(), this.getBufferedIO(), this.getBufferSize());
               super.closed = false;
            } else {
               LOG.info("Metrics upload did not run because either the file didn't exist or the the file was too small.");
            }
         }
      } catch (Exception var4) {
         this.previousTime = Calendar.getInstance();
         this.previousTime.add(12, 15);
         LOG.warn("Metrics Upload Failed,", var4);
      }

   }

   public abstract void uploadFile(byte[] var1) throws Exception;
}
