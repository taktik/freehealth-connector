package sun.net.httpserver;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Logger;

final class ServerConfig {
   static int clockTick;
   static final int DEFAULT_CLOCK_TICK = 10000;
   static final long DEFAULT_IDLE_INTERVAL = 30L;
   static final int DEFAULT_MAX_IDLE_CONNECTIONS = 200;
   static final long DEFAULT_MAX_REQ_TIME = -1L;
   static final long DEFAULT_MAX_RSP_TIME = -1L;
   static final long DEFAULT_TIMER_MILLIS = 1000L;
   static final int DEFAULT_MAX_REQ_HEADERS = 200;
   static final long DEFAULT_DRAIN_AMOUNT = 65536L;
   static long idleInterval;
   static long drainAmount;
   static int maxIdleConnections;
   private static int maxReqHeaders;
   static long maxReqTime;
   static long maxRspTime;
   static long timerMillis;
   static boolean debug;
   static boolean noDelay;

   static void checkLegacyProperties(final Logger logger) {
      AccessController.doPrivileged(new PrivilegedAction<Void>() {
         public Void run() {
            if (System.getProperty("sun.net.httpserver.readTimeout") != null) {
               logger.warning("sun.net.httpserver.readTimeout property is no longer used. Use sun.net.httpserver.maxReqTime instead.");
            }

            if (System.getProperty("sun.net.httpserver.writeTimeout") != null) {
               logger.warning("sun.net.httpserver.writeTimeout property is no longer used. Use sun.net.httpserver.maxRspTime instead.");
            }

            if (System.getProperty("sun.net.httpserver.selCacheTimeout") != null) {
               logger.warning("sun.net.httpserver.selCacheTimeout property is no longer used.");
            }

            return null;
         }
      });
   }

   static boolean debugEnabled() {
      return debug;
   }

   static long getIdleInterval() {
      return idleInterval;
   }

   static int getClockTick() {
      return clockTick;
   }

   static int getMaxIdleConnections() {
      return maxIdleConnections;
   }

   static long getDrainAmount() {
      return drainAmount;
   }

   static int getMaxReqHeaders() {
      return maxReqHeaders;
   }

   static long getMaxReqTime() {
      return maxReqTime;
   }

   static long getMaxRspTime() {
      return maxRspTime;
   }

   static long getTimerMillis() {
      return timerMillis;
   }

   static boolean noDelay() {
      return noDelay;
   }

   static {
      AccessController.doPrivileged(new PrivilegedAction<Void>() {
         public Void run() {
            ServerConfig.idleInterval = Long.getLong("sun.net.httpserver.idleInterval", 30L).longValue() * 1000L;
            ServerConfig.clockTick = Integer.getInteger("sun.net.httpserver.clockTick", 10000).intValue();
            ServerConfig.maxIdleConnections = Integer.getInteger("sun.net.httpserver.maxIdleConnections", 200).intValue();
            ServerConfig.drainAmount = Long.getLong("sun.net.httpserver.drainAmount", 65536L).longValue();
            ServerConfig.maxReqHeaders = Integer.getInteger("sun.net.httpserver.maxReqHeaders", 200).intValue();
            ServerConfig.maxReqTime = Long.getLong("sun.net.httpserver.maxReqTime", -1L).longValue();
            ServerConfig.maxRspTime = Long.getLong("sun.net.httpserver.maxRspTime", -1L).longValue();
            ServerConfig.timerMillis = Long.getLong("sun.net.httpserver.timerMillis", 1000L).longValue();
            ServerConfig.debug = Boolean.getBoolean("sun.net.httpserver.debug");
            ServerConfig.noDelay = Boolean.getBoolean("sun.net.httpserver.nodelay");
            return null;
         }
      });
   }
}
