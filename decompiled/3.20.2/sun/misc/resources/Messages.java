package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "ERROR: Invalid version format used in {0} JAR file. Check the documentation for the supported version format."}, {"optpkg.attributeerror", "ERROR: The required {0} JAR manifest attribute is not set in {1} JAR file."}, {"optpkg.attributeserror", "ERROR: Some required JAR manifest attributes are not set in {0} JAR file."}};

   public Object[][] getContents() {
      return contents;
   }
}
