package org.taktik.connector.business.recipeprojects.core.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.xml.namespace.NamespaceContext;

public class MapNamespaceContext implements NamespaceContext {
   private Map<String, String> nsMappings;

   public MapNamespaceContext() {
      this.nsMappings = new TreeMap();
      this.nsMappings.put("", "http://www.ehealth.fgov.be/standards/kmehr/schema/v1");
      this.nsMappings.put("xml", "http://www.w3.org/XML/1998/namespace");
      this.nsMappings.put("xmlns", "http://www.w3.org/2000/xmlns/");
   }

   public MapNamespaceContext(String... namespaceArray) {
      this();

      for(int i = 0; i < namespaceArray.length; ++i) {
         this.getNsMappings().put("ns" + Integer.toString(i + 1), namespaceArray[i]);
      }

   }

   public MapNamespaceContext(Map<String, String> namespaceMap) {
      this();
      this.nsMappings = namespaceMap;
   }

   protected final Map<String, String> getNsMappings() {
      return this.nsMappings;
   }

   public String getNamespaceURI(String prefix) {
      if (prefix == null) {
         throw new IllegalArgumentException("prefix must be provided");
      } else {
         return (String)this.nsMappings.get(prefix);
      }
   }

   public String getPrefix(String namespaceURI) {
      if (namespaceURI == null) {
         throw new IllegalArgumentException("namespaceURI must be provided");
      } else {
         Iterator var3 = this.nsMappings.entrySet().iterator();

         while(var3.hasNext()) {
            Entry<String, String> entity = (Entry)var3.next();
            if (((String)entity.getValue()).equals(namespaceURI)) {
               return (String)entity.getKey();
            }
         }

         return null;
      }
   }

   public Iterator getPrefixes(String namespaceURI) {
      if (namespaceURI == null) {
         throw new IllegalArgumentException("namespaceURI must be provided");
      } else {
         List<String> prefixes = new LinkedList();
         Iterator var4 = this.nsMappings.entrySet().iterator();

         while(var4.hasNext()) {
            Entry<String, String> entity = (Entry)var4.next();
            if (((String)entity.getValue()).equals(namespaceURI)) {
               prefixes.add((String)entity.getKey());
            }
         }

         return prefixes.iterator();
      }
   }
}
