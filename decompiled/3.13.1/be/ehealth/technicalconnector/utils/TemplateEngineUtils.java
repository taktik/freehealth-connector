package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TemplateEngineUtils {
   private static final Logger LOG = LoggerFactory.getLogger(TemplateEngineUtils.class);
   protected static final VelocityEngine VELOCITY_ENGINE;

   private TemplateEngineUtils() {
      throw new UnsupportedOperationException();
   }

   public static String generate(Map<String, Object> ctx, String templateLocation) {
      VelocityContext context = new VelocityContext();
      LOG.debug("Context received with " + ctx.size() + " parameters");
      Iterator i$ = ctx.entrySet().iterator();

      while(i$.hasNext()) {
         Entry<String, Object> entry = (Entry)i$.next();
         context.put((String)entry.getKey(), entry.getValue());
      }

      Template template = VELOCITY_ENGINE.getTemplate(templateLocation, "UTF-8");
      StringWriter writer = new StringWriter();
      template.merge(context, writer);
      return writer.toString();
   }

   public static String generateXML(Map<String, Object> ctx, String templateLocation) {
      return ConnectorXmlUtils.format(ConnectorXmlUtils.flatten(generate(ctx, templateLocation)));
   }

   static {
      LOG.info("Init of TemplateEngineUtils");
      VELOCITY_ENGINE = new VelocityEngine();
      VELOCITY_ENGINE.setProperty("resource.loader", "classpath");
      VELOCITY_ENGINE.setProperty("classpath.resource.loader.class", TemplateEngineUtils.ClasspathResourceLoader.class.getName());
      VELOCITY_ENGINE.setProperty("runtime.log.logsystem.class", TemplateEngineUtils.Slf4jLogChute.class.getName());
      VELOCITY_ENGINE.setProperty("velocimacro.library", "/templates/VM_connector_library.vm");
      VELOCITY_ENGINE.setProperty("resource.manager.logwhenfound", "true");
      VELOCITY_ENGINE.init();
   }

   public static class Slf4jLogChute implements LogChute {
      private static final Logger VEL_LOG = LoggerFactory.getLogger("org.apache.velocity");

      public void init(RuntimeServices rs) {
         this.log(0, "Slf4jLogChute initizalized");
      }

      public void log(int level, String message) {
         switch(level) {
         case -1:
            VEL_LOG.trace(message);
            break;
         case 0:
         default:
            VEL_LOG.debug(message);
            break;
         case 1:
            VEL_LOG.info(message);
            break;
         case 2:
            VEL_LOG.warn(message);
            break;
         case 3:
            VEL_LOG.error(message);
         }

      }

      public void log(int level, String message, Throwable t) {
         switch(level) {
         case -1:
            VEL_LOG.trace(message, t);
            break;
         case 0:
         default:
            VEL_LOG.debug(message, t);
            break;
         case 1:
            VEL_LOG.info(message, t);
            break;
         case 2:
            VEL_LOG.warn(message, t);
            break;
         case 3:
            VEL_LOG.error(message, t);
         }

      }

      public boolean isLevelEnabled(int level) {
         switch(level) {
         case -1:
            return VEL_LOG.isTraceEnabled();
         case 0:
            return VEL_LOG.isDebugEnabled();
         case 1:
            return VEL_LOG.isInfoEnabled();
         case 2:
            return VEL_LOG.isWarnEnabled();
         case 3:
            return VEL_LOG.isErrorEnabled();
         default:
            return true;
         }
      }
   }

   public static class ClasspathResourceLoader extends ResourceLoader {
      public void init(ExtendedProperties configuration) {
      }

      public InputStream getResourceStream(String source) throws ResourceNotFoundException {
         try {
            return ConnectorIOUtils.getResourceAsStream(source);
         } catch (TechnicalConnectorException var3) {
            throw new ResourceNotFoundException(var3);
         }
      }

      public boolean isSourceModified(Resource resource) {
         return false;
      }

      public long getLastModified(Resource resource) {
         return (new Date()).getTime();
      }
   }
}
