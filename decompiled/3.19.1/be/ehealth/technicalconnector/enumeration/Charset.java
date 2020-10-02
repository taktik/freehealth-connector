package be.ehealth.technicalconnector.enumeration;

public enum Charset {
   UTF_8("UTF-8"),
   ASCII("ASCII"),
   ISO_8859_1("ISO-8859-1"),
   UNICODEBIGUNMARKED("UnicodeBigUnmarked");

   private String name;

   private Charset(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
