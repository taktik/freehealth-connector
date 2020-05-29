package org.taktik.connector.technical.utils;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class IdentifierType implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final IdentifierType CBE = new IdentifierType("CBE", 10);
   public static final IdentifierType CBE_TREATCENTER = new IdentifierType("CBE-TREAT_CENTER", "CBE-TREATMENT_CENTER", "CBE-TREAT_CENTER", 10);
   public static final IdentifierType SSIN = new IdentifierType("SSIN", "INSS", "SSIN", 11);
   public static final IdentifierType NIHII = new IdentifierType("NIHII", 8);
   public static final IdentifierType NIHII11 = new IdentifierType("NIHII11", 11);
   public static final IdentifierType NIHII_PHARMACY = new IdentifierType("NIHII-PHARMACY", "NIHII-PHARMACY", "NIHII-PHARMACY", 8);
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
   public static final IdentifierType NIHII_SORTING_CENTER = new IdentifierType("NIHII-SORTING_CENTER", "NIHII-SORTING_CENTER", "SORTING_CENTER" , 8);
   public static final IdentifierType UNKNOWN = new IdentifierType("UNKNOWN", "UNKNOWN", "UNKNOWN" , 8);
   /** @deprecated */
   @Deprecated
   public static final IdentifierType HUB;
   public static final int ETKDEPOT = 48;
   public static final int EHBOX = 49;
   /** @deprecated */
   @Deprecated
   public static final int EHBOXV2 = EHBOX;
   public static final int RECIPE = 50;
   private static final Logger LOG;
   private static final int EXPECTED_SIZE_MAPPING = 3;
   private String typeEtk;
   private String typeEhbox;
   private String typeRecipe;
   private int length;
   private static Map<String, IdentifierType> predefinedTypes;

   private IdentifierType(String type, int length) {
      this.typeEtk = type;
      this.typeEhbox = type;
      this.typeRecipe = type;
      this.length = length;
   }

   private IdentifierType(String typeEtk, String typeEhboxv2, String typeRecipe, int length) {
      this.typeEtk = typeEtk;
      this.typeEhbox = typeEhboxv2;
      this.typeRecipe = typeRecipe;
      this.length = length;
   }

   public static IdentifierType getInstance(Map<Integer, String> mapping, int expectedLength) throws TechnicalConnectorException {
      if (mapping == null) {
         TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
         LOG.debug("\t## " + MessageFormat.format(errorValue.getMessage(), "mapping is empty"));
         throw new TechnicalConnectorException(errorValue, "mapping is empty");
      } else {
         String typeETK = mapping.get(ETKDEPOT);
         String typeEhbox = mapping.get(EHBOX);
         String typeRecipe = mapping.get(RECIPE);
         int actualMapSize = mapping.size();
         validateMapping(typeETK, typeEhbox, typeRecipe, actualMapSize);
         return getIdentifierType(typeETK, typeEhbox, typeRecipe, expectedLength);
      }
   }

   private static IdentifierType getIdentifierType(String typeETK, String typeEhbox, String typeRecipe, int expectedLength) throws TechnicalConnectorException {
      Map<Integer, IdentifierType> identifierTypes = lookup(typeETK, typeEhbox, typeRecipe);
      if (identifierTypes.isEmpty()) {
         LOG.info("Adding identifier with null values.");
         IdentifierType identifier = new IdentifierType(typeETK, typeEhbox, typeRecipe, expectedLength);
         predefinedTypes.put(typeETK.replace("-", "_"), identifier);
         return identifier;
      } else if (identifierTypes.containsKey(EHBOX) && identifierTypes.containsKey(ETKDEPOT)) {
         if (!identifierTypes.get(ETKDEPOT).equals(identifierTypes.get(EHBOX)) || identifierTypes.containsKey(RECIPE) && !identifierTypes.get(ETKDEPOT).equals(identifierTypes.get(RECIPE))) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_MAPPING, "Multiple IdentifierTypes matches the mapping.");
         } else {
            return identifierTypes.get(ETKDEPOT);
         }
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.INVALID_MAPPING, "Required Fields are empty.");
      }
   }

   private static Map<Integer, IdentifierType> lookup(String typeETK, String typeEhbox, String typeRecipe) {
      Map<Integer, IdentifierType> result = new HashMap();
      Iterator i$ = predefinedTypes.values().iterator();

      while(i$.hasNext()) {
         IdentifierType identifier = (IdentifierType)i$.next();
         if (identifier.getTypeEtk() != null && identifier.getTypeEtk().equals(typeETK)) {
            result.put(ETKDEPOT, identifier);
         }

         if (identifier.getTypeEhbox() != null && identifier.getTypeEhbox().equals(typeEhbox)) {
            result.put(EHBOX, identifier);
         }

         if (identifier.getTypeRecipe() != null && identifier.getTypeRecipe().equals(typeRecipe)) {
            result.put(RECIPE, identifier);
         }
      }

      return result;
   }

   private static void validateMapping(String typeETK, String typeEhbox, String typeRecipe, int actualMapSize) throws TechnicalConnectorException {
      TechnicalConnectorExceptionValues errorValue;
      if (typeETK != null && typeEhbox != null && actualMapSize <= EXPECTED_SIZE_MAPPING) {
         if (StringUtils.countMatches(typeETK, "-") > 1 || StringUtils.countMatches(typeEhbox, "-") > 1 || StringUtils.countMatches(typeRecipe, "-") > 1) {
            errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
            LOG.debug("\t## " + MessageFormat.format(errorValue.getMessage(), "maximum one '-' is allowed."));
            throw new TechnicalConnectorException(errorValue, "maximum one '-' is allowed.");
         }
      } else {
         errorValue = TechnicalConnectorExceptionValues.INVALID_MAPPING;
         LOG.debug("\t## " + MessageFormat.format(errorValue.getMessage(), "mapping doesn't contains key for ETKDEPOT or EHBOX."));
         throw new TechnicalConnectorException(errorValue, "mapping doesn't contains key for ETKDEPOT or EHBOX or RECIPE.");
      }
   }

   public static IdentifierType lookup(String type, String subType, int source) {
      IdentifierType returnValue = UNKNOWN;
      Iterator i$ = predefinedTypes.values().iterator();

      while(true) {
         IdentifierType identifierType;
         String idenType;
         String idenSubType;
         do {
            do {
               if (!i$.hasNext()) {
                  if (returnValue == UNKNOWN) {
                     LOG.warn("Could not lookup {}, {}, {}",type,subType,source);
                  }
                  return returnValue;
               }

               identifierType = (IdentifierType)i$.next();
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

   public String formatIdentifierValue(long identifierValue) {
      String value = Long.toString(identifierValue);
      return this.formatIdentifierValue(value);
   }

   public String formatIdentifierValue(String value) {
      if (value.length() > this.length) {
         LOG.debug("Truncating identifiervalue [" + value + "] to length " + this.length);
         return value.substring(0, this.length);
      } else {
         return StringUtils.leftPad(value, this.length, "0");
      }
   }

   public String getType(int source) {
      String returnValue = null;
      switch(source) {
      case ETKDEPOT:
         returnValue = this.typeEtk;
         break;
      case EHBOX:
         if (this.typeEhbox != null) {
            returnValue = this.typeEhbox.split("-")[0];
         }
         break;
      case RECIPE:
         returnValue = this.typeRecipe;
      }

      return returnValue;
   }

   public String getSubType(int source) {
      String returnValue = null;
      switch(source) {
      case EHBOX:
         if (this.typeEhbox != null) {
            String[] ehboxV2 = this.typeEhbox.split("-");
            if (ehboxV2.length == 2) {
               returnValue = this.typeEhbox.split("-")[1];
            }
         }
      default:
         return returnValue;
      }
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
      return getKeyByValue(predefinedTypes, this);
   }

   public static IdentifierType valueOf(String key) {
      return predefinedTypes.get(key);
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

      return (T) entry.getKey();
   }

   static {
      HUB = EHP;
      LOG = LoggerFactory.getLogger(IdentifierType.class);
      predefinedTypes = new HashMap();
      Field[] fields = IdentifierType.class.getDeclaredFields();
      Field[] arr$ = fields;
      int len$ = fields.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Field f = arr$[i$];
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
