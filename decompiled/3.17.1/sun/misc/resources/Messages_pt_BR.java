package sun.misc.resources;

import java.util.ListResourceBundle;

public class Messages_pt_BR extends ListResourceBundle {
   private static final Object[][] contents = new Object[][]{{"optpkg.versionerror", "ERRO: formato de versão inválido usado no arquivo JAR {0}. Verifique a documentação para obter o formato de versão suportado."}, {"optpkg.attributeerror", "ERRO: o atributo de manifesto JAR {0} necessário não está definido no arquivo JAR {1}."}, {"optpkg.attributeserror", "ERRO: alguns atributos de manifesto JAR necessários não estão definidos no arquivo JAR {0}."}};

   public Object[][] getContents() {
      return contents;
   }
}
