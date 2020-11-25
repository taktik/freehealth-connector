package be.ehealth.businessconnector.ehbox.api.utils;

import be.ehealth.technicalconnector.utils.IdentifierType;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class QualityType {
   public static final QualityType AMBULANCE_RESCUER_NIHII;
   public static final QualityType AMBULANCE_RESCUER_SSIN;
   public static final QualityType APPLIED_PSYCH_BACHELOR_NIHII;
   public static final QualityType APPLIED_PSYCH_BACHELOR_SSIN;
   public static final QualityType AUDICIEN_NIHII;
   public static final QualityType AUDICIEN_SSIN;
   public static final QualityType AUDIOLOGIST_NIHII;
   public static final QualityType AUDIOLOGIST_SSIN;
   public static final QualityType CITIZEN;
   public static final QualityType CONSORTIUM_CBE;
   public static final QualityType CTRL_ORGANISM_EHP;
   public static final QualityType DENTIST_NIHII;
   public static final QualityType DENTIST_SSIN;
   public static final QualityType DIETICIAN_NIHII;
   public static final QualityType DIETICIAN_SSIN;
   public static final QualityType DOCTOR_NIHII;
   public static final QualityType DOCTOR_SSIN;
   public static final QualityType FAMILY_SCIENCE_BACHELOR_NIHII;
   public static final QualityType FAMILY_SCIENCE_BACHELOR_SSIN;
   public static final QualityType GERONTOLOGY_MASTER_NIHII;
   public static final QualityType GERONTOLOGY_MASTER_SSIN;
   public static final QualityType GROUP_NIHII;
   public static final QualityType GROUP_DOCTORS_NIHII;
   public static final QualityType GUARD_POST_NIHII;
   public static final QualityType HOME_SERVICES_NIHII;
   public static final QualityType HOSPITAL_NIHII;
   public static final QualityType IMAGING_TECHNOLOGIST_NIHII;
   public static final QualityType IMAGING_TECHNOLOGIST_SSIN;
   public static final QualityType IMPLANTPROVIDER_NIHII;
   public static final QualityType IMPLANTPROVIDER_SSIN;
   public static final QualityType INSTITUTION_CBE;
   public static final QualityType INSTITUTION_EHP_EHP;
   public static final QualityType LABO_NIHII;
   public static final QualityType LAB_TECHNOLOGIST_NIHII;
   public static final QualityType LAB_TECHNOLOGIST_SSIN;
   public static final QualityType LOGOPEDIST_NIHII;
   public static final QualityType LOGOPEDIST_SSIN;
   public static final QualityType MEDICAL_HOUSE_NIHII;
   public static final QualityType MIDWIFE_NIHII;
   public static final QualityType MIDWIFE_SSIN;
   public static final QualityType NURSE_NIHII;
   public static final QualityType NURSE_SSIN;
   public static final QualityType OCCUPATIONAL_THERAPIST_NIHII;
   public static final QualityType OCCUPATIONAL_THERAPIST_SSIN;
   public static final QualityType OFFICE_DENTISTS_NIHII;
   public static final QualityType OFFICE_DOCTORS_NIHII;
   public static final QualityType OF_BAND_NIHII;
   public static final QualityType OF_PHYSIOS_NIHII;
   public static final QualityType OPTICIEN_NIHII;
   public static final QualityType OPTICIEN_SSIN;
   public static final QualityType ORTHOPEDAGOGIST_MASTER_NIHII;
   public static final QualityType ORTHOPEDAGOGIST_MASTER_SSIN;
   public static final QualityType ORTHOPEDIST_NIHII;
   public static final QualityType ORTHOPEDIST_SSIN;
   public static final QualityType ORTHOPTIST_NIHII;
   public static final QualityType ORTHOPTIST_SSIN;
   public static final QualityType OTD_PHARMACY_NIHII;
   public static final QualityType PALLIATIVE_CARE_NIHII;
   public static final QualityType PEDIATRIC_NURSE_NIHII;
   public static final QualityType PEDIATRIC_NURSE_SSIN;
   public static final QualityType PHARMACIST_NIHII;
   public static final QualityType PHARMACIST_SSIN;
   public static final QualityType PHARMACIST_ASSISTANT_NIHII;
   public static final QualityType PHARMACIST_ASSISTANT_SSIN;
   public static final QualityType PHARMACY_NIHII;
   public static final QualityType PHYSIOTHERAPIST_NIHII;
   public static final QualityType PHYSIOTHERAPIST_SSIN;
   public static final QualityType PODOLOGIST_NIHII;
   public static final QualityType PODOLOGIST_SSIN;
   public static final QualityType PRACTICALNURSE_NIHII;
   public static final QualityType PRACTICALNURSE_SSIN;
   public static final QualityType PROT_ACC_NIHII;
   public static final QualityType PSYCHOLOGIST_NIHII;
   public static final QualityType PSYCHOLOGIST_SSIN;
   public static final QualityType PSYCHOMOTOR_THERAPY_NIHII;
   public static final QualityType PSYCHOMOTOR_THERAPY_SSIN;
   public static final QualityType PSYCH_HOUSE_NIHII;
   public static final QualityType READAPTATION_BACHELOR_NIHII;
   public static final QualityType READAPTATION_BACHELOR_SSIN;
   public static final QualityType RETIREMENT_NIHII;
   public static final QualityType SOCIAL_WORKER_NIHII;
   public static final QualityType SOCIAL_WORKER_SSIN;
   public static final QualityType SPECIALIZED_EDUCATOR_NIHII;
   public static final QualityType SPECIALIZED_EDUCATOR_SSIN;
   public static final QualityType TREATMENT_CENTER_CBE;
   public static final QualityType TRUSS_MAKER_NIHII;
   public static final QualityType TRUSS_MAKER_SSIN;
   /** @deprecated */
   @Deprecated
   public static final QualityType GENERAL_PRACTIONER_SSIN;
   /** @deprecated */
   @Deprecated
   public static final QualityType GENERAL_PRACTIONER_NIHII;
   /** @deprecated */
   @Deprecated
   public static final QualityType GROUPOFNURSES_NIHII;
   private String quality;
   private IdentifierType type;
   private static Map<String, QualityType> predefinedQual;

   public static QualityType getInstance(String quality, IdentifierType type) {
      Iterator i$ = predefinedQual.values().iterator();

      QualityType qual;
      do {
         if (!i$.hasNext()) {
            QualityType qual = new QualityType(quality, type);
            predefinedQual.put(quality + "_" + type.name(), qual);
            return qual;
         }

         qual = (QualityType)i$.next();
      } while(!qual.getQuality().equals(quality) || !qual.getIdentifierType().equals(type));

      return qual;
   }

   private QualityType(String quality, IdentifierType type) {
      this.quality = quality;
      this.type = type;
   }

   public String getQuality() {
      return this.quality;
   }

   public IdentifierType getIdentifierType() {
      return this.type;
   }

   public String name() {
      return (String)getKeyByValue(predefinedQual, this);
   }

   public static QualityType valueOf(String key) {
      return (QualityType)predefinedQual.get(key.toUpperCase().replaceAll("INSS", "SSIN"));
   }

   public static QualityType valueOf(String quality, String type) {
      return (QualityType)predefinedQual.get((quality + "_" + type).toUpperCase().replaceAll("INSS", "SSIN"));
   }

   private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
      Iterator i$ = map.entrySet().iterator();

      Entry entry;
      do {
         if (!i$.hasNext()) {
            return null;
         }

         entry = (Entry)i$.next();
      } while(!value.equals(entry.getValue()));

      return entry.getKey();
   }

   static {
      AMBULANCE_RESCUER_NIHII = new QualityType("AMBULANCE_RESCUER", IdentifierType.NIHII);
      AMBULANCE_RESCUER_SSIN = new QualityType("AMBULANCE_RESCUER", IdentifierType.SSIN);
      APPLIED_PSYCH_BACHELOR_NIHII = new QualityType("APPLIED_PSYCH_BACHELOR", IdentifierType.NIHII);
      APPLIED_PSYCH_BACHELOR_SSIN = new QualityType("APPLIED_PSYCH_BACHELOR", IdentifierType.SSIN);
      AUDICIEN_NIHII = new QualityType("AUDICIEN", IdentifierType.NIHII);
      AUDICIEN_SSIN = new QualityType("AUDICIEN", IdentifierType.SSIN);
      AUDIOLOGIST_NIHII = new QualityType("AUDIOLOGIST", IdentifierType.NIHII);
      AUDIOLOGIST_SSIN = new QualityType("AUDIOLOGIST", IdentifierType.SSIN);
      CITIZEN = new QualityType("CITIZEN", IdentifierType.SSIN);
      CONSORTIUM_CBE = new QualityType("CONSORTIUM", IdentifierType.CBE_CONSORTIUM);
      CTRL_ORGANISM_EHP = new QualityType("CTRL_ORGANISM", IdentifierType.EHP_CTRL_ORGANISM);
      DENTIST_NIHII = new QualityType("DENTIST", IdentifierType.NIHII);
      DENTIST_SSIN = new QualityType("DENTIST", IdentifierType.SSIN);
      DIETICIAN_NIHII = new QualityType("DIETICIAN", IdentifierType.NIHII);
      DIETICIAN_SSIN = new QualityType("DIETICIAN", IdentifierType.SSIN);
      DOCTOR_NIHII = new QualityType("DOCTOR", IdentifierType.NIHII);
      DOCTOR_SSIN = new QualityType("DOCTOR", IdentifierType.SSIN);
      FAMILY_SCIENCE_BACHELOR_NIHII = new QualityType("FAMILY_SCIENCE_BACHELOR", IdentifierType.NIHII);
      FAMILY_SCIENCE_BACHELOR_SSIN = new QualityType("FAMILY_SCIENCE_BACHELOR", IdentifierType.SSIN);
      GERONTOLOGY_MASTER_NIHII = new QualityType("GERONTOLOGY_MASTER", IdentifierType.NIHII);
      GERONTOLOGY_MASTER_SSIN = new QualityType("GERONTOLOGY_MASTER", IdentifierType.SSIN);
      GROUP_NIHII = new QualityType("GROUP", IdentifierType.NIHII_GROUPOFNURSES);
      GROUP_DOCTORS_NIHII = new QualityType("GROUP_DOCTORS", IdentifierType.NIHII_GROUP_DOCTORS);
      GUARD_POST_NIHII = new QualityType("GUARD_POST", IdentifierType.NIHII_GUARD_POST);
      HOME_SERVICES_NIHII = new QualityType("HOME_SERVICES", IdentifierType.NIHII_HOME_SERVICES);
      HOSPITAL_NIHII = new QualityType("HOSPITAL", IdentifierType.NIHII_HOSPITAL);
      IMAGING_TECHNOLOGIST_NIHII = new QualityType("IMAGING_TECHNOLOGIST", IdentifierType.NIHII);
      IMAGING_TECHNOLOGIST_SSIN = new QualityType("IMAGING_TECHNOLOGIST", IdentifierType.SSIN);
      IMPLANTPROVIDER_NIHII = new QualityType("IMPLANTPROVIDER", IdentifierType.NIHII);
      IMPLANTPROVIDER_SSIN = new QualityType("IMPLANTPROVIDER", IdentifierType.SSIN);
      INSTITUTION_CBE = new QualityType("INSTITUTION", IdentifierType.CBE);
      INSTITUTION_EHP_EHP = new QualityType("INSTITUTION_EHP", IdentifierType.EHP);
      LABO_NIHII = new QualityType("LABO", IdentifierType.NIHII_LABO);
      LAB_TECHNOLOGIST_NIHII = new QualityType("LAB_TECHNOLOGIST", IdentifierType.NIHII);
      LAB_TECHNOLOGIST_SSIN = new QualityType("LAB_TECHNOLOGIST", IdentifierType.SSIN);
      LOGOPEDIST_NIHII = new QualityType("LOGOPEDIST", IdentifierType.NIHII);
      LOGOPEDIST_SSIN = new QualityType("LOGOPEDIST", IdentifierType.SSIN);
      MEDICAL_HOUSE_NIHII = new QualityType("MEDICAL_HOUSE", IdentifierType.NIHII_MEDICAL_HOUSE);
      MIDWIFE_NIHII = new QualityType("MIDWIFE", IdentifierType.NIHII);
      MIDWIFE_SSIN = new QualityType("MIDWIFE", IdentifierType.SSIN);
      NURSE_NIHII = new QualityType("NURSE", IdentifierType.NIHII);
      NURSE_SSIN = new QualityType("NURSE", IdentifierType.SSIN);
      OCCUPATIONAL_THERAPIST_NIHII = new QualityType("OCCUPATIONAL_THERAPIST", IdentifierType.NIHII);
      OCCUPATIONAL_THERAPIST_SSIN = new QualityType("OCCUPATIONAL_THERAPIST", IdentifierType.SSIN);
      OFFICE_DENTISTS_NIHII = new QualityType("OFFICE_DENTISTS", IdentifierType.NIHII_OFFICE_DENTISTS);
      OFFICE_DOCTORS_NIHII = new QualityType("OFFICE_DOCTORS", IdentifierType.NIHII_OFFICE_DOCTORS);
      OF_BAND_NIHII = new QualityType("OF_BAND", IdentifierType.NIHII_OF_BAND);
      OF_PHYSIOS_NIHII = new QualityType("OF_PHYSIOS", IdentifierType.NIHII_OF_PHYSIOS);
      OPTICIEN_NIHII = new QualityType("OPTICIEN", IdentifierType.NIHII);
      OPTICIEN_SSIN = new QualityType("OPTICIEN", IdentifierType.SSIN);
      ORTHOPEDAGOGIST_MASTER_NIHII = new QualityType("ORTHOPEDAGOGIST_MASTER", IdentifierType.NIHII);
      ORTHOPEDAGOGIST_MASTER_SSIN = new QualityType("ORTHOPEDAGOGIST_MASTER", IdentifierType.SSIN);
      ORTHOPEDIST_NIHII = new QualityType("ORTHOPEDIST", IdentifierType.NIHII);
      ORTHOPEDIST_SSIN = new QualityType("ORTHOPEDIST", IdentifierType.SSIN);
      ORTHOPTIST_NIHII = new QualityType("ORTHOPTIST", IdentifierType.NIHII);
      ORTHOPTIST_SSIN = new QualityType("ORTHOPTIST", IdentifierType.SSIN);
      OTD_PHARMACY_NIHII = new QualityType("OTD_PHARMACY", IdentifierType.NIHII_OTD_PHARMACY);
      PALLIATIVE_CARE_NIHII = new QualityType("PALLIATIVE_CARE", IdentifierType.NIHII_PALLIATIVE_CARE);
      PEDIATRIC_NURSE_NIHII = new QualityType("PEDIATRIC_NURSE", IdentifierType.NIHII);
      PEDIATRIC_NURSE_SSIN = new QualityType("PEDIATRIC_NURSE", IdentifierType.SSIN);
      PHARMACIST_NIHII = new QualityType("PHARMACIST", IdentifierType.NIHII);
      PHARMACIST_SSIN = new QualityType("PHARMACIST", IdentifierType.SSIN);
      PHARMACIST_ASSISTANT_NIHII = new QualityType("PHARMACIST_ASSISTANT", IdentifierType.NIHII);
      PHARMACIST_ASSISTANT_SSIN = new QualityType("PHARMACIST_ASSISTANT", IdentifierType.SSIN);
      PHARMACY_NIHII = new QualityType("PHARMACY", IdentifierType.NIHII_PHARMACY);
      PHYSIOTHERAPIST_NIHII = new QualityType("PHYSIOTHERAPIST", IdentifierType.NIHII);
      PHYSIOTHERAPIST_SSIN = new QualityType("PHYSIOTHERAPIST", IdentifierType.SSIN);
      PODOLOGIST_NIHII = new QualityType("PODOLOGIST", IdentifierType.NIHII);
      PODOLOGIST_SSIN = new QualityType("PODOLOGIST", IdentifierType.SSIN);
      PRACTICALNURSE_NIHII = new QualityType("PRACTICALNURSE", IdentifierType.NIHII);
      PRACTICALNURSE_SSIN = new QualityType("PRACTICALNURSE", IdentifierType.SSIN);
      PROT_ACC_NIHII = new QualityType("PROT_ACC", IdentifierType.NIHII_PROT_ACC);
      PSYCHOLOGIST_NIHII = new QualityType("PSYCHOLOGIST", IdentifierType.NIHII);
      PSYCHOLOGIST_SSIN = new QualityType("PSYCHOLOGIST", IdentifierType.SSIN);
      PSYCHOMOTOR_THERAPY_NIHII = new QualityType("PSYCHOMOTOR_THERAPY", IdentifierType.NIHII);
      PSYCHOMOTOR_THERAPY_SSIN = new QualityType("PSYCHOMOTOR_THERAPY", IdentifierType.SSIN);
      PSYCH_HOUSE_NIHII = new QualityType("PSYCH_HOUSE", IdentifierType.NIHII_PSYCH_HOUSE);
      READAPTATION_BACHELOR_NIHII = new QualityType("READAPTATION_BACHELOR", IdentifierType.NIHII);
      READAPTATION_BACHELOR_SSIN = new QualityType("READAPTATION_BACHELOR", IdentifierType.SSIN);
      RETIREMENT_NIHII = new QualityType("RETIREMENT", IdentifierType.NIHII_RETIREMENT);
      SOCIAL_WORKER_NIHII = new QualityType("SOCIAL_WORKER", IdentifierType.NIHII);
      SOCIAL_WORKER_SSIN = new QualityType("SOCIAL_WORKER", IdentifierType.SSIN);
      SPECIALIZED_EDUCATOR_NIHII = new QualityType("SPECIALIZED_EDUCATOR", IdentifierType.NIHII);
      SPECIALIZED_EDUCATOR_SSIN = new QualityType("SPECIALIZED_EDUCATOR", IdentifierType.SSIN);
      TREATMENT_CENTER_CBE = new QualityType("TREATMENT_CENTER", IdentifierType.CBE_TREATCENTER);
      TRUSS_MAKER_NIHII = new QualityType("TRUSS_MAKER", IdentifierType.NIHII);
      TRUSS_MAKER_SSIN = new QualityType("TRUSS_MAKER", IdentifierType.SSIN);
      GENERAL_PRACTIONER_SSIN = DOCTOR_SSIN;
      GENERAL_PRACTIONER_NIHII = DOCTOR_NIHII;
      GROUPOFNURSES_NIHII = GROUP_NIHII;
      predefinedQual = new HashMap();
      Field[] fields = QualityType.class.getDeclaredFields();
      Field[] arr$ = fields;
      int len$ = fields.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Field f = arr$[i$];
         if (Modifier.isStatic(f.getModifiers()) && QualityType.class.isAssignableFrom(f.getType()) && !f.isAnnotationPresent(Deprecated.class)) {
            try {
               predefinedQual.put(f.getName(), (QualityType)f.get(AMBULANCE_RESCUER_NIHII));
            } catch (IllegalAccessException var6) {
               throw new IllegalArgumentException(var6);
            }
         }
      }

   }
}
