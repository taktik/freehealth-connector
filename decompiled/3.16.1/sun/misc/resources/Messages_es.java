package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_es extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "ERROR: el formato del archivo JAR {0} pertenece a una versión no válida. Busque en la documentación el formato de una versión soportada."}, {"optpkg.attributeerror", "ERROR: el atributo obligatorio JAR manifest {0} no está definido en el archivo JAR {1}."}, {"optpkg.attributeserror", "ERROR: algunos atributos obligatorios JAR manifest no están definidos en el archivo JAR {0}."}};

   public Object[][] getContents() {
      return contents;
   }
}
