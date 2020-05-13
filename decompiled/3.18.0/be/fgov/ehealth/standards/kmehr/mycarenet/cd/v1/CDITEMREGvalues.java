package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-REGvalues"
)
@XmlEnum
public enum CDITEMREGvalues {
   @XmlEnumValue("isinitialintervention")
   ISINITIALINTERVENTION("isinitialintervention"),
   @XmlEnumValue("dominance")
   DOMINANCE("dominance"),
   @XmlEnumValue("existingbridge")
   EXISTINGBRIDGE("existingbridge"),
   @XmlEnumValue("observedlesion")
   OBSERVEDLESION("observedlesion"),
   @XmlEnumValue("segment")
   SEGMENT("segment"),
   @XmlEnumValue("totaltreatedlength")
   TOTALTREATEDLENGTH("totaltreatedlength"),
   @XmlEnumValue("bloodvesseldiameter")
   BLOODVESSELDIAMETER("bloodvesseldiameter"),
   @XmlEnumValue("procedure-device")
   PROCEDURE_DEVICE("procedure-device"),
   @XmlEnumValue("contrastproductused")
   CONTRASTPRODUCTUSED("contrastproductused"),
   @XmlEnumValue("reimbursementnomenclaturetype")
   REIMBURSEMENTNOMENCLATURETYPE("reimbursementnomenclaturetype"),
   @XmlEnumValue("intervention-type")
   INTERVENTION_TYPE("intervention-type"),
   @XmlEnumValue("intervention-site")
   INTERVENTION_SITE("intervention-site"),
   @XmlEnumValue("approach")
   APPROACH("approach"),
   @XmlEnumValue("navigationcomputer")
   NAVIGATIONCOMPUTER("navigationcomputer"),
   @XmlEnumValue("intervention-device")
   INTERVENTION_DEVICE("intervention-device"),
   @XmlEnumValue("bearingsurface")
   BEARINGSURFACE("bearingsurface"),
   @XmlEnumValue("graftused")
   GRAFTUSED("graftused"),
   @XmlEnumValue("acetabularinterface")
   ACETABULARINTERFACE("acetabularinterface"),
   @XmlEnumValue("femoralinterface")
   FEMORALINTERFACE("femoralinterface"),
   @XmlEnumValue("stem")
   STEM("stem"),
   @XmlEnumValue("cup")
   CUP("cup"),
   @XmlEnumValue("head")
   HEAD("head"),
   @XmlEnumValue("neck")
   NECK("neck"),
   @XmlEnumValue("insert")
   INSERT("insert"),
   @XmlEnumValue("prosthesis-other")
   PROSTHESIS_OTHER("prosthesis-other"),
   @XmlEnumValue("brand")
   BRAND("brand"),
   @XmlEnumValue("producer")
   PRODUCER("producer"),
   @XmlEnumValue("size")
   SIZE("size"),
   @XmlEnumValue("revisiontechnic")
   REVISIONTECHNIC("revisiontechnic"),
   @XmlEnumValue("kneeimplanttype")
   KNEEIMPLANTTYPE("kneeimplanttype"),
   @XmlEnumValue("kneeinsert")
   KNEEINSERT("kneeinsert"),
   @XmlEnumValue("tibialinterface")
   TIBIALINTERFACE("tibialinterface"),
   @XmlEnumValue("patellarinterface")
   PATELLARINTERFACE("patellarinterface"),
   @XmlEnumValue("patella")
   PATELLA("patella"),
   @XmlEnumValue("femur")
   FEMUR("femur"),
   @XmlEnumValue("tibia")
   TIBIA("tibia"),
   @XmlEnumValue("tickness")
   TICKNESS("tickness"),
   @XmlEnumValue("kneeprosthesis")
   KNEEPROSTHESIS("kneeprosthesis"),
   @XmlEnumValue("revisioncomponent")
   REVISIONCOMPONENT("revisioncomponent"),
   @XmlEnumValue("doctorsremark")
   DOCTORSREMARK("doctorsremark"),
   @XmlEnumValue("resynchronisation")
   RESYNCHRONISATION("resynchronisation"),
   @XmlEnumValue("pacingindication")
   PACINGINDICATION("pacingindication"),
   @XmlEnumValue("issecondoperator")
   ISSECONDOPERATOR("issecondoperator"),
   @XmlEnumValue("postpcitimi")
   POSTPCITIMI("postpcitimi"),
   @XmlEnumValue("poststenosis")
   POSTSTENOSIS("poststenosis"),
   @XmlEnumValue("infiltration")
   INFILTRATION("infiltration"),
   @XmlEnumValue("operation-type")
   OPERATION_TYPE("operation-type"),
   @XmlEnumValue("alignment")
   ALIGNMENT("alignment"),
   @XmlEnumValue("custominstrumentation")
   CUSTOMINSTRUMENTATION("custominstrumentation"),
   @XmlEnumValue("substitute")
   SUBSTITUTE("substitute"),
   @XmlEnumValue("modularneck")
   MODULARNECK("modularneck"),
   @XmlEnumValue("materialsremark")
   MATERIALSREMARK("materialsremark"),
   @XmlEnumValue("ortho-device")
   ORTHO_DEVICE("ortho-device"),
   @XmlEnumValue("devicename")
   DEVICENAME("devicename"),
   @XmlEnumValue("distributor")
   DISTRIBUTOR("distributor"),
   @XmlEnumValue("manufacturer")
   MANUFACTURER("manufacturer"),
   @XmlEnumValue("femoralreplacement")
   FEMORALREPLACEMENT("femoralreplacement"),
   @XmlEnumValue("acetabularreplacement")
   ACETABULARREPLACEMENT("acetabularreplacement"),
   @XmlEnumValue("revisedcomponent")
   REVISEDCOMPONENT("revisedcomponent"),
   @XmlEnumValue("cardioversion")
   CARDIOVERSION("cardioversion"),
   @XmlEnumValue("motivation")
   MOTIVATION("motivation"),
   @XmlEnumValue("criteriacomments")
   CRITERIACOMMENTS("criteriacomments"),
   @XmlEnumValue("comorbiditiescomments")
   COMORBIDITIESCOMMENTS("comorbiditiescomments"),
   @XmlEnumValue("informationprimo")
   INFORMATIONPRIMO("informationprimo"),
   @XmlEnumValue("lvef")
   LVEF("lvef"),
   @XmlEnumValue("qrs")
   QRS("qrs"),
   @XmlEnumValue("diameter")
   DIAMETER("diameter"),
   @XmlEnumValue("cardiacasynchrony")
   CARDIACASYNCHRONY("cardiacasynchrony"),
   @XmlEnumValue("indication")
   INDICATION("indication"),
   @XmlEnumValue("registration-type")
   REGISTRATION_TYPE("registration-type"),
   @XmlEnumValue("declarationdas28")
   DECLARATIONDAS_28("declarationdas28"),
   @XmlEnumValue("declarationhaq")
   DECLARATIONHAQ("declarationhaq"),
   @XmlEnumValue("declarationinfiltration")
   DECLARATIONINFILTRATION("declarationinfiltration"),
   @XmlEnumValue("declarationmedication")
   DECLARATIONMEDICATION("declarationmedication"),
   @XmlEnumValue("substance")
   SUBSTANCE("substance"),
   @XmlEnumValue("medicationstopinfo")
   MEDICATIONSTOPINFO("medicationstopinfo"),
   @XmlEnumValue("chapter4")
   CHAPTER_4("chapter4"),
   @XmlEnumValue("dosis")
   DOSIS("dosis"),
   @XmlEnumValue("frequency")
   FREQUENCY("frequency"),
   @XmlEnumValue("route")
   ROUTE("route");

   private final String value;

   private CDITEMREGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMREGvalues fromValue(String v) {
      CDITEMREGvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMREGvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
