package javax.script;

public abstract class CompiledScript {
   public abstract Object eval(ScriptContext var1) throws ScriptException;

   public Object eval(Bindings bindings) throws ScriptException {
      ScriptContext ctxt = this.getEngine().getContext();
      if (bindings != null) {
         SimpleScriptContext tempctxt = new SimpleScriptContext();
         tempctxt.setBindings(bindings, 100);
         tempctxt.setBindings(((ScriptContext)ctxt).getBindings(200), 200);
         tempctxt.setWriter(((ScriptContext)ctxt).getWriter());
         tempctxt.setReader(((ScriptContext)ctxt).getReader());
         tempctxt.setErrorWriter(((ScriptContext)ctxt).getErrorWriter());
         ctxt = tempctxt;
      }

      return this.eval((ScriptContext)ctxt);
   }

   public Object eval() throws ScriptException {
      return this.eval(this.getEngine().getContext());
   }

   public abstract ScriptEngine getEngine();
}
