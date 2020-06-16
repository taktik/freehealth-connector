package be.business.connector.recipe.prescriber.mock;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.recipe.prescriber.PrescriberIntegrationModuleDevV4Impl;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

public class PrescriberIntegrationModuleMock extends PrescriberIntegrationModuleDevV4Impl {
   private static final Logger LOG = Logger.getLogger(PrescriberIntegrationModuleMock.class);

   public PrescriberIntegrationModuleMock() throws IntegrationModuleException {
   }

   private String compressedB64toString(String s) {
      byte[] bytes = Base64.decode(s.getBytes());

      try {
         bytes = IOUtils.decompress(bytes);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

      return new String(bytes);
   }

   protected void init() throws IntegrationModuleException {
   }

   public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException {
      LOG.info("createPrescription : " + prescriptionType);
      return "BEPP" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
   }

   public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
      LOG.info("getPrescription : " + rid);
      GetPrescriptionForPrescriberResult p = new GetPrescriptionForPrescriberResult();
      p.setRid(rid);
      p.setPatientId("84071230581");
      p.setCreationDate(GregorianCalendar.getInstance());
      if (this.getPropertyHandler().hasProperty("MOCK_PRESC")) {
         try {
            LOG.info("Loading prescription from " + this.getPropertyHandler().getProperty("MOCK_PRESC"));
            p.setPrescription(IOUtils.getBytes(IOUtils.getResourceAsStream(this.getPropertyHandler().getProperty("MOCK_PRESC"))));
         } catch (IOException var4) {
            LOG.error("IOException", var4);
            p.setPrescription(this.compressedB64toString("H4sIAAAAAAAAAO1a33OiSBB+vvwVU9zL3QMC/lgxRdyy1CTUGpNSc7V5HIdR5xYGFkZj/vttMIIGMcAmt5dNfImM3d9093R/PTPE+Lx2bLSifsBcfiZpFVVClBPXYnx+Jt1OzmVdQoHA3MK2y+mZxF0JfW6fnBg80E6/OXThOzQI8JwiAOLBKQyfSQshvFNFub+/r9AFxbZYVGZzd1WZUmWD5VuBEikrAVlQBysrTXoEgNn3AO5rFdefK1VV1RS1roAQSPy5lV4HLFP669VgHKHLjIfTEiq1T/5Ajx8DDLOo3z5Bex9ja+DTH3ZEiIXGZ1K3J48nnWGvM+pJaPxPGDxNaldVTVU/qZqhkBSEoWSBGyyCNHvyl6v+5WiLp0ptrf6p2VA1AK1XwKtW9LWlbj6GwrKgBtfdzgBwBhHqqN81b/o7sDrVSJ3iulzTqw25rtO6PCWkOtUJtma4ifVW8xA4wFtY0MhLWdXl0M9oIC0nmEPbautUVU9DQ6PHtFRA+YFF2BFYEA/74iFbIh3Cy+5NZzS5OxzEw16lwOIlToN5UCze4iFghGF+aJkPwHEM3vf8CuotyTfUczm2LUOJRrNdVzJ9h0TKiJvhU8I8Rrl47ZhuUurnwwnl6i2nNiMboigS0FHoq0x/KpAH4gXyT6jBmLn24XBn121G+XhYPLM6MeRNZ2L2h5Pdqm22Wmqr3tK02vHAGzPmByIKy7lPIdWS5yM62GH2QySEzm3GRSCA8kE3Gc9WnjJfLA5TQUo2EtNatZoMLFKtZVFILK/kAAcmWRdJxHH/605gHWzT45kXltyBCQwlc0EN4WMeYCKgs+ZZ7pwZlHZlMuoMx53uxLwe7jQib4F9BxO6FIxg2/NpQHzmhcY842hOgk/czEP0sTReioV7hPBjwdwkFWu8ZAOIQV+2EcSwZRpCrJzNZ4nIc3E2WEBcx7MprK3wl5D9OwPHtFbYZmFGWLFaMpKtR9ce83GYfdv0qm3T68lPR9oWsDJsS4u0rYI1FQMkq97v9MzhxW5d5a6kxBxBnTJ5XNL6tBfmpH+144JDLeCE/A4kgC4XR5tXpmY0JeMhEbnWkuSCMFg4m0Wt2I/e6PZC7g6/bHyphjFRa5re1MLNaixcCDsquQ6cbrBA174boJqK/iJ/wx9njhLUfJWZmkUp4/dWt3C0DZvNKHkgdiFTkzQZmOf97l13sHtQeEz3abiJKJQrSgljjO9LzAUrwvqxqgUbOWjjYZFsvxYwtvjExsyn35dwFi1jLLQO5oaZUcbVnRW76Y/M657ZNfcaU6/YUsWwSimzYINYPBKwy3jYzFbCzoQPOnebCOw4T1eUQ5comq0l7DE8N3Btd14mAQRdCwRnc24Dra9h1wTPRczNPzVwWK7282JdqvoGu9Tr9SdN1Ru63ijan1JzbPoUgTOFB5G/vEI3lM+YbUfNCva15q3i2EWA30Nr+2huqGRzK0Vte6xWXSsW0qp9tHJdH1kUORjbgv0L69H9rfjumauYFND/ge8ivVfkvGazqellOe8p65k8WML2HPtZvFcM+30Q3wfz/ZfMt1Hd39QB/TUf2W9BBQps7FH+WzFf6bu0X8p8pXgvL/E1mq1Gqzzx7c0U8dPYY77LXRuT8DIe9SsXlS31OfPiR8dklndCgx88+OauNyKIjyuO7CsOx/Xf3BWHYv3K1rd5pXvw5QGYdeg9HSz54ztfQ3n6ry7tH4z7rjM2IwAA").getBytes());
         }
      } else {
         p.setPrescription(this.compressedB64toString("H4sIAAAAAAAAAO1a33OiSBB+vvwVU9zL3QMC/lgxRdyy1CTUGpNSc7V5HIdR5xYGFkZj/vttMIIGMcAmt5dNfImM3d9093R/PTPE+Lx2bLSifsBcfiZpFVVClBPXYnx+Jt1OzmVdQoHA3MK2y+mZxF0JfW6fnBg80E6/OXThOzQI8JwiAOLBKQyfSQshvFNFub+/r9AFxbZYVGZzd1WZUmWD5VuBEikrAVlQBysrTXoEgNn3AO5rFdefK1VV1RS1roAQSPy5lV4HLFP669VgHKHLjIfTEiq1T/5Ajx8DDLOo3z5Bex9ja+DTH3ZEiIXGZ1K3J48nnWGvM+pJaPxPGDxNaldVTVU/qZqhkBSEoWSBGyyCNHvyl6v+5WiLp0ptrf6p2VA1AK1XwKtW9LWlbj6GwrKgBtfdzgBwBhHqqN81b/o7sDrVSJ3iulzTqw25rtO6PCWkOtUJtma4ifVW8xA4wFtY0MhLWdXl0M9oIC0nmEPbautUVU9DQ6PHtFRA+YFF2BFYEA/74iFbIh3Cy+5NZzS5OxzEw16lwOIlToN5UCze4iFghGF+aJkPwHEM3vf8CuotyTfUczm2LUOJRrNdVzJ9h0TKiJvhU8I8Rrl47ZhuUurnwwnl6i2nNiMboigS0FHoq0x/KpAH4gXyT6jBmLn24XBn121G+XhYPLM6MeRNZ2L2h5Pdqm22Wmqr3tK02vHAGzPmByIKy7lPIdWS5yM62GH2QySEzm3GRSCA8kE3Gc9WnjJfLA5TQUo2EtNatZoMLFKtZVFILK/kAAcmWRdJxHH/605gHWzT45kXltyBCQwlc0EN4WMeYCKgs+ZZ7pwZlHZlMuoMx53uxLwe7jQib4F9BxO6FIxg2/NpQHzmhcY842hOgk/czEP0sTReioV7hPBjwdwkFWu8ZAOIQV+2EcSwZRpCrJzNZ4nIc3E2WEBcx7MprK3wl5D9OwPHtFbYZmFGWLFaMpKtR9ce83GYfdv0qm3T68lPR9oWsDJsS4u0rYI1FQMkq97v9MzhxW5d5a6kxBxBnTJ5XNL6tBfmpH+144JDLeCE/A4kgC4XR5tXpmY0JeMhEbnWkuSCMFg4m0Wt2I/e6PZC7g6/bHyphjFRa5re1MLNaixcCDsquQ6cbrBA174boJqK/iJ/wx9njhLUfJWZmkUp4/dWt3C0DZvNKHkgdiFTkzQZmOf97l13sHtQeEz3abiJKJQrSgljjO9LzAUrwvqxqgUbOWjjYZFsvxYwtvjExsyn35dwFi1jLLQO5oaZUcbVnRW76Y/M657ZNfcaU6/YUsWwSimzYINYPBKwy3jYzFbCzoQPOnebCOw4T1eUQ5comq0l7DE8N3Btd14mAQRdCwRnc24Dra9h1wTPRczNPzVwWK7282JdqvoGu9Tr9SdN1Ru63ijan1JzbPoUgTOFB5G/vEI3lM+YbUfNCva15q3i2EWA30Nr+2huqGRzK0Vte6xWXSsW0qp9tHJdH1kUORjbgv0L69H9rfjumauYFND/ge8ivVfkvGazqellOe8p65k8WML2HPtZvFcM+30Q3wfz/ZfMt1Hd39QB/TUf2W9BBQps7FH+WzFf6bu0X8p8pXgvL/E1mq1Gqzzx7c0U8dPYY77LXRuT8DIe9SsXlS31OfPiR8dklndCgx88+OauNyKIjyuO7CsOx/Xf3BWHYv3K1rd5pXvw5QGYdeg9HSz54ztfQ3n6ry7tH4z7rjM2IwAA").getBytes());
      }

      p.setEncryptionKeyId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8));
      return p;
   }

   public List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException {
      List<ListFeedbackItem> list = new ArrayList();

      for(int i = 0; i < 10; ++i) {
         ListFeedbackItem item = new ListFeedbackItem();
         if (this.getPropertyHandler().getProperty("MOCK_FEEDB") != null) {
            try {
               LOG.info("Loading prescription from " + this.getPropertyHandler().getProperty("MOCK_FEEDB"));
               item.setContent(IOUtils.getBytes(IOUtils.getResourceAsStream(this.getPropertyHandler().getProperty("MOCK_FEEDB"))));
            } catch (IOException var6) {
               var6.printStackTrace();
               item.setContent(this.compressedB64toString("H4sIAAAAAAAAAH1SYW/aMBD9vPyKWz61EkmAaVLHgCqjTIvKgkToun40ySVYC7ZnOw38+10c6FpNmhUpsu/du/fubnp7PNTwjNpwKWb+KBz6gCKXBRfVzE+ydXBz8/FTMPJv5970fRB4HtBZSHXSvNpbuFpcw3g4GsIGc64C7OMrnqMwWEAjCtRg9wixYjn9zpEB/OhLwjgcwlUH8M8h//qz4zjJBg7sBEJaaAwSCTdQ8hoBjzkqC1xALg+q5kzkCC23e1foTBM6kqczidxZRnhGGYpu5WskMNurvpy9tWoSRW3bhsypDqWuorpHm2iVLJZptgxIeZ/3IGo0BjT+brgm07sTMEW6crYjtTVrQWpglUaKWdnpbjW31OABGFnalml0PAU3VvNdY9807qKS3L8GUOuYAD/OIMl8+BJnSTZwLI/J9tv6YQuP8WYTp9tkmcF6A4t1epdsk3VKt68Qp09wn6R3A0BqGxXCo9KdBxLKu5Zi0fcvQ3wjopS9KKNo3CXPyZ2oGlYhVJKWSJApUKgP3HTDNSSxcDw1P3DLrHv7x1roeUEw97ypmpTUox3LfwFtpTATNfPPwzConwltQt3tGYY79MF716OOhr/guqG1H9zAxsPhKPr5fZXRBA8s4MLYblNcnuET455XMney/lMILqLCoyn8OfmZWjzauVtI+tgLYBq5gDeN/jqZe38AuxSP0mMDAAA=").getBytes());
            }
         } else {
            item.setContent(this.compressedB64toString("H4sIAAAAAAAAAH1SYW/aMBD9vPyKWz61EkmAaVLHgCqjTIvKgkToun40ySVYC7ZnOw38+10c6FpNmhUpsu/du/fubnp7PNTwjNpwKWb+KBz6gCKXBRfVzE+ydXBz8/FTMPJv5970fRB4HtBZSHXSvNpbuFpcw3g4GsIGc64C7OMrnqMwWEAjCtRg9wixYjn9zpEB/OhLwjgcwlUH8M8h//qz4zjJBg7sBEJaaAwSCTdQ8hoBjzkqC1xALg+q5kzkCC23e1foTBM6kqczidxZRnhGGYpu5WskMNurvpy9tWoSRW3bhsypDqWuorpHm2iVLJZptgxIeZ/3IGo0BjT+brgm07sTMEW6crYjtTVrQWpglUaKWdnpbjW31OABGFnalml0PAU3VvNdY9807qKS3L8GUOuYAD/OIMl8+BJnSTZwLI/J9tv6YQuP8WYTp9tkmcF6A4t1epdsk3VKt68Qp09wn6R3A0BqGxXCo9KdBxLKu5Zi0fcvQ3wjopS9KKNo3CXPyZ2oGlYhVJKWSJApUKgP3HTDNSSxcDw1P3DLrHv7x1roeUEw97ypmpTUox3LfwFtpTATNfPPwzConwltQt3tGYY79MF716OOhr/guqG1H9zAxsPhKPr5fZXRBA8s4MLYblNcnuET455XMney/lMILqLCoyn8OfmZWjzauVtI+tgLYBq5gDeN/jqZe38AuxSP0mMDAAA=").getBytes());
         }

         item.setRid("BEPPaabbccd" + i);
         item.setSentBy("20795810001");
         item.setSentDate(GregorianCalendar.getInstance());
         list.add(item);
      }

      return list;
   }

   public List<String> listOpenPrescription(String patientId) throws IntegrationModuleException {
      List<String> list = new ArrayList();

      for(int i = 0; i < 10; ++i) {
         list.add("BEPP" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8));
      }

      return list;
   }

   public void prepareCreatePrescription(String patientId, String pp) {
      LOG.info("prepareCreatePrescription : " + patientId);
   }

   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      LOG.info("revokePrescription : " + rid);
   }

   public void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException {
      LOG.info("sendNotification");
   }

   public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
      LOG.info("updateFeedbackFlag");
   }

   public void setPersonalPassword(String personalPassword) throws IntegrationModuleException {
   }
}
