package be.business.connector.recipe.executor.mock;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.recipe.executor.ExecutorIntegrationModuleImpl;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.ListNotificationsItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;

public class ExecutorIntegrationModuleMock extends ExecutorIntegrationModuleImpl {
   private static final Logger LOG = Logger.getLogger(ExecutorIntegrationModuleMock.class);
   private static final String MOCK_NOTIF = "H4sIAAAAAAAAAO1YTVPjOBA9w69w5byO5XwwhBKhXEmGyRAC5RiWOSq2iLXjr5VkEvbXb8uObTmB2Z2t3cNWATlEcre6X/fTawO+2sWR8UK5YGly2bG7qGPQxE8DlmwuO/PVnXl+PhyZdudqfIqTVLJn5hMJtgb4JeKyE0qZXViWoPyF+VR0OfVZRrtr2jFOTwqbi51gtd12u+1u+92Ub6weQrb1dLtY+SGNickSIUnil36CXYhie5GW4X4QyNDT6u5E0BmfGgaWdCfHMmTCgA9pGWGreKisvsc05DEVgmzo+PQEh5QElMO3E6zSCQgP1OIE+4GxuuxMpubKc5ZTx512jNVjWbAxIOnZPTTEll9YY0v3xaxwnU/Nm9vZF7f0Gyi/Tzb6ZJ8ju2v3+gNssdI8IJKqI0cmOjN7PWwVG+qJZDEdo7ML1L9ACECoZZEpTfZJAwA/I1y+Fgst9JfJveN637Skq+BVXB3jsXUGDMnCV8F8RpIKJ7g8My5kQiCRxzTl2GrW++ckZtFrsQF95uw3oBpYNbtF0paWNRSvhoOLJjOayJ8EZ3fPBvDbhQ9Cg5+BSHhMfCbkWxitY1CuStCkfwWphQMT/3uSbiMabOIaWp2YM7lZ3v26mE2vb2dLT8svTSjnqsYVyY6OgaAVffFzGlVFfIt/ZZVqzmVwL+pUavt7x5u3c+j3UN9Gtj3qN7zROPCVKnK0OaBXa5qvUyaOi7VmXIYVzasrAEF6JuqbNmquAEBs2wL3d4etXc2etJRjEtG6mYpcpT0mQcDh3h86O9OpO1uttAPCNKYaG/w0TyTfc1BznNw9LD1XJ9Rad7NafvgPlo3P0ACoqb7tT2ZAF/fu4QmMWc1yITmlcuzm1AhyA06BjxKYYrc0CdMc7kwer6HhZ0ACbVmC1rBiS+s1lpwkgvhKFA96/y5XNMie6yxXzsSb3y31e1TeIZpLkNsog8A+Z1mpu1Ub3tW4d0UOGpbLMOUVZF0JfigFPe3n6uqq0YJ/onc62T/P3ZW3dG5nR6rX4vzCqa3arG9rhOpSAxAz4adxFlGoiuQ5EEnb2Bu8kIipsgW1RbNTqiWIAYzxQ7U87Ozb8jhzpvPltWYGhCNSCY1GaiZpfNSC95mjB5h7s1v9ltKgHs5Nsf00kZUsqXVjVW1pJ07dh2tzsrzZnzoajTpjZPeHA/tcO7MkfUCLNnj0hewMEmcG+mUYR8C2+lFtrQgMObSjHoa9B80ApXSaq/BYwBJplB+AKq7gW2cWNz3ZyFCLEsDcAAEboy68XFSL5nGeMNkstaQelnNdthU0LQHIoOVZykkrNOZpLum7eEGlvJkOlIG2kTgXfh4RfoC2dRS2jpqotFHvM47YM/Vf/ahxqqMv5p9nk2+TxUxDR4KYJTCyuaK+Th/r8CD8e04SWWurVmD7sLzYatsCiWuy/z3e9/493qvXVWNx2aEJKJMmqEb6bDh5Mf5ZYgwRijdGf2f7JBOgqK8wMDgIgDE0YCGqt963aq6hK18i9rpR3Ah9QvwnE2MdEfh8TIeP6fAxHT6mw8d0+L9OBxCx/V+d2Gr/YwVb+v9fxqd/AsXSGAp3EgAA";
   private static final String MOCK_PRESC = "H4sIAAAAAAAAAO1a33OiSBB+vq3a/2GKe7l9QMAfK6ZItiw1CbXGpNRcbR7HYdS5hYGF0Zj//hqMgFFQ2OT2sokvEez+prun5/uGIcaXlWOjJfUD5vJTSauoEqKcuBbjs1Ppdnwu6xIKBOYWtl1OTyXuSujL2ccPxneHzn2HBgGeUQQgPDiV5kJ4J4pyf39foXOKbTGvTGfusjKhyhrDtwIlclQCMqcOVpaahD5+iNxPYNwtiPtaxfVnSlVVNUWtK2AEFn8m9quAZdp/u+qPohFkxsOhCZUg6D/Q48eA6Czqwy209TE2Ye78krIhFhqdSp2uPBq3B932sCuh0d9h7TTprKpqqvpZ1QyF7GIYSia8wSJQsyt/vepdDjeIqnSm1T83G6oGsPUKpNaKvrbU9cdQWCZW/7rT7gNQP4Id9jrmTS+Fq1ON1CmuyzW92pDrOq3LE0KqE51ga4qbWG8196IDvoUFjTKVVV0Oc41u7DEUzKFnautEVU/CWKPLPWYB5fsmI2UxJx72xUOOyW4hLzs37eH4bn8pM1LbQYvnehfNg0XjzR8CRhjme+d7Dx7HUIKuX0HdBfmOui7HtmUo0d2c9JXs/KGnsqpn+JQwj1EuXr606/Z6hqrCCvYWE5uRNX8UquswzFemP1nPfVUDj6eEYUxdO6Pu2Ws5a0V5WByapxj0pj02e4Nxeik3Wy21VW9pWu3ADBhT5gciKs+5T6Hzkus8J+ww+yGyQuc24yIQoAXgnNzP8Z4wX8wzGGLHOLLTWrWaDOxSrWVSS+ygHAMPDLMq1Jej3rdUfR1s0wONGC7DfUMYSvbUGsLHPMBEgPQeNfPHttNuPuNhezBqd8bm9SAlVt4c+w4mdCEYwbbn04D4zAvDOZTtsQqQpHqUEsTmeCHmbp4ixJbH01fs8qwKEaM+s1LEuKUUI/bOYbrE5mC1DRYQ1/FsCpMs/AUshtSNXLcltlnYG1bsl9zJcaQrj/k47MRNp9U2nfbkpzxlA8qGbWwhZSu6xGKEZPp77a45uEgvs+MXVhKQoE6pni6bwG4i5rh3lcrCoRawRIEcEkSXi3xxy3SNBmU8JCfXWpDjMAwWjmdRK06lO7y9kDuDr+t0qmFd1JqmN7VwixsbFwOPVmAbHouwQNe+G6Caiv4in+CPM0MJ7JELdWcYpVTqG+fiJTdsNqXkgdjFok3apW+e9zp3nX76GeOx8yfhTqNYzyhlwjF+LDAXrJAaxL4WbPtA6MMFs/laJN4SQxtTn/5YwDNtqXhBVJgbtkipdFMTd9Mbmtdds2NuaVa34IzFuEq5wGA3WaIasBF5WI9XJtSEH9p36yqkCkCXlIN6FG7cMhEZnhu4tjsr1QiCrgSCh3xuA9uvYG8F14UiLjA48NpxwvR8AlZ9nQL2gtKlqXpD1xuFpWtnkLWEEXgM8aD8l1fohvIps+1Ix2APbN4qjl0I+a3I3rvwxfGWEb5ydLfFdNWVYiGt2kNL1/WRRZGDsS3YPzArnd+OAw8d6+wg/T84MHJ8SR5sNpuaXpoHnzKhyYMF7Oaxn8WFBcHfDhm+s+Em3v+ODde+25s/oMTmIyPOqUCBjT3Kfzs2LH8094vZsBwXHkuGjWar0foJMtwaKqKskcd8l7s2JuF5P+pVLiobOnRmJZ45k2HeEDW+c+Mm3td2RBJhvB+T5B+TOK7/Go9JFOsXy+L6jfL+1xMQ2t43gzD5m3fOhpL+/5uzfwEC3nENxyMAAA==";

