package be.business.connector.gfddpp.tipsystem.offline.queue;

import be.business.connector.core.utils.PropertyHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class QueueControle {
   private static Logger LOG = Logger.getLogger(QueueControle.class);
   private static final String MESSAGE_QUEUE_TIMER_KEY = "MESSAGE_QUEUE_TIMER";
   private final int period;
   private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
   private ScheduledFuture<?> messageHandle;

   public QueueControle() {
      PropertyHandler propertyHandler = PropertyHandler.getInstance();
      if (propertyHandler.hasProperty("MESSAGE_QUEUE_TIMER")) {
         this.period = Integer.decode(propertyHandler.getProperty("MESSAGE_QUEUE_TIMER"));
         LOG.info("Interval for between runs of queuebehavior in seconds: " + this.period);
      } else {
         LOG.info("Property missinng:MESSAGE_QUEUE_TIMER");
         throw new RuntimeException("MESSAGE_QUEUE_TIMER not found in properties file !");
      }
   }

   public void processMessages(Runnable processBehavior) {
      try {
         this.messageHandle = this.scheduler.scheduleWithFixedDelay(processBehavior, (long)this.period, (long)this.period, TimeUnit.SECONDS);
         LOG.info("remaining time(in seconds before next execution of the offline queue: " + this.messageHandle.getDelay(TimeUnit.SECONDS));
      } catch (Exception var3) {
         LOG.info("Something wrong happened in offline queue" + var3.getMessage());
      }

   }

   public ScheduledFuture<?> getMessageHandle() {
      return this.messageHandle;
   }

   public void shutdown() {
      LOG.info("Offline queue shutting down");
      this.scheduler.shutdown();
   }

   public int getPeriod() {
      return this.period;
   }
}
