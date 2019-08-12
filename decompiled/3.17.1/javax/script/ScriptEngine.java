package javax.script;

import java.io.Reader;

public interface ScriptEngine {
   String ARGV = "javax.script.argv";
   String FILENAME = "javax.script.filename";
   String ENGINE = "javax.script.engine";
   String ENGINE_VERSION = "javax.script.engine_version";
   String NAME = "javax.script.name";
   String LANGUAGE = "javax.script.language";
   String LANGUAGE_VERSION = "javax.script.language_version";

   Object eval(String var1, ScriptContext var2) throws ScriptException;

   Object eval(Reader var1, ScriptContext var2) throws ScriptException;

   Object eval(String var1) throws ScriptException;

   Object eval(Reader var1) throws ScriptException;

   Object eval(String var1, Bindings var2) throws ScriptException;

   Object eval(Reader var1, Bindings var2) throws ScriptException;

   void put(String var1, Object var2);

   Object get(String var1);

   Bindings getBindings(int var1);

   void setBindings(Bindings var1, int var2);

   Bindings createBindings();

   ScriptContext getContext();

   void setContext(ScriptContext var1);

   ScriptEngineFactory getFactory();
}
