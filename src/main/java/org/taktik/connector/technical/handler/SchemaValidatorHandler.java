package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.utils.SOAPFaultFactory;
import org.taktik.connector.technical.validator.ValidatorHelper;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPFault;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class SchemaValidatorHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(SchemaValidatorHandler.class);
   private String[] schemaFiles;
   private int verify;
   public static final int VERIFY_INBOUND = 1;
   public static final int VERIFY_OUTBOUND = 2;
   public static final int VERIFY_BOTH = 3;

   public SchemaValidatorHandler(int verifyType, String... schemaFile) {
      validVerifyType(verifyType);
      Validate.notEmpty(schemaFile);
      Validate.noNullElements(schemaFile);
      this.verify = verifyType;
      this.schemaFiles = schemaFile;
   }

   public boolean handleInbound(SOAPMessageContext context) {
      if (this.verify == 3 || this.verify == 1) {
         LOG.info("Validating incoming message.");
         this.validate(context, "IN");
      }

      return true;
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      if (this.verify == 3 || this.verify == 2) {
         LOG.info("Validating outgoing message.");
         this.validate(context, "OUT");
      }

      return true;
   }

   private void validate(SOAPMessageContext context, String mode) {
      try {
         SOAPBody body = context.getMessage().getSOAPBody();
         SOAPFault fault = body.getFault();
         if (fault != null) {
            return;
         }

         Node payloadNode = body.getFirstChild();
         ValidatorHelper.validate(new DOMSource(payloadNode), this.isXOPEnabled(context), this.schemaFiles);
      } catch (Exception var6) {
         dumpMessage(context.getMessage(), mode, LOG);
         LOG.error(var6.getClass().getSimpleName() + ": " + var6.getMessage());
         throw SOAPFaultFactory.newSOAPFaultException(var6.getMessage(), var6);
      }

      LOG.info("Message validation done.");
   }

   private boolean isXOPEnabled(SOAPMessageContext context) {
      boolean xopEnabled = false;
      if (context.containsKey("http://www.w3.org/2004/08/soap/features/http-optimization")) {
         xopEnabled = ((Boolean)context.get("http://www.w3.org/2004/08/soap/features/http-optimization")).booleanValue();
      }

      return xopEnabled;
   }

   private static void validVerifyType(int verifyType) {
      if (verifyType < 0 || verifyType > 3) {
         throw new IllegalArgumentException("Verify of type " + verifyType + " is not supported.");
      }
   }
}
