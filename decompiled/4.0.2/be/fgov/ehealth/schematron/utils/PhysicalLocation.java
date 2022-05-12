package be.fgov.ehealth.schematron.utils;

import org.xml.sax.helpers.AttributesImpl;

public class PhysicalLocation {
   public int line;
   public int col;

   public PhysicalLocation() {
   }

   public PhysicalLocation(int line, int col) {
      this.line = line;
      this.col = col;
   }

   public AttributesImpl addAsAttributes(AttributesImpl atts) {
      atts.addAttribute("", "line", "line", "CDATA", "" + this.line);
      atts.addAttribute("", "col", "col", "CDATA", "" + this.col);
      return atts;
   }
}
