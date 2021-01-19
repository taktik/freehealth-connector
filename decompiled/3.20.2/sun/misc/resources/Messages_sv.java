package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_sv extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "FEL: Ogiltigt versionsformat i {0} JAR-fil. Kontrollera i dokumentationen vilket versionsformat som stöds."}, {"optpkg.attributeerror", "FEL: Obligatoriskt JAR manifest-attribut {0} är inte inställt i {1} JAR-filen."}, {"optpkg.attributeserror", "FEL: Vissa obligatoriska JAR manifest-attribut är inte inställda i {0} JAR-filen."}};

   public Object[][] getContents() {
      return contents;
   }
}
