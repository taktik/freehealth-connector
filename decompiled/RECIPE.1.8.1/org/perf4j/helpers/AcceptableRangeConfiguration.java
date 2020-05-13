package org.perf4j.helpers;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcceptableRangeConfiguration implements Serializable, Cloneable {
   private String attributeName;
   private double minValue = Double.NEGATIVE_INFINITY;
   private double maxValue = Double.POSITIVE_INFINITY;
   protected static final Pattern CONFIG_STRING_PATTERN = Pattern.compile("(.+?)\\((<(.+?)|>(.+?)|(.+?)-(.+?))\\)");

   public AcceptableRangeConfiguration() {
   }

   public AcceptableRangeConfiguration(String configString) {
      Matcher matcher = CONFIG_STRING_PATTERN.matcher(configString);
      if (matcher.matches()) {
         this.attributeName = matcher.group(1).trim();

         try {
            String rangeString = matcher.group(2).trim();
            if (rangeString.startsWith("<")) {
               this.maxValue = Double.parseDouble(matcher.group(3).trim());
            } else if (rangeString.startsWith(">")) {
               this.minValue = Double.parseDouble(matcher.group(4).trim());
            } else {
               this.minValue = Double.parseDouble(matcher.group(5).trim());
               this.maxValue = Double.parseDouble(matcher.group(6).trim());
            }

         } catch (Exception var4) {
            throw new IllegalArgumentException("Invalid acceptable range config string: " + configString);
         }
      } else {
         throw new IllegalArgumentException("Invalid acceptable range config string: " + configString);
      }
   }

   public AcceptableRangeConfiguration(String attributeName, double minValue, double maxValue) {
      this.attributeName = attributeName;
      this.minValue = minValue;
      this.maxValue = maxValue;
   }

   public String getAttributeName() {
      return this.attributeName;
   }

   public void setAttributeName(String attributeName) {
      this.attributeName = attributeName;
   }

   public double getMinValue() {
      return this.minValue;
   }

   public void setMinValue(double minValue) {
      this.minValue = minValue;
   }

   public double getMaxValue() {
      return this.maxValue;
   }

   public void setMaxValue(double maxValue) {
      this.maxValue = maxValue;
   }

   public boolean isInRange(double value) {
      return value >= this.minValue && value <= this.maxValue;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof AcceptableRangeConfiguration)) {
         return false;
      } else {
         boolean var10000;
         label38: {
            AcceptableRangeConfiguration that = (AcceptableRangeConfiguration)o;
            if (Double.compare(that.maxValue, this.maxValue) == 0 && Double.compare(that.minValue, this.minValue) == 0) {
               if (this.attributeName == null) {
                  if (that.attributeName == null) {
                     break label38;
                  }
               } else if (this.attributeName.equals(that.attributeName)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      int result = this.attributeName != null ? this.attributeName.hashCode() : 0;
      long temp = this.minValue != 0.0D ? Double.doubleToLongBits(this.minValue) : 0L;
      result = 31 * result + (int)(temp ^ temp >>> 32);
      temp = this.maxValue != 0.0D ? Double.doubleToLongBits(this.maxValue) : 0L;
      result = 31 * result + (int)(temp ^ temp >>> 32);
      return result;
   }

   public String toString() {
      if (this.minValue == Double.NEGATIVE_INFINITY) {
         return this.attributeName + "(<" + this.maxValue + ")";
      } else {
         return this.maxValue == Double.POSITIVE_INFINITY ? this.attributeName + "(>" + this.minValue + ")" : this.attributeName + "(" + this.minValue + "-" + this.maxValue + ")";
      }
   }

   public AcceptableRangeConfiguration clone() {
      try {
         return (AcceptableRangeConfiguration)super.clone();
      } catch (Exception var2) {
         throw new Error("Unexpected CloneNotSupportedException");
      }
   }
}
