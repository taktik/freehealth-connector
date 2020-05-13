package javax.script;

import java.io.Reader;

public interface Compilable {
   CompiledScript compile(String var1) throws ScriptException;

   CompiledScript compile(Reader var1) throws ScriptException;
}
