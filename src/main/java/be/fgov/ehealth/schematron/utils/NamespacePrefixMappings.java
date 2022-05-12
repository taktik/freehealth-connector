package be.fgov.ehealth.schematron.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.AttributesImpl;

public class NamespacePrefixMappings {
   static Logger LOG = LoggerFactory.getLogger(NamespacePrefixMappings.class);
   private HashMap<String, String> nsMap = new HashMap();
   private HashMap<String, String> prefixMap = new HashMap();
   private int custom;

   public NamespacePrefixMappings() {
   }

   public String prefixForNs(String ns) {
      return (String)this.nsMap.get(ns);
   }

   public String nsForPrefix(String prefix) {
      return (String)this.prefixMap.get(prefix);
   }

   public void registerMapping(String prefix, String ns) {
      if (this.nsMap.get(ns) == null && this.prefixMap.get(prefix) == null) {
         LOG.debug("Registering Namespace/prefix binding:" + prefix + " " + ns);
         this.prefixMap.put(prefix, ns);
         this.nsMap.put(ns, prefix);
      } else {
         throw new IllegalArgumentException("Cannot register a prefix or Namespace name that has already been registered");
      }
   }

   public String registerUnprefixed(String ns) {
      String customPrefix = "ns" + this.custom;
      ++this.custom;
      this.registerMapping(customPrefix, ns);
      return customPrefix;
   }

   public String fixupXpath(String pseudo) {
      String ret = pseudo.replaceAll("\\{\\}", "");

      while(true) {
         int n1 = ret.indexOf("{");
         if (n1 == -1) {
            return ret;
         }

         int n2 = ret.indexOf("}");
         String uri = ret.substring(n1 + 1, n2);
         LOG.trace("Fixing up URI: <" + uri + ">");
         String prefix = this.prefixForNs(uri);
         ret = ret.replaceAll("\\{" + uri + "\\}", prefix + ":");
      }
   }

   public ArrayList<AttributesImpl> asAttributes() {
      ArrayList<AttributesImpl> a = new ArrayList();
      Iterator<String> iter = this.prefixMap.keySet().iterator();

      while(iter.hasNext()) {
         String prefix = (String)iter.next();
         String uri = this.nsForPrefix(prefix);
         AttributesImpl atts = new AttributesImpl();
         atts.addAttribute("", "prefix", "prefix", "CDATA", prefix);
         atts.addAttribute("", "uri", "uri", "CDATA", uri);
         a.add(atts);
      }

      return a;
   }
}
