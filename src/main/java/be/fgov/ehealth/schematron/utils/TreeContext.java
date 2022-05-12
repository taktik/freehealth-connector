package be.fgov.ehealth.schematron.utils;

import java.util.HashMap;
import java.util.Stack;

public class TreeContext extends Stack<HashMap<String, Integer>> {
   boolean justClosed;
   Stack<String> parents = new Stack();

   public TreeContext() {
   }

   public void onStartElement(String uri, String localName) {
      String pseudo = "*:" + localName + "[namespace-uri()='" + uri + "']";
      HashMap currContext;
      if (this.justClosed && this.size() != 0) {
         currContext = (HashMap)this.peek();
         Integer preceding = (Integer)currContext.get(pseudo);
         if (preceding != null) {
            int newCount = preceding + 1;
            currContext.remove(pseudo);
            preceding = newCount;
            currContext.put(pseudo, newCount);
         } else {
            currContext.put(pseudo, 1);
         }
      } else {
         currContext = new HashMap();
         currContext.put(pseudo, 1);
         this.push(currContext);
      }

      this.parents.push(pseudo);
      this.justClosed = false;
   }

   public synchronized boolean equals(Object o) {
      return super.equals(o);
   }

   public synchronized int hashCode() {
      return super.hashCode();
   }

   public void onEndElement() {
      if (this.justClosed) {
         this.pop();
      }

      this.justClosed = true;
      this.parents.pop();
   }

   public String currentContext() {
      StringBuffer sb = new StringBuffer();

      for(int i = 0; i < this.size(); ++i) {
         if (i == 0) {
            sb.append("/" + (String)this.parents.get(0) + "[1]");
         } else {
            String pseudo = (String)this.parents.get(i);
            sb.append(pseudo);
            HashMap<String, Integer> context = (HashMap)this.get(i);
            Integer n = (Integer)context.get(pseudo);
            sb.append("[" + (n == null ? "1" : n) + "]");
         }

         if (i < this.size() - 1) {
            sb.append("/");
         }
      }

      return sb.toString();
   }
}