   public ExecutorIntegrationModuleMock() throws IntegrationModuleException {
   }

   public void createFeedback(String prescriberId, String rid, byte[] feedbackText) throws IntegrationModuleException {
   }

   protected void init() throws IntegrationModuleException {
   }

   public GetPrescriptionForExecutorResult getPrescription(String rid) throws IntegrationModuleException {
      LOG.info("GetPrescriptionForExecutorResult : " + rid);
      GetPrescriptionForExecutorResult result = new GetPrescriptionForExecutorResult();
      result.setCreationDate(GregorianCalendar.getInstance());
      result.setEncryptionKeyId(UUID.randomUUID().toString().replaceAll("-", ""));
      result.setPatientId("84071230581l");
      result.setPrescriberId("12659389004l");
      result.setPrescriptionType("P0");
      result.setRid("BEP0" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8));
      result.setTimestampingId(UUID.randomUUID().toString().replaceAll("-", ""));
      result.setEncryptionKey("fake key".getBytes());
      result.setSealedContent("H4sIAAAAAAAAAO1a33OiSBB+vq3a/2GKe7l9QMAfK6ZItiw1CbXGpNRcbR7HYdS5hYGF0Zj//hqMgFFQ2OT2sokvEez+prun5/uGIcaXlWOjJfUD5vJTSauoEqKcuBbjs1Ppdnwu6xIKBOYWtl1OTyXuSujL2ccPxneHzn2HBgGeUQQgPDiV5kJ4J4pyf39foXOKbTGvTGfusjKhyhrDtwIlclQCMqcOVpaahD5+iNxPYNwtiPtaxfVnSlVVNUWtK2AEFn8m9quAZdp/u+qPohFkxsOhCZUg6D/Q48eA6Czqwy209TE2Ye78krIhFhqdSp2uPBq3B932sCuh0d9h7TTprKpqqvpZ1QyF7GIYSia8wSJQsyt/vepdDjeIqnSm1T83G6oGsPUKpNaKvrbU9cdQWCZW/7rT7gNQP4Id9jrmTS+Fq1ON1CmuyzW92pDrOq3LE0KqE51ga4qbWG8196IDvoUFjTKVVV0Oc41u7DEUzKFnautEVU/CWKPLPWYB5fsmI2UxJx72xUOOyW4hLzs37eH4bn8pM1LbQYvnehfNg0XjzR8CRhjme+d7Dx7HUIKuX0HdBfmOui7HtmUo0d2c9JXs/KGnsqpn+JQwj1EuXr606/Z6hqrCCvYWE5uRNX8UquswzFemP1nPfVUDj6eEYUxdO6Pu2Ws5a0V5WByapxj0pj02e4Nxeik3Wy21VW9pWu3ADBhT5gciKs+5T6Hzkus8J+ww+yGyQuc24yIQoAXgnNzP8Z4wX8wzGGLHOLLTWrWaDOxSrWVSS+ygHAMPDLMq1Jej3rdUfR1s0wONGC7DfUMYSvbUGsLHPMBEgPQeNfPHttNuPuNhezBqd8bm9SAlVt4c+w4mdCEYwbbn04D4zAvDOZTtsQqQpHqUEsTmeCHmbp4ixJbH01fs8qwKEaM+s1LEuKUUI/bOYbrE5mC1DRYQ1/FsCpMs/AUshtSNXLcltlnYG1bsl9zJcaQrj/k47MRNp9U2nfbkpzxlA8qGbWwhZSu6xGKEZPp77a45uEgvs+MXVhKQoE6pni6bwG4i5rh3lcrCoRawRIEcEkSXi3xxy3SNBmU8JCfXWpDjMAwWjmdRK06lO7y9kDuDr+t0qmFd1JqmN7VwixsbFwOPVmAbHouwQNe+G6Caiv4in+CPM0MJ7JELdWcYpVTqG+fiJTdsNqXkgdjFok3apW+e9zp3nX76GeOx8yfhTqNYzyhlwjF+LDAXrJAaxL4WbPtA6MMFs/laJN4SQxtTn/5YwDNtqXhBVJgbtkipdFMTd9Mbmtdds2NuaVa34IzFuEq5wGA3WaIasBF5WI9XJtSEH9p36yqkCkCXlIN6FG7cMhEZnhu4tjsr1QiCrgSCh3xuA9uvYG8F14UiLjA48NpxwvR8AlZ9nQL2gtKlqXpD1xuFpWtnkLWEEXgM8aD8l1fohvIps+1Ix2APbN4qjl0I+a3I3rvwxfGWEb5ydLfFdNWVYiGt2kNL1/WRRZGDsS3YPzArnd+OAw8d6+wg/T84MHJ8SR5sNpuaXpoHnzKhyYMF7Oaxn8WFBcHfDhm+s+Em3v+ODde+25s/oMTmIyPOqUCBjT3Kfzs2LH8094vZsBwXHkuGjWar0foJMtwaKqKskcd8l7s2JuF5P+pVLiobOnRmJZ45k2HeEDW+c+Mm3td2RBJhvB+T5B+TOK7/Go9JFOsXy+L6jfL+1xMQ2t43gzD5m3fOhpL+/5uzfwEC3nENxyMAAA==".getBytes());
      if (this.getPropertyHandler().hasProperty("MOCK_PRESC")) {
         try {
            LOG.info("Loading prescription from " + this.getPropertyHandler().getProperty("MOCK_PRESC"));
            result.setPrescription(IOUtils.getBytes(IOUtils.getResourceAsStream(this.getPropertyHandler().getProperty("MOCK_PRESC"))));
         } catch (IOException var5) {
            var5.printStackTrace();
            byte[] b = this.compressedB64toString("H4sIAAAAAAAAAO1a33OiSBB+vq3a/2GKe7l9QMAfK6ZItiw1CbXGpNRcbR7HYdS5hYGF0Zj//hqMgFFQ2OT2sokvEez+prun5/uGIcaXlWOjJfUD5vJTSauoEqKcuBbjs1Ppdnwu6xIKBOYWtl1OTyXuSujL2ccPxneHzn2HBgGeUQQgPDiV5kJ4J4pyf39foXOKbTGvTGfusjKhyhrDtwIlclQCMqcOVpaahD5+iNxPYNwtiPtaxfVnSlVVNUWtK2AEFn8m9quAZdp/u+qPohFkxsOhCZUg6D/Q48eA6Czqwy209TE2Ye78krIhFhqdSp2uPBq3B932sCuh0d9h7TTprKpqqvpZ1QyF7GIYSia8wSJQsyt/vepdDjeIqnSm1T83G6oGsPUKpNaKvrbU9cdQWCZW/7rT7gNQP4Id9jrmTS+Fq1ON1CmuyzW92pDrOq3LE0KqE51ga4qbWG8196IDvoUFjTKVVV0Oc41u7DEUzKFnautEVU/CWKPLPWYB5fsmI2UxJx72xUOOyW4hLzs37eH4bn8pM1LbQYvnehfNg0XjzR8CRhjme+d7Dx7HUIKuX0HdBfmOui7HtmUo0d2c9JXs/KGnsqpn+JQwj1EuXr606/Z6hqrCCvYWE5uRNX8UquswzFemP1nPfVUDj6eEYUxdO6Pu2Ws5a0V5WByapxj0pj02e4Nxeik3Wy21VW9pWu3ADBhT5gciKs+5T6Hzkus8J+ww+yGyQuc24yIQoAXgnNzP8Z4wX8wzGGLHOLLTWrWaDOxSrWVSS+ygHAMPDLMq1Jej3rdUfR1s0wONGC7DfUMYSvbUGsLHPMBEgPQeNfPHttNuPuNhezBqd8bm9SAlVt4c+w4mdCEYwbbn04D4zAvDOZTtsQqQpHqUEsTmeCHmbp4ixJbH01fs8qwKEaM+s1LEuKUUI/bOYbrE5mC1DRYQ1/FsCpMs/AUshtSNXLcltlnYG1bsl9zJcaQrj/k47MRNp9U2nfbkpzxlA8qGbWwhZSu6xGKEZPp77a45uEgvs+MXVhKQoE6pni6bwG4i5rh3lcrCoRawRIEcEkSXi3xxy3SNBmU8JCfXWpDjMAwWjmdRK06lO7y9kDuDr+t0qmFd1JqmN7VwixsbFwOPVmAbHouwQNe+G6Caiv4in+CPM0MJ7JELdWcYpVTqG+fiJTdsNqXkgdjFok3apW+e9zp3nX76GeOx8yfhTqNYzyhlwjF+LDAXrJAaxL4WbPtA6MMFs/laJN4SQxtTn/5YwDNtqXhBVJgbtkipdFMTd9Mbmtdds2NuaVa34IzFuEq5wGA3WaIasBF5WI9XJtSEH9p36yqkCkCXlIN6FG7cMhEZnhu4tjsr1QiCrgSCh3xuA9uvYG8F14UiLjA48NpxwvR8AlZ9nQL2gtKlqXpD1xuFpWtnkLWEEXgM8aD8l1fohvIps+1Ix2APbN4qjl0I+a3I3rvwxfGWEb5ydLfFdNWVYiGt2kNL1/WRRZGDsS3YPzArnd+OAw8d6+wg/T84MHJ8SR5sNpuaXpoHnzKhyYMF7Oaxn8WFBcHfDhm+s+Em3v+ODde+25s/oMTmIyPOqUCBjT3Kfzs2LH8094vZsBwXHkuGjWar0foJMtwaKqKskcd8l7s2JuF5P+pVLiobOnRmJZ45k2HeEDW+c+Mm3td2RBJhvB+T5B+TOK7/Go9JFOsXy+L6jfL+1xMQ2t43gzD5m3fOhpL+/5uzfwEC3nENxyMAAA==").getBytes();
            result.setSealedContent(b);
            result.setPrescription(b);
         }
      } else {
         byte[] b = this.compressedB64toString("H4sIAAAAAAAAAO1a33OiSBB+vq3a/2GKe7l9QMAfK6ZItiw1CbXGpNRcbR7HYdS5hYGF0Zj//hqMgFFQ2OT2sokvEez+prun5/uGIcaXlWOjJfUD5vJTSauoEqKcuBbjs1Ppdnwu6xIKBOYWtl1OTyXuSujL2ccPxneHzn2HBgGeUQQgPDiV5kJ4J4pyf39foXOKbTGvTGfusjKhyhrDtwIlclQCMqcOVpaahD5+iNxPYNwtiPtaxfVnSlVVNUWtK2AEFn8m9quAZdp/u+qPohFkxsOhCZUg6D/Q48eA6Czqwy209TE2Ye78krIhFhqdSp2uPBq3B932sCuh0d9h7TTprKpqqvpZ1QyF7GIYSia8wSJQsyt/vepdDjeIqnSm1T83G6oGsPUKpNaKvrbU9cdQWCZW/7rT7gNQP4Id9jrmTS+Fq1ON1CmuyzW92pDrOq3LE0KqE51ga4qbWG8196IDvoUFjTKVVV0Oc41u7DEUzKFnautEVU/CWKPLPWYB5fsmI2UxJx72xUOOyW4hLzs37eH4bn8pM1LbQYvnehfNg0XjzR8CRhjme+d7Dx7HUIKuX0HdBfmOui7HtmUo0d2c9JXs/KGnsqpn+JQwj1EuXr606/Z6hqrCCvYWE5uRNX8UquswzFemP1nPfVUDj6eEYUxdO6Pu2Ws5a0V5WByapxj0pj02e4Nxeik3Wy21VW9pWu3ADBhT5gciKs+5T6Hzkus8J+ww+yGyQuc24yIQoAXgnNzP8Z4wX8wzGGLHOLLTWrWaDOxSrWVSS+ygHAMPDLMq1Jej3rdUfR1s0wONGC7DfUMYSvbUGsLHPMBEgPQeNfPHttNuPuNhezBqd8bm9SAlVt4c+w4mdCEYwbbn04D4zAvDOZTtsQqQpHqUEsTmeCHmbp4ixJbH01fs8qwKEaM+s1LEuKUUI/bOYbrE5mC1DRYQ1/FsCpMs/AUshtSNXLcltlnYG1bsl9zJcaQrj/k47MRNp9U2nfbkpzxlA8qGbWwhZSu6xGKEZPp77a45uEgvs+MXVhKQoE6pni6bwG4i5rh3lcrCoRawRIEcEkSXi3xxy3SNBmU8JCfXWpDjMAwWjmdRK06lO7y9kDuDr+t0qmFd1JqmN7VwixsbFwOPVmAbHouwQNe+G6Caiv4in+CPM0MJ7JELdWcYpVTqG+fiJTdsNqXkgdjFok3apW+e9zp3nX76GeOx8yfhTqNYzyhlwjF+LDAXrJAaxL4WbPtA6MMFs/laJN4SQxtTn/5YwDNtqXhBVJgbtkipdFMTd9Mbmtdds2NuaVa34IzFuEq5wGA3WaIasBF5WI9XJtSEH9p36yqkCkCXlIN6FG7cMhEZnhu4tjsr1QiCrgSCh3xuA9uvYG8F14UiLjA48NpxwvR8AlZ9nQL2gtKlqXpD1xuFpWtnkLWEEXgM8aD8l1fohvIps+1Ix2APbN4qjl0I+a3I3rvwxfGWEb5ydLfFdNWVYiGt2kNL1/WRRZGDsS3YPzArnd+OAw8d6+wg/T84MHJ8SR5sNpuaXpoHnzKhyYMF7Oaxn8WFBcHfDhm+s+Em3v+ODde+25s/oMTmIyPOqUCBjT3Kfzs2LH8094vZsBwXHkuGjWar0foJMtwaKqKskcd8l7s2JuF5P+pVLiobOnRmJZ45k2HeEDW+c+Mm3td2RBJhvB+T5B+TOK7/Go9JFOsXy+L6jfL+1xMQ2t43gzD5m3fOhpL+/5uzfwEC3nENxyMAAA==").getBytes();
         result.setSealedContent(b);
         result.setPrescription(b);
      }

