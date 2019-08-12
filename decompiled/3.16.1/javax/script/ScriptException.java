package javax.script;

public class ScriptException extends Exception {
   private String fileName;
   private int lineNumber;
   private int columnNumber;

   public ScriptException(String s) {
      super(s);
      this.fileName = null;
      this.lineNumber = -1;
      this.columnNumber = -1;
   }

   public ScriptException(Exception e) {
      super(e);
      this.fileName = null;
      this.lineNumber = -1;
      this.columnNumber = -1;
   }

   public ScriptException(String message, String fileName, int lineNumber) {
      super(message);
      this.fileName = fileName;
      this.lineNumber = lineNumber;
      this.columnNumber = -1;
   }

   public ScriptException(String message, String fileName, int lineNumber, int columnNumber) {
      super(message);
      this.fileName = fileName;
      this.lineNumber = lineNumber;
      this.columnNumber = columnNumber;
   }

   public String getMessage() {
      String ret = super.getMessage();
      if (this.fileName != null) {
         ret = ret + " in " + this.fileName;
         if (this.lineNumber != -1) {
            ret = ret + " at line number " + this.lineNumber;
         }

         if (this.columnNumber != -1) {
            ret = ret + " at column number " + this.columnNumber;
         }
      }

      return ret;
   }

   public int getLineNumber() {
      return this.lineNumber;
   }

   public int getColumnNumber() {
      return this.columnNumber;
   }

   public String getFileName() {
      return this.fileName;
   }
}
