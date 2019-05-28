package sun.security.smartcardio;

final class PCSCException extends Exception {
   private static final long serialVersionUID = 4181137171979130432L;
   final int code;

   PCSCException(int code) {
      super(toErrorString(code));
      this.code = code;
   }

   private static String toErrorString(int code) {
      switch(code) {
      case -2146435071:
         return "SCARD_F_INTERNAL_ERROR";
      case -2146435070:
         return "SCARD_E_CANCELLED";
      case -2146435069:
         return "SCARD_E_INVALID_HANDLE";
      case -2146435068:
         return "SCARD_E_INVALID_PARAMETER";
      case -2146435067:
         return "SCARD_E_INVALID_TARGET";
      case -2146435066:
         return "SCARD_E_NO_MEMORY";
      case -2146435065:
         return "SCARD_F_WAITED_TOO_LONG";
      case -2146435064:
         return "SCARD_E_INSUFFICIENT_BUFFER";
      case -2146435063:
         return "SCARD_E_UNKNOWN_READER";
      case -2146435062:
         return "SCARD_E_TIMEOUT";
      case -2146435061:
         return "SCARD_E_SHARING_VIOLATION";
      case -2146435060:
         return "SCARD_E_NO_SMARTCARD";
      case -2146435059:
         return "SCARD_E_UNKNOWN_CARD";
      case -2146435058:
         return "SCARD_E_CANT_DISPOSE";
      case -2146435057:
         return "SCARD_E_PROTO_MISMATCH";
      case -2146435056:
         return "SCARD_E_NOT_READY";
      case -2146435055:
         return "SCARD_E_INVALID_VALUE";
      case -2146435054:
         return "SCARD_E_SYSTEM_CANCELLED";
      case -2146435053:
         return "SCARD_F_COMM_ERROR";
      case -2146435052:
         return "SCARD_F_UNKNOWN_ERROR";
      case -2146435051:
         return "SCARD_E_INVALID_ATR";
      case -2146435050:
         return "SCARD_E_NOT_TRANSACTED";
      case -2146435049:
         return "SCARD_E_READER_UNAVAILABLE";
      case -2146435047:
         return "SCARD_E_PCI_TOO_SMALL";
      case -2146435046:
         return "SCARD_E_READER_UNSUPPORTED";
      case -2146435045:
         return "SCARD_E_DUPLICATE_READER";
      case -2146435044:
         return "SCARD_E_CARD_UNSUPPORTED";
      case -2146435043:
         return "SCARD_E_NO_SERVICE";
      case -2146435042:
         return "SCARD_E_SERVICE_STOPPED";
      case -2146435041:
         return "SCARD_E_UNSUPPORTED_FEATURE";
      case -2146435026:
         return "SCARD_E_NO_READERS_AVAILABLE";
      case -2146434971:
         return "SCARD_W_UNSUPPORTED_CARD";
      case -2146434970:
         return "SCARD_W_UNRESPONSIVE_CARD";
      case -2146434969:
         return "SCARD_W_UNPOWERED_CARD";
      case -2146434968:
         return "SCARD_W_RESET_CARD";
      case -2146434967:
         return "SCARD_W_REMOVED_CARD";
      case -2146434966:
         return "SCARD_W_INSERTED_CARD";
      case 0:
         return "SCARD_S_SUCCESS";
      case 6:
         return "WINDOWS_ERROR_INVALID_HANDLE";
      case 87:
         return "WINDOWS_ERROR_INVALID_PARAMETER";
      default:
         return "Unknown error 0x" + Integer.toHexString(code);
      }
   }
}
