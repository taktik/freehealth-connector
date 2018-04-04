package javax.smartcardio;

import java.security.AccessController;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.Collections;
import java.util.List;
import sun.security.action.GetPropertyAction;
import sun.security.jca.GetInstance;
import sun.security.jca.GetInstance.Instance;

public final class TerminalFactory {
   private static final String PROP_NAME = "javax.smartcardio.TerminalFactory.DefaultType";
   private static final String defaultType;
   private static final TerminalFactory defaultFactory;
   private final TerminalFactorySpi spi;
   private final Provider provider;
   private final String type;

   private TerminalFactory(TerminalFactorySpi spi, Provider provider, String type) {
      this.spi = spi;
      this.provider = provider;
      this.type = type;
   }

   public static String getDefaultType() {
      return defaultType;
   }

   public static TerminalFactory getDefault() {
      return defaultFactory;
   }

   public static TerminalFactory getInstance(String type, Object params) throws NoSuchAlgorithmException {
      Instance instance = GetInstance.getInstance("TerminalFactory", TerminalFactorySpi.class, type, params);
      return new TerminalFactory((TerminalFactorySpi)instance.impl, instance.provider, type);
   }

   public static TerminalFactory getInstance(String type, Object params, String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
      Instance instance = GetInstance.getInstance("TerminalFactory", TerminalFactorySpi.class, type, params, provider);
      return new TerminalFactory((TerminalFactorySpi)instance.impl, instance.provider, type);
   }

   public static TerminalFactory getInstance(String type, Object params, Provider provider) throws NoSuchAlgorithmException {
      Instance instance = GetInstance.getInstance("TerminalFactory", TerminalFactorySpi.class, type, params, provider);
      return new TerminalFactory((TerminalFactorySpi)instance.impl, instance.provider, type);
   }

   public Provider getProvider() {
      return this.provider;
   }

   public String getType() {
      return this.type;
   }

   public CardTerminals terminals() {
      return this.spi.engineTerminals();
   }

   public String toString() {
      return "TerminalFactory for type " + this.type + " from provider " + this.provider.getName();
   }

   static {
      String type = ((String)AccessController.doPrivileged(new GetPropertyAction("javax.smartcardio.TerminalFactory.DefaultType", "PC/SC"))).trim();
      TerminalFactory factory = null;

      try {
         factory = getInstance(type, (Object)null);
      } catch (Exception var5) {
         ;
      }

      if (factory == null) {
         try {
            type = "PC/SC";
            Provider sun = Security.getProvider("SunPCSC");
            if (sun == null) {
               Class clazz = Class.forName("sun.security.smartcardio.SunPCSC");
               sun = (Provider)clazz.newInstance();
            }

            factory = getInstance(type, (Object)null, (Provider)sun);
         } catch (Exception var4) {
            ;
         }
      }

      if (factory == null) {
         type = "None";
         factory = new TerminalFactory(TerminalFactory.NoneFactorySpi.INSTANCE, TerminalFactory.NoneProvider.INSTANCE, "None");
      }

      defaultType = type;
      defaultFactory = factory;
   }

   private static final class NoneCardTerminals extends CardTerminals {
      static final CardTerminals INSTANCE = new TerminalFactory.NoneCardTerminals();

      public List<CardTerminal> list(CardTerminals.State state) throws CardException {
         if (state == null) {
            throw new NullPointerException();
         } else {
            return Collections.emptyList();
         }
      }

      public boolean waitForChange(long timeout) throws CardException {
         throw new IllegalStateException("no terminals");
      }
   }

   private static final class NoneFactorySpi extends TerminalFactorySpi {
      static final TerminalFactorySpi INSTANCE = new TerminalFactory.NoneFactorySpi();

      protected CardTerminals engineTerminals() {
         return TerminalFactory.NoneCardTerminals.INSTANCE;
      }
   }

   private static final class NoneProvider extends Provider {
      static final Provider INSTANCE = new TerminalFactory.NoneProvider();

      private NoneProvider() {
         super("None", 1.0D, "none");
      }
   }
}
