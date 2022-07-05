package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class LocalizedText implements Serializable {
   private static final long serialVersionUID = 1L;
   private List<LocalizedString> values = new ArrayList();

   public LocalizedText() {
   }

   public List<LocalizedString> getValues() {
      return this.values;
   }

   public void setValues(List<LocalizedString> values) {
      this.values = values;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         LocalizedText that = (LocalizedText)o;
         return (new EqualsBuilder()).append(this.getValues(), that.getValues()).isEquals();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return (new HashCodeBuilder(17, 37)).append(this.getValues()).toHashCode();
   }
}
