package be.ehealth.technicalconnector.session.renew;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.service.sts.utils.SAMLHelper;
import be.ehealth.technicalconnector.session.SessionItem;
import java.util.Timer;
import java.util.TimerTask;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlidingWindowRenewStrategy extends AbstractRenewStrategy {
   public static final String SLIDING_WINDOW_SKIP_THRESHOLD = "be.ehealth.technicalconnector.session.renew.SlidingWindowRenewStrategy.threshold";
   public static final String SLIDING_WINDOW_DIVIDER = "be.ehealth.technicalconnector.session.renew.SlidingWindowRenewStrategy.divider";
   private static final Logger LOG = LoggerFactory.getLogger(SlidingWindowRenewStrategy.class);
   private boolean scheduled = false;

   public void renew(SessionItem session) throws SessionManagementException {
      if (!this.scheduled) {
         DateTime end = SAMLHelper.getNotOnOrAfterCondition(session.getSAMLToken().getAssertion());
         long delay = calculateDelay(end);
         if (delay > (long)ConfigFactory.getConfigValidator().getIntegerProperty("be.ehealth.technicalconnector.session.renew.SlidingWindowRenewStrategy.threshold", 100)) {
            LOG.debug("Timer scheduled to execute within " + Duration.millis(delay).toPeriod().toString());
            Timer reloadTimer = new Timer("SlidingWindowRenewStrategy", true);
            reloadTimer.schedule(new SlidingWindowRenewStrategy.SlidingWindowTimerTask(this, session), delay);
            this.scheduled = true;
         } else {
            LOG.debug("Period too short to start the timer, executing reload now.");
            executeReload(session, this.getCacheServices());
         }
      }

   }

   private void reset() {
      this.scheduled = false;
   }

   private static long calculateDelay(DateTime end) {
      if (end.isAfterNow()) {
         Interval interval = new Interval(DateTime.now(), end);
         return interval.toDurationMillis() / (long)ConfigFactory.getConfigValidator().getIntegerProperty("be.ehealth.technicalconnector.session.renew.SlidingWindowRenewStrategy.divider", 2);
      } else {
         return 0L;
      }
   }

   public void flushCache() {
      super.flushCache();
      this.reset();
   }

   private static class SlidingWindowTimerTask extends TimerTask {
      private static final Logger LOG = LoggerFactory.getLogger(SlidingWindowRenewStrategy.SlidingWindowTimerTask.class);
      private SlidingWindowRenewStrategy strategy;
      private SessionItem session;

      public SlidingWindowTimerTask(SlidingWindowRenewStrategy strategy, SessionItem session) {
         this.strategy = strategy;
         this.session = session;
      }

      public void run() {
         try {
            SlidingWindowRenewStrategy var10000 = this.strategy;
            SlidingWindowRenewStrategy.executeReload(this.session, this.strategy.getCacheServices());
         } catch (SessionManagementException var2) {
            LOG.error("Unable to renew session", var2);
         }

         this.strategy.reset();
      }
   }
}
