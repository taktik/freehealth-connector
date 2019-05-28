package javax.script;

import java.io.Reader;

public abstract class AbstractScriptEngine implements ScriptEngine {
   protected ScriptContext context;

   public AbstractScriptEngine() {
      this.context = new SimpleScriptContext();
   }

   public AbstractScriptEngine(Bindings n) {
      this();
      if (n == null) {
         throw new NullPointerException("n is null");
      } else {
         this.context.setBindings(n, 100);
      }
   }

   public void setContext(ScriptContext ctxt) {
      if (ctxt == null) {
         throw new NullPointerException("null context");
      } else {
         this.context = ctxt;
      }
   }

   public ScriptContext getContext() {
      return this.context;
   }

   public Bindings getBindings(int scope) {
      if (scope == 200) {
         return this.context.getBindings(200);
      } else if (scope == 100) {
         return this.context.getBindings(100);
      } else {
         throw new IllegalArgumentException("Invalid scope value.");
      }
   }

   public void setBindings(Bindings bindings, int scope) {
      if (scope == 200) {
         this.context.setBindings(bindings, 200);
      } else {
         if (scope != 100) {
            throw new IllegalArgumentException("Invalid scope value.");
         }

         this.context.setBindings(bindings, 100);
      }

   }

   public void put(String key, Object value) {
      Bindings nn = this.getBindings(100);
      if (nn != null) {
         nn.put(key, value);
      }

   }

   public Object get(String key) {
      Bindings nn = this.getBindings(100);
      return nn != null ? nn.get(key) : null;
   }

   public Object eval(Reader reader, Bindings bindings) throws ScriptException {
      ScriptContext ctxt = this.getScriptContext(bindings);
      return this.eval((Reader)reader, (ScriptContext)ctxt);
   }

   public Object eval(String script, Bindings bindings) throws ScriptException {
      ScriptContext ctxt = this.getScriptContext(bindings);
      return this.eval((String)script, (ScriptContext)ctxt);
   }

   public Object eval(Reader reader) throws ScriptException {
      return this.eval((Reader)reader, (ScriptContext)this.context);
   }

   public Object eval(String script) throws ScriptException {
      return this.eval((String)script, (ScriptContext)this.context);
   }

   protected ScriptContext getScriptContext(Bindings nn) {
      SimpleScriptContext ctxt = new SimpleScriptContext();
      Bindings gs = this.getBindings(200);
      if (gs != null) {
         ctxt.setBindings(gs, 200);
      }

      if (nn != null) {
         ctxt.setBindings(nn, 100);
         ctxt.setReader(this.context.getReader());
         ctxt.setWriter(this.context.getWriter());
         ctxt.setErrorWriter(this.context.getErrorWriter());
         return ctxt;
      } else {
         throw new NullPointerException("Engine scope Bindings may not be null.");
      }
   }
}
