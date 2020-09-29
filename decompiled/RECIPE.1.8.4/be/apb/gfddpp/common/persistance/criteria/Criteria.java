package be.apb.gfddpp.common.persistance.criteria;

import java.lang.reflect.Field;

public class Criteria<T> {
   private Field field;
   private T value;

   public Criteria(Field field, T value) {
      this.field = field;
      this.value = value;
   }

   public Field getField() {
      return this.field;
   }

   public void setField(Field field) {
      this.field = field;
   }

   public T getValue() {
      return this.value;
   }

   public void setValue(T value) {
      this.value = value;
   }

   public boolean isDate() {
      return !this.isEmpty() && this.value instanceof DateRange;
   }

   public boolean isString() {
      return !this.isEmpty() && this.field.getType().isAssignableFrom(String.class);
   }

   public boolean isEnum() {
      return !this.isEmpty() && this.field.getType().isEnum();
   }

   public String getName() {
      return this.field != null ? this.field.getName() : null;
   }

   public boolean isEmpty() {
      if (this.value == null) {
         return true;
      } else {
         return this.value instanceof String ? "".equals(this.value) : false;
      }
   }
}
