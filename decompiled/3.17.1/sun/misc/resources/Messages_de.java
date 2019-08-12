package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_de extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "ERROR: In JAR-Datei {0} wurde ein ungültiges Versionsformat verwendet. Prüfen Sie in der Dokumentation, welches Versionsformat unterstützt wird."}, {"optpkg.attributeerror", "ERROR: In JAR-Datei {1} ist das erforderliche JAR-Manifestattribut {0} nicht festgelegt."}, {"optpkg.attributeserror", "ERROR: In JAR-Datei {0} sind einige erforderliche JAR-Manifestattribute nicht festgelegt."}};

   public Object[][] getContents() {
      return contents;
   }
}
