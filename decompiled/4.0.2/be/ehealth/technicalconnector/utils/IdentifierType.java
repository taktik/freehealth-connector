package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IdentifierType implements Serializable {
   public static final IdentifierType CBE = new IdentifierType("CBE", 10);
   public static final IdentifierType CBE_TREATCENTER = new IdentifierType("CBE-TREAT_CENTER", "CBE-TREATMENT_CENTER", 10);
   public static final IdentifierType SSIN = new IdentifierType("SSIN", "INSS", 11);
   public static final IdentifierType NIHII = new IdentifierType("NIHII", 8);
   public static final IdentifierType NIHII11 = new IdentifierType("NIHII11", 11);
   public static final IdentifierType NIHII_PHARMACY = new IdentifierType("NIHII-PHARMACY", "NIHII-PHARMACY", 8);
   public static final IdentifierType NIHII_LABO = new IdentifierType("NIHII-LABO", 8);
   public static final IdentifierType NIHII_RETIREMENT = new IdentifierType("NIHII-RETIREMENT", 8);
   public static final IdentifierType NIHII_OTD_PHARMACY = new IdentifierType("NIHII-OTD_PHARMACY", 8);
   public static final IdentifierType NIHII_HOSPITAL = new IdentifierType("NIHII-HOSPITAL", 8);
   public static final IdentifierType NIHII_GROUPOFNURSES = new IdentifierType("NIHII-GROUP", 8);
   public static final IdentifierType EHP = new IdentifierType("EHP", 10);
   public static final IdentifierType NIHII_PALLIATIVE_CARE = new IdentifierType("NIHII-PALLIATIVE_CARE", 8);
   public static final IdentifierType NIHII_OFFICE_DENTISTS = new IdentifierType("NIHII-OFFICE_DENTISTS", 8);
   public static final IdentifierType NIHII_MEDICAL_HOUSE = new IdentifierType("NIHII-MEDICAL_HOUSE", 8);
   public static final IdentifierType NIHII_OFFICE_DOCTORS = new IdentifierType("NIHII-OFFICE_DOCTORS", 8);
   public static final IdentifierType NIHII_GROUP_DOCTORS = new IdentifierType("NIHII-GROUP_DOCTORS", 8);
   public static final IdentifierType NIHII_OF_BAND = new IdentifierType("NIHII-OF_BAND", 8);
   public static final IdentifierType NIHII_PSYCH_HOUSE = new IdentifierType("NIHII-PSYCH_HOUSE", 8);
   public static final IdentifierType NIHII_PROT_ACC = new IdentifierType("NIHII-PROT_ACC", 8);
   public static final IdentifierType NIHII_HOME_SERVICES = new IdentifierType("NIHII-HOME_SERVICES", 8);
   public static final IdentifierType NIHII_OF_PHYSIOS = new IdentifierType("NIHII-OF_PHYSIOS", 8);
   public static final IdentifierType SITE = new IdentifierType("SITE", 4);
   public static final IdentifierType SITESMUR = new IdentifierType("SITESMUR", 10);
   public static final IdentifierType SITEPIT = new IdentifierType("SITEPIT", 10);
   public static final IdentifierType CBE_CONSORTIUM = new IdentifierType("CBE-CONSORTIUM", 10);
   public static final IdentifierType NIHII_GUARD_POST = new IdentifierType("NIHII-GUARD_POST", 8);
   public static final IdentifierType EHP_CTRL_ORGANISM = new IdentifierType("EHP-CTRL_ORGANISM", 10);
   public static final IdentifierType NIHII_BELRAI = new IdentifierType("NIHII-BELRAI", 8);
   public static final IdentifierType NIHII_BELRAI_SCREEN = new IdentifierType("NIHII-BELRAI_SCREEN", 8);
   public static final IdentifierType NIHII_GROUP_MIDWIVES = new IdentifierType("NIHII-GROUP_MIDWIVES", 8);
   public static final IdentifierType NIHII_AMBU_SERVICE = new IdentifierType("NIHII-AMBU_SERVICE", 8);
   public static final IdentifierType NIHII_LEGAL_PSY = new IdentifierType("NIHII-LEGAL_PSY", 8);
   public static final IdentifierType NIHII_REEDUCATION = new IdentifierType("NIHII-REEDUCATION", 8);
   public static final IdentifierType NIHII_SORTING_CENTER = new IdentifierType("NIHII-SORTING_CENTER", 8);
   /** @deprecated */
   @Deprecated
   public static final IdentifierType HUB;
   public static final int ETKDEPOT = 48;
   public static final int EHBOX = 49;
   private static final long serialVersionUID = 1L;
   private static final Logger LOG;
   private static final int EXPECTED_SIZE_MAPPING = 3;
   private static Map<String, IdentifierType> predefinedTypes;
   private String typeEtk;
   private String typeEhbox;
   private String typeRecipe;
   private int length;

   private IdentifierType(String type, int length) {
      this.typeEtk = type;
      this.typeEhbox = type;
      this.typeRecipe = type;
      this.length = length;
   }

   private IdentifierType(String typeEtk, String typeEhbox, int length) {
      this.typeEtk = typeEtk;
      this.typeEhbox = typeEhbox;
      this.typeRecipe = this.typeRecipe;
      this.length = length;
   }

   public static IdentifierType getInstance(Map<Integer, String> mapping, int expectedLength) throws TechnicalConnectorException {
      if (mapping == null) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
         LOG.debug("\t## {}", MessageFormat.format(errorValue.getMessage(), "mapping is empty"));
         throw new TechnicalConnectorException(errorValue, new Object[]{"mapping is empty"});
      } else {
         String typeETK = (String)mapping.get(48);
         String typeEhbox = (String)mapping.get(49);
         int actualMapSize = mapping.size();
         validateMapping(typeETK, typeEhbox, actualMapSize);
         return getIdentifierType(typeETK, typeEhbox, expectedLength);
      }
   }

   private static IdentifierType getIdentifierType(String typeETK, String typeEhbox, int expectedLength) throws TechnicalConnectorException {
      Map<Integer, IdentifierType> identifierTypes = lookup(typeETK, typeEhbox);
      if (identifierTypes.isEmpty()) {
         LOG.info("Adding identifier with null values.");
         IdentifierType identifier = new IdentifierType(typeETK, typeEhbox, expectedLength);
         predefinedTypes.put(typeETK.replace("-", "_"), identifier);
         return identifier;
      } else if (identifierTypes.containsKey(49) && identifierTypes.containsKey(48)) {
         if (((IdentifierType)identifierTypes.get(48)).equals(identifierTypes.get(49))) {
            return (IdentifierType)identifierTypes.get(48);
         } else {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_MAPPING, new Object[]{"Multiple IdentifierTypes matches the mapping."});
         }
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_MAPPING, new Object[]{"Required Fields are empty."});
      }
   }

   private static Map<Integer, IdentifierType> lookup(String typeETK, String typeEhbox) {
      Map<Integer, IdentifierType> result = new HashMap();
      Iterator var3 = predefinedTypes.values().iterator();

      while(var3.hasNext()) {
         IdentifierType identifier = (IdentifierType)var3.next();
         if (identifier.getTypeEtk() != null && identifier.getTypeEtk().equals(typeETK)) {
            result.put(48, identifier);
         }

         if (identifier.getTypeEhbox() != null && identifier.getTypeEhbox().equals(typeEhbox)) {
            result.put(49, identifier);
         }
      }

      return result;
   }

   private static void validateMapping(String typeETK, String typeEhbox, int actualMapSize) throws TechnicalConnectorException {
      TechnicalConnectorExceptionValues errorValue;
      if (typeETK != null && typeEhbox != null && actualMapSize <= 3) {
         if (StringUtils.countMatches(typeETK, "-") > 1 || StringUtils.countMatches(typeEhbox, "-") > 1) {
            errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
            LOG.debug("\t## {}", MessageFormat.format(errorValue.getMessage(), "maximum one '-' is allowed."));
            throw new TechnicalConnectorException(errorValue, new Object[]{"maximum one '-' is allowed."});
         }
      } else {
         errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
         LOG.debug("\t## {}", MessageFormat.format(errorValue.getMessage(), "mapping doesn't contains key for ETKDEPOT or EHBOX."));
         throw new TechnicalConnectorException(errorValue, new Object[]{"mapping doesn't contains key for ETKDEPOT or EHBOX or RECIPE."});
      }
   }

   public static IdentifierType lookup(String type, String subType, int source) {
      IdentifierType returnValue = null;
      Iterator var4 = predefinedTypes.values().iterator();

      while(true) {
         while(true) {
            IdentifierType identifierType;
            String idenType;
            String idenSubType;
            do {
               do {
                  if (!var4.hasNext()) {
                     return returnValue;
                  }

                  identifierType = (IdentifierType)var4.next();
                  idenType = identifierType.getType(source);
                  idenSubType = identifierType.getSubType(source);
               } while(idenType == null);
            } while(!idenType.equalsIgnoreCase(type));

            if (idenSubType != null && subType != null) {
               if (idenSubType.equalsIgnoreCase(subType)) {
                  returnValue = identifierType;
               }
            } else if (subType == null && idenSubType == null) {
               returnValue = identifierType;
            }
         }
      }
   }

   public static IdentifierType valueOf(String key) {
      return (IdentifierType)predefinedTypes.get(key);
   }

   private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
      Iterator var2 = map.entrySet().iterator();

      Map.Entry entry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entry = (Map.Entry)var2.next();
      } while(!value.equals(entry.getValue()));

      return entry.getKey();
   }

   public String formatIdentifierValue(long identifierValue) {
      String value = Long.toString(identifierValue);
      return this.formatIdentifierValue(value);
   }

   public String formatIdentifierValue(String value) {
      if (value.length() > this.length) {
         LOG.debug("Truncating identifiervalue [{}] to length {}", value, this.length);
         return value.substring(0, this.length);
      } else {
         return StringUtils.leftPad(value, this.length, "0");
      }
   }

   public String getType(int source) {
      String returnValue = null;
      switch (source) {
         case 48:
            returnValue = this.typeEtk;
            break;
         case 49:
            if (this.typeEhbox != null) {
               returnValue = this.typeEhbox.split("-")[0];
            }
      }

      return returnValue;
   }

   public String getSubType(int source) {
      String returnValue = null;
      if (source == 49 && this.typeEhbox != null) {
         String[] ehboxV2 = this.typeEhbox.split("-");
         if (ehboxV2.length == 2) {
            returnValue = this.typeEhbox.split("-")[1];
         }
      }

      return returnValue;
   }

   private String getTypeEhbox() {
      return this.typeEhbox;
   }

   private String getTypeEtk() {
      return this.typeEtk;
   }

   private String getTypeRecipe() {
      return this.typeRecipe;
   }

   public String name() {
      return (String)getKeyByValue(predefinedTypes, this);
   }

   static {
      HUB = EHP;
      LOG = LoggerFactory.getLogger(IdentifierType.class);
      predefinedTypes = new HashMap();
      Field[] fields = IdentifierType.class.getDeclaredFields();
      Field[] var1 = fields;
      int var2 = fields.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Field f = var1[var3];
         if (Modifier.isStatic(f.getModifiers()) && IdentifierType.class.isAssignableFrom(f.getType())) {
            try {
               predefinedTypes.put(f.getName(), (IdentifierType)f.get(CBE));
            } catch (IllegalAccessException var6) {
               throw new IllegalArgumentException(var6);
            }
         }
      }

   }
}
