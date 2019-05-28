package javax.script;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface ScriptContext {
   int ENGINE_SCOPE = 100;
   int GLOBAL_SCOPE = 200;

   void setBindings(Bindings var1, int var2);

   Bindings getBindings(int var1);

   void setAttribute(String var1, Object var2, int var3);

   Object getAttribute(String var1, int var2);

   Object removeAttribute(String var1, int var2);

   Object getAttribute(String var1);

   int getAttributesScope(String var1);

   Writer getWriter();

   Writer getErrorWriter();

   void setWriter(Writer var1);

   void setErrorWriter(Writer var1);

   Reader getReader();

   void setReader(Reader var1);

   List<Integer> getScopes();
}
