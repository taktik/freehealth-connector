package be.business.connector.recipe.utils;

import be.business.connector.core.utils.PropertyHandler;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

public class MedFileUtils {
   private static Map<String, MedFile> medFilesMap = new HashMap();
   private PropertyHandler propertyHandler;
   // $FF: synthetic field
   private static int[] $SWITCH_TABLE$be$business$connector$recipe$utils$MedFileTypeEnum;

   public MedFileUtils(PropertyHandler propertyHandler) {
      try {
         this.propertyHandler = propertyHandler;
         String filePath = propertyHandler.getProperty("medFilesDirectory");
         Path dir = Paths.get(filePath);
         if (Files.exists(dir, new LinkOption[0])) {
            Throwable var4 = null;
            Object var5 = null;

            try {
               DirectoryStream directoryStream = Files.newDirectoryStream(dir);

               try {
                  parseDirectoryStream(directoryStream);
               } finally {
                  if (directoryStream != null) {
                     directoryStream.close();
                  }

               }
            } catch (Throwable var14) {
               if (var4 == null) {
                  var4 = var14;
               } else if (var4 != var14) {
                  var4.addSuppressed(var14);
               }

               throw var4;
            }
         }
      } catch (Exception var15) {
         var15.printStackTrace();
      }

   }

   public MedFile getCNKCode(String cnk) throws IOException, ParseException {
      return (MedFile)medFilesMap.get(cnk);
   }

   private static void parseDirectoryStream(DirectoryStream<Path> directoryStream) throws IOException, ParseException {
      Iterator var2 = directoryStream.iterator();

      while(true) {
         Path path;
         do {
            if (!var2.hasNext()) {
               return;
            }

            path = (Path)var2.next();
         } while(!path.getFileName().toString().startsWith("MED"));

         LineIterator it = FileUtils.lineIterator(path.toFile(), "UTF-8");

         try {
            while(it.hasNext()) {
               MedFile medfile = parseLine(it.nextLine());
               medFilesMap.put(medfile.getCNK(), medfile);
            }
         } finally {
            LineIterator.closeQuietly(it);
         }
      }
   }

   private static MedFile parseLine(String line) throws ParseException {
      MedFile medFile = new MedFile();
      if (StringUtils.isEmpty(line)) {
         return medFile;
      } else {
         switch($SWITCH_TABLE$be$business$connector$recipe$utils$MedFileTypeEnum()[MedFileTypeEnum.valueOf(line.substring(0, 5)).ordinal()]) {
         case 1:
            medFile.setMedFileType(MedFileTypeEnum.MED01);
            medFile.setDateSuplement(parseDate(line.substring(5, 13)));
            medFile.setCNK(line.substring(13, 20));
            medFile.setCommercialStatus(CommercialStatusEnum.valueOf(line.substring(20, 21)));
            medFile.setCategorie(CategorieEnum.valueOf(line.substring(21, 22)));
            medFile.setFrBenaming(line.substring(22, 72).trim());
            medFile.setNlBenaming(line.substring(72, 122).trim());
            medFile.setPubliekprijsIncBTW(parseLong(line.substring(122, 129)));
            medFile.setAtcCode(line.substring(129, 136).trim());
         case 2:
         case 3:
            return medFile;
         default:
            throw new RuntimeException("Invalid line found in file.");
         }
      }
   }

   private static Date parseDate(String substring) throws ParseException {
      DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
      return formatter.parse(substring);
   }

   private static float parseLong(String number) {
      number = number.substring(0, number.length() - 2) + "." + number.substring(number.length() - 2);
      return Float.parseFloat(number);
   }

   public PropertyHandler getPropertyHandler() {
      if (this.propertyHandler == null) {
         this.propertyHandler = PropertyHandler.getInstance();
      }

      return this.propertyHandler;
   }

   public void setPropertyHandler(PropertyHandler propertyHandler) {
      this.propertyHandler = propertyHandler;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$be$business$connector$recipe$utils$MedFileTypeEnum() {
      int[] var10000 = $SWITCH_TABLE$be$business$connector$recipe$utils$MedFileTypeEnum;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[MedFileTypeEnum.values().length];

         try {
            var0[MedFileTypeEnum.MED01.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[MedFileTypeEnum.MED02.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[MedFileTypeEnum.MED03.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$be$business$connector$recipe$utils$MedFileTypeEnum = var0;
         return var0;
      }
   }
}
