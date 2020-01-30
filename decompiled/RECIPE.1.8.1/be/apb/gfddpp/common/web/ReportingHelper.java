package be.apb.gfddpp.common.web;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.log.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReportingHelper {
   private static final Logger LOG = Logger.getLogger(ReportingHelper.class);
   private static final int LENGTH_SINGLE_SERIES_PLOT = 2;
   private static final String FAILURE_SUFFIX = ".failure";
   private static final String SUCCESS_SUFFIX = ".success";
   public static final String COUNT = "count";
   public static final String DATETIME = "datetime";
   public static final String TAG = "tag";
   public static final String MAXTIME = "maxtime";
   public static final String MINTIME = "mintime";
   public static final String MEAN = "mean";
   public static final String PHARMACY_ID = "PHARMACY_ID";
   public static final String PHARMACY_ID_TYPE = "PHARMACY_ID_TYPE";
   public static final String ID = "ID";
   public static final String PATIENT_ID = "PATIENT_ID";
   public static final String PATIENT_ID_TYPE = "PATIENT_ID_TYPE";
   public static final String CREATED = "CREATED";
   public static final String SGUID = "SGUID";
   public static final String DGUID = "DGUID";
   public static final String MESSAGE_ID = "MESSAGE_ID";
   public static final String ACTOR_ID = "ACTOR_ID";
   public static final String SYSTEM_OWNER = "SYSTEM_OWNER";
   public static final String PARAMETERS = "METHOD_PARAMETERS";
   public static final String SERVICE_NAME = "SERVICE_NAME";
   public static final String SUCCESS = "SUCCESS";
   public static final String DELIVERED = "DELIVERED";
   public static final String UNREVOKED = "UNREVOKED";
   public static final String DISPENSATION = "DISPENSATION";
   public static final String REVOKED = "REVOKED";
   public static final String QUALITY = "QUALITY";
   public static final String META_DATA = "META-DATA";
   public static final String NON_THERAPEUTICAL_RELATION = "NON_THERAPEUTICAL_RELATION";
   public static final String MOTIVATION_TYPE = "MOTIVATION_TYPE";

   public static Plot createPlot(String serieName, List<Object[]> data) throws GFDDPPException {
      if (data != null && !data.isEmpty()) {
         if (((Object[])data.get(0)).length == 2) {
            return createSingleSeriesPlot(serieName, data);
         }

         if (((Object[])data.get(0)).length > 2) {
            return createMultiSeriesPlot(data);
         }
      }

      LOG.error("Invalid data : " + serieName + " data = " + data);
      throw new GFDDPPException("reporting.data.structure.multi.series.plot" + new Object[]{serieName});
   }

   private static Plot createSingleSeriesPlot(String serieName, List<Object[]> data) throws GFDDPPException {
      if (data != null && !data.isEmpty() && ((Object[])data.get(0)).length != 2) {
         throw new GFDDPPException("reporting.data.structure.multi.series.plot" + new Object[]{serieName});
      } else {
         Plot plot = new Plot(serieName);
         Serie serie = new Serie(serieName);
         Iterator var4 = data.iterator();

         while(var4.hasNext()) {
            Object[] o = (Object[])var4.next();
            int i = 1;
            String x = null;
            String y = null;
            String z = null;
            String xName = null;
            String yName = null;

            for(String var12 = null; i < o.length; ++i) {
               switch(i) {
               case 1:
                  xName = getData(o[i]);
                  break;
               case 2:
                  x = getData(o[i]);
                  break;
               case 3:
                  yName = getData(o[i]);
                  break;
               case 4:
                  y = getData(o[i]);
                  break;
               case 5:
                  var12 = getData(o[i]);
                  break;
               case 6:
                  z = getData(o[i]);
                  break;
               default:
                  LOG.debug("CURRENT STRUCTURE HANDLE ONLY 'serieName xName X yName Y zName Z' VALUE");
                  LOG.debug("i = : " + i + ", Value : " + o[i]);
               }
            }

            serie.addPoint(new Point(getData(x), getData(y), getData(z)));
         }

         plot.addSerie(serie);
         return plot;
      }
   }

   private static Plot createMultiSeriesPlot(List<Object[]> data) throws GFDDPPException {
      if (data != null && !data.isEmpty() && ((Object[])data.get(0)).length <= 2) {
         throw new GFDDPPException("reporting.data.structure.multi.series.plot");
      } else {
         boolean hasFailure = hasFailure(data);
         Plot plot = new Plot();
         String name = "";
         Serie serie = null;

         Point p;
         for(Iterator var5 = data.iterator(); var5.hasNext(); serie.addPoint(p)) {
            Object[] o = (Object[])var5.next();
            int i = 0;
            String serieName = null;
            String x = null;
            String y = null;
            String z = null;
            String xName = null;
            String yName = null;

            for(String var14 = null; i < o.length; ++i) {
               switch(i) {
               case 0:
                  serieName = getData(o[i]);
                  if (serie != null && name.equalsIgnoreCase(serieName)) {
                     break;
                  }

                  if (serie != null && hasFailure) {
                     addFailurePoint(serie);
                  }

                  serie = new Serie(serieName);
                  name = serieName;
                  plot.addSerie(serie);
                  break;
               case 1:
                  xName = getData(o[i]);
                  break;
               case 2:
                  x = getData(o[i]);
                  break;
               case 3:
                  yName = getData(o[i]);
                  break;
               case 4:
                  y = getData(o[i]);
                  break;
               case 5:
                  var14 = getData(o[i]);
                  break;
               case 6:
                  z = getData(o[i]);
                  break;
               default:
                  LOG.debug("CURRENT STRUCTURE HANDLE ONLY 'serieName xName X yName Y zName Z' VALUE");
                  LOG.debug("i = : " + i + ", Value : " + o[i]);
               }

               if (z != null && z.length() > 10) {
                  z = z.substring(0, 10);
               }
            }

            p = new Point(x, y, z);
            if (serie == null) {
               serie = new Serie();
               plot.addSerie(serie);
            }
         }

         return plot;
      }
   }

   private static String getData(Object o) {
      if (o == null) {
         return "";
      } else {
         return o instanceof Date ? (new SimpleDateFormat("yyyy-MM-dd")).format((Date)o) : o.toString();
      }
   }

   private static boolean hasFailure(List<Object[]> data) {
      Iterator var1 = data.iterator();

      while(var1.hasNext()) {
         Object[] o = (Object[])var1.next();

         for(int i = 0; i < o.length; ++i) {
            if (isFailure(o)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean isFailure(Object[] o) {
      Object[] var1 = o;
      int var2 = o.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Object obj = var1[var3];
         String data = getData(obj);
         if (data != null && data.toLowerCase().contains(".failure")) {
            return true;
         }
      }

      return false;
   }

   private static void addFailurePoint(Serie serie) {
      boolean contains = false;
      Iterator var2 = serie.getPoints().iterator();

      while(var2.hasNext()) {
         Point p = (Point)var2.next();
         if (p.getX().equalsIgnoreCase("count.failure")) {
            contains = true;
            break;
         }
      }

      if (!contains) {
         serie.addPoint(new Point("count.failure", "0", "0"));
      }

   }
}
