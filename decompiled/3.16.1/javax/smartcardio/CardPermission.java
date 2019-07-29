package javax.smartcardio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Permission;

public class CardPermission extends Permission {
   private static final long serialVersionUID = 7146787880530705613L;
   private static final int A_CONNECT = 1;
   private static final int A_EXCLUSIVE = 2;
   private static final int A_GET_BASIC_CHANNEL = 4;
   private static final int A_OPEN_LOGICAL_CHANNEL = 8;
   private static final int A_RESET = 16;
   private static final int A_TRANSMIT_CONTROL = 32;
   private static final int A_ALL = 63;
   private static final int[] ARRAY_MASKS = new int[]{63, 1, 2, 4, 8, 16, 32};
   private static final String S_CONNECT = "connect";
   private static final String S_EXCLUSIVE = "exclusive";
   private static final String S_GET_BASIC_CHANNEL = "getBasicChannel";
   private static final String S_OPEN_LOGICAL_CHANNEL = "openLogicalChannel";
   private static final String S_RESET = "reset";
   private static final String S_TRANSMIT_CONTROL = "transmitControl";
   private static final String S_ALL = "*";
   private static final String[] ARRAY_STRINGS = new String[]{"*", "connect", "exclusive", "getBasicChannel", "openLogicalChannel", "reset", "transmitControl"};
   private transient int mask;
   private volatile String actions;

   public CardPermission(String terminalName, String actions) {
      super(terminalName);
      if (terminalName == null) {
         throw new NullPointerException();
      } else {
         this.mask = getMask(actions);
      }
   }

   private static int getMask(String actions) {
      if (actions != null && actions.length() != 0) {
         int mask;
         for(mask = 0; mask < ARRAY_STRINGS.length; ++mask) {
            if (actions == ARRAY_STRINGS[mask]) {
               return ARRAY_MASKS[mask];
            }
         }

         if (actions.endsWith(",")) {
            throw new IllegalArgumentException("Invalid actions: '" + actions + "'");
         } else {
            mask = 0;
            String[] split = actions.split(",");
            String[] arr$ = split;
            int len$ = split.length;

            label38:
            for(int i$ = 0; i$ < len$; ++i$) {
               String s = arr$[i$];

               for(int i = 0; i < ARRAY_STRINGS.length; ++i) {
                  if (ARRAY_STRINGS[i].equalsIgnoreCase(s)) {
                     mask |= ARRAY_MASKS[i];
                     continue label38;
                  }
               }

               throw new IllegalArgumentException("Invalid action: '" + s + "'");
            }

            return mask;
         }
      } else {
         throw new IllegalArgumentException("actions must not be empty");
      }
   }

   private static String getActions(int mask) {
      if (mask == 63) {
         return "*";
      } else {
         boolean first = true;
         StringBuilder sb = new StringBuilder();

         for(int i = 0; i < ARRAY_MASKS.length; ++i) {
            int action = ARRAY_MASKS[i];
            if ((mask & action) == action) {
               if (first) {
                  sb.append(",");
               } else {
                  first = false;
               }

               sb.append(ARRAY_STRINGS[i]);
            }
         }

         return sb.toString();
      }
   }

   public String getActions() {
      if (this.actions == null) {
         this.actions = getActions(this.mask);
      }

      return this.actions;
   }

   public boolean implies(Permission permission) {
      if (!(permission instanceof CardPermission)) {
         return false;
      } else {
         CardPermission other = (CardPermission)permission;
         if ((this.mask & other.mask) != other.mask) {
            return false;
         } else {
            String thisName = this.getName();
            if (thisName.equals("*")) {
               return true;
            } else {
               return thisName.equals(other.getName());
            }
         }
      }
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (!(obj instanceof CardPermission)) {
         return false;
      } else {
         CardPermission other = (CardPermission)obj;
         return this.getName().equals(other.getName()) && this.mask == other.mask;
      }
   }

   public int hashCode() {
      return this.getName().hashCode() + 31 * this.mask;
   }

   private void writeObject(ObjectOutputStream s) throws IOException {
      if (this.actions == null) {
         this.getActions();
      }

      s.defaultWriteObject();
   }

   private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      this.mask = getMask(this.actions);
   }
}