      return result;
   }

   public List<ListNotificationsItem> listNotifications(boolean readFlag) throws IntegrationModuleException {
      LOG.info("listNotifications : " + readFlag);
      byte[] bytes = null;
      byte[] bytes;
      if (this.getPropertyHandler().hasProperty("MOCK_NOTIF")) {
         try {
            LOG.info("Loading prescription from " + this.getPropertyHandler().getProperty("MOCK_NOTIF"));
            bytes = IOUtils.getBytes(IOUtils.getResourceAsStream(this.getPropertyHandler().getProperty("MOCK_NOTIF")));
         } catch (IOException var6) {
            var6.printStackTrace();
            bytes = this.compressedB64toString("H4sIAAAAAAAAAO1YTVPjOBA9w69w5byO5XwwhBKhXEmGyRAC5RiWOSq2iLXjr5VkEvbXb8uObTmB2Z2t3cNWATlEcre6X/fTawO+2sWR8UK5YGly2bG7qGPQxE8DlmwuO/PVnXl+PhyZdudqfIqTVLJn5hMJtgb4JeKyE0qZXViWoPyF+VR0OfVZRrtr2jFOTwqbi51gtd12u+1u+92Ub6weQrb1dLtY+SGNickSIUnil36CXYhie5GW4X4QyNDT6u5E0BmfGgaWdCfHMmTCgA9pGWGreKisvsc05DEVgmzo+PQEh5QElMO3E6zSCQgP1OIE+4GxuuxMpubKc5ZTx512jNVjWbAxIOnZPTTEll9YY0v3xaxwnU/Nm9vZF7f0Gyi/Tzb6ZJ8ju2v3+gNssdI8IJKqI0cmOjN7PWwVG+qJZDEdo7ML1L9ACECoZZEpTfZJAwA/I1y+Fgst9JfJveN637Skq+BVXB3jsXUGDMnCV8F8RpIKJ7g8My5kQiCRxzTl2GrW++ckZtFrsQF95uw3oBpYNbtF0paWNRSvhoOLJjOayJ8EZ3fPBvDbhQ9Cg5+BSHhMfCbkWxitY1CuStCkfwWphQMT/3uSbiMabOIaWp2YM7lZ3v26mE2vb2dLT8svTSjnqsYVyY6OgaAVffFzGlVFfIt/ZZVqzmVwL+pUavt7x5u3c+j3UN9Gtj3qN7zROPCVKnK0OaBXa5qvUyaOi7VmXIYVzasrAEF6JuqbNmquAEBs2wL3d4etXc2etJRjEtG6mYpcpT0mQcDh3h86O9OpO1uttAPCNKYaG/w0TyTfc1BznNw9LD1XJ9Rad7NafvgPlo3P0ACoqb7tT2ZAF/fu4QmMWc1yITmlcuzm1AhyA06BjxKYYrc0CdMc7kwer6HhZ0ACbVmC1rBiS+s1lpwkgvhKFA96/y5XNMie6yxXzsSb3y31e1TeIZpLkNsog8A+Z1mpu1Ub3tW4d0UOGpbLMOUVZF0JfigFPe3n6uqq0YJ/onc62T/P3ZW3dG5nR6rX4vzCqa3arG9rhOpSAxAz4adxFlGoiuQ5EEnb2Bu8kIipsgW1RbNTqiWIAYzxQ7U87Ozb8jhzpvPltWYGhCNSCY1GaiZpfNSC95mjB5h7s1v9ltKgHs5Nsf00kZUsqXVjVW1pJ07dh2tzsrzZnzoajTpjZPeHA/tcO7MkfUCLNnj0hewMEmcG+mUYR8C2+lFtrQgMObSjHoa9B80ApXSaq/BYwBJplB+AKq7gW2cWNz3ZyFCLEsDcAAEboy68XFSL5nGeMNkstaQelnNdthU0LQHIoOVZykkrNOZpLum7eEGlvJkOlIG2kTgXfh4RfoC2dRS2jpqotFHvM47YM/Vf/ahxqqMv5p9nk2+TxUxDR4KYJTCyuaK+Th/r8CD8e04SWWurVmD7sLzYatsCiWuy/z3e9/493qvXVWNx2aEJKJMmqEb6bDh5Mf5ZYgwRijdGf2f7JBOgqK8wMDgIgDE0YCGqt963aq6hK18i9rpR3Ah9QvwnE2MdEfh8TIeP6fAxHT6mw8d0+L9OBxCx/V+d2Gr/YwVb+v9fxqd/AsXSGAp3EgAA").getBytes();
         }
      } else {
         bytes = this.compressedB64toString("H4sIAAAAAAAAAO1YTVPjOBA9w69w5byO5XwwhBKhXEmGyRAC5RiWOSq2iLXjr5VkEvbXb8uObTmB2Z2t3cNWATlEcre6X/fTawO+2sWR8UK5YGly2bG7qGPQxE8DlmwuO/PVnXl+PhyZdudqfIqTVLJn5hMJtgb4JeKyE0qZXViWoPyF+VR0OfVZRrtr2jFOTwqbi51gtd12u+1u+92Ub6weQrb1dLtY+SGNickSIUnil36CXYhie5GW4X4QyNDT6u5E0BmfGgaWdCfHMmTCgA9pGWGreKisvsc05DEVgmzo+PQEh5QElMO3E6zSCQgP1OIE+4GxuuxMpubKc5ZTx512jNVjWbAxIOnZPTTEll9YY0v3xaxwnU/Nm9vZF7f0Gyi/Tzb6ZJ8ju2v3+gNssdI8IJKqI0cmOjN7PWwVG+qJZDEdo7ML1L9ACECoZZEpTfZJAwA/I1y+Fgst9JfJveN637Skq+BVXB3jsXUGDMnCV8F8RpIKJ7g8My5kQiCRxzTl2GrW++ckZtFrsQF95uw3oBpYNbtF0paWNRSvhoOLJjOayJ8EZ3fPBvDbhQ9Cg5+BSHhMfCbkWxitY1CuStCkfwWphQMT/3uSbiMabOIaWp2YM7lZ3v26mE2vb2dLT8svTSjnqsYVyY6OgaAVffFzGlVFfIt/ZZVqzmVwL+pUavt7x5u3c+j3UN9Gtj3qN7zROPCVKnK0OaBXa5qvUyaOi7VmXIYVzasrAEF6JuqbNmquAEBs2wL3d4etXc2etJRjEtG6mYpcpT0mQcDh3h86O9OpO1uttAPCNKYaG/w0TyTfc1BznNw9LD1XJ9Rad7NafvgPlo3P0ACoqb7tT2ZAF/fu4QmMWc1yITmlcuzm1AhyA06BjxKYYrc0CdMc7kwer6HhZ0ACbVmC1rBiS+s1lpwkgvhKFA96/y5XNMie6yxXzsSb3y31e1TeIZpLkNsog8A+Z1mpu1Ub3tW4d0UOGpbLMOUVZF0JfigFPe3n6uqq0YJ/onc62T/P3ZW3dG5nR6rX4vzCqa3arG9rhOpSAxAz4adxFlGoiuQ5EEnb2Bu8kIipsgW1RbNTqiWIAYzxQ7U87Ozb8jhzpvPltWYGhCNSCY1GaiZpfNSC95mjB5h7s1v9ltKgHs5Nsf00kZUsqXVjVW1pJ07dh2tzsrzZnzoajTpjZPeHA/tcO7MkfUCLNnj0hewMEmcG+mUYR8C2+lFtrQgMObSjHoa9B80ApXSaq/BYwBJplB+AKq7gW2cWNz3ZyFCLEsDcAAEboy68XFSL5nGeMNkstaQelnNdthU0LQHIoOVZykkrNOZpLum7eEGlvJkOlIG2kTgXfh4RfoC2dRS2jpqotFHvM47YM/Vf/ahxqqMv5p9nk2+TxUxDR4KYJTCyuaK+Th/r8CD8e04SWWurVmD7sLzYatsCiWuy/z3e9/493qvXVWNx2aEJKJMmqEb6bDh5Mf5ZYgwRijdGf2f7JBOgqK8wMDgIgDE0YCGqt963aq6hK18i9rpR3Ah9QvwnE2MdEfh8TIeP6fAxHT6mw8d0+L9OBxCx/V+d2Gr/YwVb+v9fxqd/AsXSGAp3EgAA").getBytes();
      }

      List<ListNotificationsItem> list = new ArrayList();

      for(int i = 0; i < 10; ++i) {
         be.business.connector.recipe.executor.domain.ListNotificationsItem item = new be.business.connector.recipe.executor.domain.ListNotificationsItem(new ListNotificationsItem());
         item.setContent(bytes);
         item.setSentBy("12659389004l");
         item.setSentDate(new Date());
         list.add(item);
      }

      return list;
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

   public void markAsArchived(String rid) throws IntegrationModuleException {
      LOG.info("markAsArchived : " + rid);
   }

   public void markAsDelivered(String rid) throws IntegrationModuleException {
      LOG.info("markAsDelivered : " + rid);
   }

   public void markAsUndelivered(String rid) throws IntegrationModuleException {
      LOG.info("markAsUndelivered : " + rid);
   }

   public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
      LOG.info("revoked : " + rid);
   }
}
