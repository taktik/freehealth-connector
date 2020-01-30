package be.apb.gfddpp.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyHandler {
   private static PropertyHandler instance = null;
   private Properties properties;

   public PropertyHandler(InputStream propertyfile) {
      try {
         this.properties = new Properties();
         this.properties.load(this.getResourceAsStream("/smc.properties"));
         this.properties.load(propertyfile);
         instance = this;
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public PropertyHandler(String propertyfile) {
      try {
         this.properties = new Properties();
         this.properties.load(this.getResourceAsStream("/smc.properties"));
         Path configFile = Paths.get(propertyfile);
         if (Files.exists(configFile, new LinkOption[0])) {
            try {
               Charset charset = StandardCharsets.UTF_8;
               String content = new String(Files.readAllBytes(configFile), charset);
               String pathToFile = Paths.get(propertyfile).getParent().toString().replace("\\", "/");
               content = content.replaceAll("%CONF%", pathToFile);
               Files.write(configFile, content.getBytes(charset), new OpenOption[0]);
            } finally {
               this.properties.load(Files.newInputStream(configFile));
            }
         }

         instance = this;
      } catch (Exception var10) {
         throw new RuntimeException(var10);
      }
   }

   public InputStream getResourceAsStream(String path) throws IOException {
      try {
         Path filePath = Paths.get(path);
         return Files.newInputStream(filePath);
      } catch (Throwable var4) {
         InputStream stream = PropertyHandler.class.getResourceAsStream(path);
         if (stream == null) {
            throw new IOException("Invalid resource " + path);
         } else {
            return stream;
         }
      }
   }

   public PropertyHandler(Properties properties) {
      this.properties = properties;
      instance = this;
   }

   public static PropertyHandler getInstance() {
      return instance;
   }

   public String getProperty(String string) {
      return this.getProperty(string, (String)null);
   }

   public Integer getIntegerProperty(String string) {
      return Integer.parseInt(this.getProperty(string));
   }

   public URL getURLProperty(String string) {
      try {
         String prop = this.getProperty(string);
         if (prop != null && prop.contains("META-INF")) {
            return this.getClass().getResource(prop);
         } else {
            String wsdl = this.getProperty(string);
            if (wsdl == null) {
               return null;
            } else {
               File f = new File(wsdl);
               if (f.exists()) {
                  return f.toURI().toURL();
               } else {
                  URL url = new URL(wsdl);

                  try {
                     url.openStream().close();
                  } catch (IOException var7) {
                     throw new RuntimeException(var7);
                  }

                  return url;
               }
            }
         }
      } catch (Throwable var8) {
         throw new RuntimeException(var8);
      }
   }

   public Integer getIntegerProperty(String string, String defaultValue) {
      return Integer.parseInt(this.getProperty(string, defaultValue));
   }

   public String getProperty(String string, String defaultValue) {
      if (this.properties == null) {
         return defaultValue;
      } else {
         if (!this.properties.containsKey(string)) {
         }

         return this.properties.getProperty(string, defaultValue);
      }
   }

   public boolean hasProperty(String key) {
      return this.properties != null && this.properties.containsKey(key);
   }

   public Properties getProperties() {
      return this.properties;
   }

   public List<String> getMatchingProperties(String rootKey) {
      int i = 1;
      ArrayList ret = new ArrayList();

      while(true) {
         String key = rootKey + "." + i;
         if (this.properties.getProperty(key) == null) {
            return ret;
         }

         ret.add(this.getProperty(key));
         ++i;
      }
   }
}
