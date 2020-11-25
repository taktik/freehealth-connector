package javax.script;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Service;
import sun.misc.ServiceConfigurationError;
import sun.reflect.Reflection;
import sun.security.util.SecurityConstants;

public class ScriptEngineManager {
   private static final Logger LOG = LoggerFactory.getLogger(ScriptEngineManager.class);
   public static Boolean DEBUG = false;
   private Set<ScriptEngineFactory> engineSpis;
   private Map<String, ScriptEngineFactory> nameAssociations;
   private Map<String, ScriptEngineFactory> extensionAssociations;
   private Map<String, ScriptEngineFactory> mimeTypeAssociations;
   private Bindings globalScope;

   public ScriptEngineManager() {
      ClassLoader ctxtLoader = Thread.currentThread().getContextClassLoader();
      if (this.canCallerAccessLoader(ctxtLoader)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("ScriptEngineManager : using " + ctxtLoader);
         }

         this.init(ctxtLoader);
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("ScriptEngineManager : using bootstrap loader");
         }

         this.init((ClassLoader)null);
      }

   }

   public ScriptEngineManager(ClassLoader loader) {
      this.init(loader);
   }

   private void init(final ClassLoader loader) {
      this.globalScope = new SimpleBindings();
      this.engineSpis = new HashSet();
      this.nameAssociations = new HashMap();
      this.extensionAssociations = new HashMap();
      this.mimeTypeAssociations = new HashMap();
      AccessController.doPrivileged(new PrivilegedAction<Object>() {
         public Object run() {
            ScriptEngineManager.this.initEngines(loader);
            return null;
         }
      });
   }

   private void initEngines(ClassLoader loader) {
      Iterator itr = null;

      try {
         if (loader != null) {
            itr = Service.providers(ScriptEngineFactory.class, loader);
         } else {
            itr = Service.installedProviders(ScriptEngineFactory.class);
         }
      } catch (ServiceConfigurationError var4) {
         System.err.println("Can't find ScriptEngineFactory providers: " + var4.getMessage());
         if (DEBUG) {
            var4.printStackTrace();
         }

         return;
      }

      try {
         while(itr.hasNext()) {
            try {
               ScriptEngineFactory fact = (ScriptEngineFactory)itr.next();
               this.engineSpis.add(fact);
            } catch (ServiceConfigurationError var5) {
               System.err.println("ScriptEngineManager providers.next(): " + var5.getMessage());
               if (DEBUG) {
                  var5.printStackTrace();
               }
            }
         }

      } catch (ServiceConfigurationError var6) {
         System.err.println("ScriptEngineManager providers.hasNext(): " + var6.getMessage());
         if (DEBUG) {
            var6.printStackTrace();
         }

      }
   }

   public void setBindings(Bindings bindings) {
      if (bindings == null) {
         throw new IllegalArgumentException("Global scope cannot be null.");
      } else {
         this.globalScope = bindings;
      }
   }

   public Bindings getBindings() {
      return this.globalScope;
   }

   public void put(String key, Object value) {
      this.globalScope.put(key, value);
   }

   public Object get(String key) {
      return this.globalScope.get(key);
   }

   public ScriptEngine getEngineByName(String shortName) {
      if (shortName == null) {
         throw new NullPointerException();
      } else {
         Object obj = this.nameAssociations.get(shortName);
         if (null != obj) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;

            try {
               ScriptEngine engine = spi.getScriptEngine();
               engine.setBindings(this.getBindings(), 200);
               return engine;
            } catch (Exception var11) {
               if (DEBUG) {
                  var11.printStackTrace();
               }
            }
         }

         Iterator i$ = this.engineSpis.iterator();

         label58:
         while(true) {
            if (i$.hasNext()) {
               ScriptEngineFactory spi = (ScriptEngineFactory)i$.next();
               List names = null;

               try {
                  names = spi.getNames();
               } catch (Exception var10) {
                  if (DEBUG) {
                     var10.printStackTrace();
                  }
               }

               if (names == null) {
                  continue;
               }

               Iterator i$ = names.iterator();

               while(true) {
                  String name;
                  do {
                     if (!i$.hasNext()) {
                        continue label58;
                     }

                     name = (String)i$.next();
                  } while(!shortName.equals(name));

                  try {
                     ScriptEngine engine = spi.getScriptEngine();
                     engine.setBindings(this.getBindings(), 200);
                     return engine;
                  } catch (Exception var9) {
                     if (DEBUG) {
                        var9.printStackTrace();
                     }
                  }
               }
            }

            return null;
         }
      }
   }

   public ScriptEngine getEngineByExtension(String extension) {
      if (extension == null) {
         throw new NullPointerException();
      } else {
         Object obj = this.extensionAssociations.get(extension);
         if (null != obj) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;

            try {
               ScriptEngine engine = spi.getScriptEngine();
               engine.setBindings(this.getBindings(), 200);
               return engine;
            } catch (Exception var11) {
               if (DEBUG) {
                  var11.printStackTrace();
               }
            }
         }

         Iterator i$ = this.engineSpis.iterator();

         label59:
         while(true) {
            if (i$.hasNext()) {
               ScriptEngineFactory spi = (ScriptEngineFactory)i$.next();
               List exts = null;

               try {
                  exts = spi.getExtensions();
               } catch (Exception var10) {
                  if (DEBUG) {
                     var10.printStackTrace();
                  }
               }

               if (exts == null) {
                  continue;
               }

               Iterator i$ = exts.iterator();

               while(true) {
                  String ext;
                  do {
                     if (!i$.hasNext()) {
                        continue label59;
                     }

                     ext = (String)i$.next();
                  } while(!extension.equals(ext));

                  try {
                     ScriptEngine engine = spi.getScriptEngine();
                     engine.setBindings(this.getBindings(), 200);
                     return engine;
                  } catch (Exception var9) {
                     if (DEBUG) {
                        var9.printStackTrace();
                     }
                  }
               }
            }

            return null;
         }
      }
   }

   public ScriptEngine getEngineByMimeType(String mimeType) {
      if (mimeType == null) {
         throw new NullPointerException();
      } else {
         Object obj = this.mimeTypeAssociations.get(mimeType);
         if (null != obj) {
            ScriptEngineFactory spi = (ScriptEngineFactory)obj;

            try {
               ScriptEngine engine = spi.getScriptEngine();
               engine.setBindings(this.getBindings(), 200);
               return engine;
            } catch (Exception var11) {
               if (DEBUG) {
                  var11.printStackTrace();
               }
            }
         }

         Iterator i$ = this.engineSpis.iterator();

         label59:
         while(true) {
            if (i$.hasNext()) {
               ScriptEngineFactory spi = (ScriptEngineFactory)i$.next();
               List types = null;

               try {
                  types = spi.getMimeTypes();
               } catch (Exception var10) {
                  if (DEBUG) {
                     var10.printStackTrace();
                  }
               }

               if (types == null) {
                  continue;
               }

               Iterator i$ = types.iterator();

               while(true) {
                  String type;
                  do {
                     if (!i$.hasNext()) {
                        continue label59;
                     }

                     type = (String)i$.next();
                  } while(!mimeType.equals(type));

                  try {
                     ScriptEngine engine = spi.getScriptEngine();
                     engine.setBindings(this.getBindings(), 200);
                     return engine;
                  } catch (Exception var9) {
                     if (DEBUG) {
                        var9.printStackTrace();
                     }
                  }
               }
            }

            return null;
         }
      }
   }

   public List<ScriptEngineFactory> getEngineFactories() {
      List<ScriptEngineFactory> res = new ArrayList(this.engineSpis.size());
      Iterator i$ = this.engineSpis.iterator();

      while(i$.hasNext()) {
         ScriptEngineFactory spi = (ScriptEngineFactory)i$.next();
         res.add(spi);
      }

      return Collections.unmodifiableList(res);
   }

   public void registerEngineName(String name, ScriptEngineFactory factory) {
      if (name != null && factory != null) {
         this.nameAssociations.put(name, factory);
      } else {
         throw new NullPointerException();
      }
   }

   public void registerEngineMimeType(String type, ScriptEngineFactory factory) {
      if (type != null && factory != null) {
         this.mimeTypeAssociations.put(type, factory);
      } else {
         throw new NullPointerException();
      }
   }

   public void registerEngineExtension(String extension, ScriptEngineFactory factory) {
      if (extension != null && factory != null) {
         this.extensionAssociations.put(extension, factory);
      } else {
         throw new NullPointerException();
      }
   }

   private boolean canCallerAccessLoader(ClassLoader loader) {
      SecurityManager sm = System.getSecurityManager();
      if (sm != null) {
         ClassLoader callerLoader = this.getCallerClassLoader();
         if (callerLoader != null && (loader != callerLoader || !this.isAncestor(loader, callerLoader))) {
            try {
               sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
            } catch (SecurityException var5) {
               if (DEBUG) {
                  var5.printStackTrace();
               }

               return false;
            }
         }
      }

      return true;
   }

   private ClassLoader getCallerClassLoader() {
      Class caller = Reflection.getCallerClass(3);
      return caller == null ? null : caller.getClassLoader();
   }

   private boolean isAncestor(ClassLoader cl1, ClassLoader cl2) {
      do {
         cl2 = cl2.getParent();
         if (cl1 == cl2) {
            return true;
         }
      } while(cl2 != null);

      return false;
   }
}
