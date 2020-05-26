package be.business.connector.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class IOUtils {
   private static final Logger LOG = Logger.getLogger(IOUtils.class);
   private static final int DEFAULT_BUFFER_SIZE = 4096;
   private static final int EOF = -1;

   public static byte[] getBytes(InputStream inputStream) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];

         int len;
         while((len = inputStream.read(buffer)) >= 0) {
            baos.write(buffer, 0, len);
         }

         return baos.toByteArray();
      } catch (IOException var4) {
         throw new IllegalArgumentException(var4);
      }
   }

   public static byte[] toByte(String string) {
      return string.getBytes();
   }

   public static byte[] loadResource(String path) {
      try {
         return getBytes(getResourceAsStream(path));
      } catch (IOException var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   public static byte[] compress(byte[] input) throws IOException {
      long size = (long)input.length;
      ByteArrayOutputStream outstream = new ByteArrayOutputStream();
      GZIPOutputStream out = new GZIPOutputStream(outstream);
      out.write(input);
      out.flush();
      outstream.flush();
      out.close();
      outstream.close();
      byte[] ret = outstream.toByteArray();
      LOG.debug("Compression of data from " + size + " bytes to " + ret.length + " bytes");
      return ret;
   }

   public static byte[] decompress(byte[] unSealmessage) throws IOException {
      long size = (long)unSealmessage.length;
      ByteArrayInputStream inputstream = new ByteArrayInputStream(unSealmessage);
      GZIPInputStream input = new GZIPInputStream(inputstream);
      ByteArrayOutputStream o = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];

      int i;
      while((i = input.read(buffer)) > 0) {
         o.write(buffer, 0, i);
      }

      o.flush();
      input.close();
      inputstream.close();
      o.close();
      byte[] ret = o.toByteArray();
      LOG.debug("Decompression of data from " + size + " bytes to " + ret.length + " bytes");
      return ret;
   }

   public static InputStream getResourceAsStream(String path) throws IOException {
      File f = new File(path);
      LOG.info("Loading " + f.getAbsolutePath() + " from file system");
      if (f.exists()) {
         LOG.info("Loading " + f.getAbsolutePath() + " from file system");
         return new FileInputStream(f);
      } else {
         LOG.info("Loading " + path + " from classpath");
         InputStream stream = IOUtils.class.getResourceAsStream(path);
         if (stream == null) {
            path = path.replace(" ", "%20");
            URL u = new URL(path);
            stream = u.openStream();
            if (stream == null) {
               throw new IOException("Invalid resource " + path);
            }
         }

         return stream;
      }
   }

   public static String getFileAsFullPath(String path) throws IOException {
      File f = new File(path);
      if (f.exists()) {
         LOG.info("Loading " + path + " from file system");
         return f.getAbsolutePath();
      } else {
         return null;
      }
   }

   public static String strConvert(byte[] message) {
      return message != null ? new String(message, StandardCharsets.UTF_8) : "";
   }

   public static List<String> getP12FileList(String path, String ssinRiziv) {
      if (path == null) {
         return Collections.emptyList();
      } else {
         List<String> fileList = new ArrayList();
         File dir = new File(path);
         if (dir.exists() && dir.isDirectory()) {
            FileFilter filter = new FileFilter() {
               public boolean accept(File pathname) {
                  return pathname.isFile();
               }
            };
            File[] files = dir.listFiles(filter);
            String olddate = null;
            String filenames = null;
            if (files == null) {
               return Collections.emptyList();
            } else {
               File[] var11 = files;
               int var10 = files.length;

               for(int var9 = 0; var9 < var10; ++var9) {
                  File f = var11[var9];
                  LOG.info("Keystore filename: " + f.getName());
                  if (f.getName().contains(ssinRiziv) && CertificateExtension.isSupported(f)) {
                     try {
                        CertificateExtension certificateExtension = CertificateExtension.lookupType(f);
                        filenames = certificateExtension.removeExtension(f.getName());
                        String[] filename = null;
                        if (f.getName().contains(" ")) {
                           filename = filenames.split(" ");
                        } else {
                           filename = filenames.split("_");
                        }

                        for(int i = 0; i < filename.length; ++i) {
                           LOG.info("Keystore filename part " + i + ":" + filename[i]);
                        }

                        String[] date = filename[filename.length - 1].split("-");

                        for(int i = 0; i < date.length; ++i) {
                           LOG.info("Keystore date part " + i + ":" + date[i]);
                        }

                        LOG.info("Keystore date.length: " + date.length);
                        LOG.info("Keystore date last part: " + date[date.length - 1]);
                        String lastDt = date[date.length - 1];
                        lastDt = lastDt.substring(0, 6);
                        LOG.info("Keystore lastDt: " + lastDt);
                        long newDate = Long.parseLong(date[0] + lastDt);
                        LOG.info("Keystore new date:" + newDate);
                        if (olddate != null && newDate > Long.parseLong(olddate)) {
                           if (fileList.size() > 0) {
                              fileList.remove(0);
                           }

                           fileList.add(f.getCanonicalPath());
                           olddate = String.valueOf(newDate);
                        } else if (olddate == null) {
                           fileList.add(f.getCanonicalPath());
                           olddate = String.valueOf(newDate);
                        } else {
                           fileList.add(f.getCanonicalPath());
                        }
                     } catch (IOException var18) {
                        LOG.error("IOException on getP12FileList()", var18);
                     }
                  }
               }

               return fileList;
            }
         } else {
            LOG.error("The directory " + path + " does not exist");
            return Collections.emptyList();
         }
      }
   }

   public static List<String> getConfigurationFileList(String path, String configName) {
      if (path == null) {
         return Collections.emptyList();
      } else {
         List<String> fileList = new ArrayList();
         File dir = new File(path);
         if (dir.exists() && dir.isDirectory()) {
            FileFilter filter = new FileFilter() {
               public boolean accept(File pathname) {
                  return pathname.isFile();
               }
            };
            File[] files = dir.listFiles(filter);
            String filenames = null;
            Integer oldTot = 0;
            if (files == null) {
               return Collections.emptyList();
            } else {
               File[] var11 = files;
               int var10 = files.length;

               for(int var9 = 0; var9 < var10; ++var9) {
                  File f = var11[var9];
                  LOG.info("filename: " + f.getName());
                  if (f.getName().contains(configName) && f.getName().endsWith(".xml")) {
                     filenames = f.getName();
                     String[] filename = null;
                     filename = filenames.split("_");
                     String version = null;

                     for(int i = 0; i < filename.length; ++i) {
                        LOG.info("Config filename part " + i + ":" + filename[i]);
                        if (filename[i].endsWith(".xml")) {
                           String fi = filename[i].replace(".xml", "");
                           if (!StringUtils.isEmpty(fi)) {
                              version = fi.replace("v", "");
                              Integer tot = Integer.valueOf(version);
                              if (tot > oldTot) {
                                 if (fileList.size() > 0) {
                                    fileList.remove(0);
                                 }

                                 fileList.add(f.getAbsolutePath());
                                 oldTot = tot;
                              }
                           }
                        }
                     }
                  }
               }

               return fileList;
            }
         } else {
            LOG.error("The directory " + path + " does not exist");
            return Collections.emptyList();
         }
      }
   }

   public static List<String> getP12OldFileList(String path, String ssinRiziv) {
      if (path == null) {
         return Collections.emptyList();
      } else {
         List<String> fileList = new ArrayList();
         File dir = new File(path);
         if (dir.exists() && dir.isDirectory()) {
            FileFilter filter = new FileFilter() {
               public boolean accept(File pathname) {
                  return pathname.isFile();
               }
            };
            File[] files = dir.listFiles(filter);
            String olddate = null;
            String filenames = null;
            if (files == null) {
               return Collections.emptyList();
            } else {
               for(int j = 0; j < files.length; ++j) {
                  File f = files[j];
                  LOG.info("Keystore filename: " + f.getName());
                  if (f.getName().contains(ssinRiziv) && f.getName().endsWith(".p12")) {
                     try {
                        filenames = f.getName();
                        String[] filename = null;
                        if (f.getName().contains(" ")) {
                           filename = filenames.split(" ");
                        } else {
                           filename = filenames.split("_");
                        }

                        for(int i = 0; i < filename.length; ++i) {
                           LOG.debug("Keystore filename part " + i + ":" + filename[i]);
                        }

                        String[] date = filename[filename.length - 1].split("-");

                        for(int i = 0; i < date.length; ++i) {
                           LOG.debug("Keystore date part " + i + ":" + date[i]);
                        }

                        LOG.debug("Keystore date.length: " + date.length);
                        LOG.debug("Keystore date last part: " + date[date.length - 1]);
                        String lastDt = date[date.length - 1];
                        lastDt = lastDt.substring(0, 6);
                        LOG.debug("Keystore lastDt: " + lastDt);
                        long newDate = Long.parseLong(date[0] + lastDt);
                        LOG.debug("Keystore new date:" + newDate);
                        if (olddate != null && newDate > Long.parseLong(olddate)) {
                           if (fileList.size() > 0) {
                              fileList.remove(0);
                           }

                           fileList.add(files[j - 1].getCanonicalPath());
                           olddate = String.valueOf(newDate);
                        } else if (olddate == null) {
                           fileList.add(f.getCanonicalPath());
                           olddate = String.valueOf(newDate);
                        } else {
                           fileList.add(f.getCanonicalPath());
                        }
                     } catch (IOException var15) {
                        LOG.error("IOException on getP12OldFileList()", var15);
                     }
                  }
               }

               return fileList;
            }
         } else {
            LOG.error("The directory " + path + " does not exist");
            return Collections.emptyList();
         }
      }
   }

   public static byte[] toByteArray(InputStream input) throws IOException {
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      copy(input, output);
      return output.toByteArray();
   }

   public static int copy(InputStream input, OutputStream output) throws IOException {
      long count = copyLarge(input, output);
      return count > 2147483647L ? -1 : (int)count;
   }

   public static long copyLarge(InputStream input, OutputStream output) throws IOException {
      return copyLarge(input, output, new byte[4096]);
   }

   public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
      long count = 0L;

      int n;
      for(boolean var5 = false; -1 != (n = input.read(buffer)); count += (long)n) {
         output.write(buffer, 0, n);
      }

      return count;
   }
}
