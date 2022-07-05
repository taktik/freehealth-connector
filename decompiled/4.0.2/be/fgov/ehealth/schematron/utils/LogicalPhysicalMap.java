package be.fgov.ehealth.schematron.utils;

import java.util.HashMap;
import org.xml.sax.Locator;

public class LogicalPhysicalMap extends HashMap<String, PhysicalLocation> {
   public LogicalPhysicalMap() {
   }

   void handleMapping(TreeContext tc, Locator loc) {
      String pxpath = tc.currentContext();
      PhysicalLocation pl = (PhysicalLocation)this.get(pxpath);
      if (pl != null) {
         this.remove(pxpath);
         this.put(pxpath, new PhysicalLocation(loc.getLineNumber(), loc.getColumnNumber()));
      }

   }
}
