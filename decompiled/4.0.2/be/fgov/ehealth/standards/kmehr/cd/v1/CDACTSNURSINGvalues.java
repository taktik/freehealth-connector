package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACTS-NURSINGvalues"
)
@XmlEnum
public enum CDACTSNURSINGvalues {
   @XmlEnumValue("NMF001")
   NMF_001("NMF001"),
   @XmlEnumValue("NMF002")
   NMF_002("NMF002"),
   @XmlEnumValue("NMF011")
   NMF_011("NMF011"),
   @XmlEnumValue("NMF012")
   NMF_012("NMF012"),
   @XmlEnumValue("NMF013")
   NMF_013("NMF013"),
   @XmlEnumValue("NMF021")
   NMF_021("NMF021"),
   @XmlEnumValue("NMF022")
   NMF_022("NMF022"),
   @XmlEnumValue("NMF031")
   NMF_031("NMF031"),
   @XmlEnumValue("NMF040")
   NMF_040("NMF040"),
   @XmlEnumValue("NMF041")
   NMF_041("NMF041"),
   @XmlEnumValue("NMF042")
   NMF_042("NMF042"),
   @XmlEnumValue("NMF043")
   NMF_043("NMF043"),
   @XmlEnumValue("NMF044")
   NMF_044("NMF044"),
   @XmlEnumValue("NMF045")
   NMF_045("NMF045"),
   @XmlEnumValue("NMF046")
   NMF_046("NMF046"),
   @XmlEnumValue("NMF047")
   NMF_047("NMF047"),
   @XmlEnumValue("NMF051")
   NMF_051("NMF051"),
   @XmlEnumValue("NMF052")
   NMF_052("NMF052"),
   @XmlEnumValue("NMF061")
   NMF_061("NMF061"),
   @XmlEnumValue("NMF062")
   NMF_062("NMF062"),
   @XmlEnumValue("NMF071")
   NMF_071("NMF071"),
   @XmlEnumValue("NMF072")
   NMF_072("NMF072"),
   @XmlEnumValue("NMF073")
   NMF_073("NMF073"),
   @XmlEnumValue("NMF074")
   NMF_074("NMF074"),
   @XmlEnumValue("NMF081")
   NMF_081("NMF081"),
   @XmlEnumValue("NMF082")
   NMF_082("NMF082"),
   @XmlEnumValue("NMF091")
   NMF_091("NMF091"),
   @XmlEnumValue("NMF092")
   NMF_092("NMF092"),
   @XmlEnumValue("NMF093")
   NMF_093("NMF093"),
   @XmlEnumValue("NMF101")
   NMF_101("NMF101"),
   @XmlEnumValue("NMF111")
   NMF_111("NMF111"),
   @XmlEnumValue("NMF112")
   NMF_112("NMF112"),
   @XmlEnumValue("NMF113")
   NMF_113("NMF113"),
   @XmlEnumValue("NMF114")
   NMF_114("NMF114"),
   @XmlEnumValue("NMF115")
   NMF_115("NMF115"),
   @XmlEnumValue("NMF116")
   NMF_116("NMF116"),
   @XmlEnumValue("NMF121")
   NMF_121("NMF121"),
   @XmlEnumValue("NMF999")
   NMF_999("NMF999");

   private final String value;

   private CDACTSNURSINGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACTSNURSINGvalues fromValue(String v) {
      CDACTSNURSINGvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDACTSNURSINGvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
