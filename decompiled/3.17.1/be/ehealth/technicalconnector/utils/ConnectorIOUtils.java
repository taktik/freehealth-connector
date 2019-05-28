package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.enumeration.Charset;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import com.gc.iotools.stream.is.InputStreamFromOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.Deflater;
import java.util.zip.DeflaterInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectorIOUtils {
   private static final String BASE64_VALIDATOR_REGEX = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$";
   private static final Logger LOG = LoggerFactory.getLogger(ConnectorIOUtils.class);
   public static final String COMPRESSION_ALGO_DEFLATE = "deflate";
   public static final String COMPRESSION_ALGO_GZIP = "gz";
   private static CompressorStreamFactory factory = new CompressorStreamFactory();
   private static ArchiveStreamFactory asFactory = new ArchiveStreamFactory();

   private ConnectorIOUtils() {
      throw new UnsupportedOperationException();
   }

   public static byte[] getBytes(InputStream inputStream) throws TechnicalConnectorException {
      if (inputStream == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else {
         byte[] var1;
         try {
            var1 = IOUtils.toByteArray(inputStream);
         } catch (IOException var5) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
         } finally {
            closeQuietly((Object)inputStream);
         }

         return var1;
      }
   }

   public static byte[] toBytes(String content, Charset charsetName) throws TechnicalConnectorException {
      byte[] bytes = null;
      if (content == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else if (charsetName == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else {
         try {
            byte[] bytes = content.getBytes(charsetName.getName());
            return bytes;
         } catch (UnsupportedEncodingException var4) {
            LOG.error(var4.getClass().getSimpleName() + ": " + var4.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNSUPPORTED_ENCODING_EXCEPTION, var4, new Object[]{var4.getMessage()});
         }
      }
   }

   public static String toString(byte[] message, Charset charsetName) throws TechnicalConnectorException {
      Validate.notNull(message);
      Validate.notNull(charsetName);

      try {
         return new String(message, charsetName.getName());
      } catch (UnsupportedEncodingException var3) {
         LOG.error("Unable to convert input.", var3);
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.UNSUPPORTED_ENCODING_EXCEPTION, var3, new Object[]{var3.getMessage()});
      }
   }

   public static byte[] compress(byte[] input) throws TechnicalConnectorException {
      return compress(input, "gz");
   }

   /** @deprecated */
   @Deprecated
   public static byte[] compress(boolean noWrap, byte[] input) throws TechnicalConnectorException {
      return compress(input);
   }

   public static void compress(InputStream in, OutputStream out, String algo) throws TechnicalConnectorException {
      InputStream compressedInputStream = null;
      CompressorOutputStream gzippedOut = null;

      try {
         if ("deflate".equalsIgnoreCase(algo)) {
            compressedInputStream = new DeflaterInputStream(in, new Deflater(-1, true));
            IOUtils.copy(compressedInputStream, out);
         } else {
            gzippedOut = factory.createCompressorOutputStream(algo, out);
            IOUtils.copy(in, gzippedOut);
         }
      } catch (Exception var9) {
         LOG.error(var9.getClass().getSimpleName() + ": " + var9.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_COMPRESSION, var9, new Object[0]);
      } finally {
         closeQuietly(compressedInputStream, gzippedOut);
      }

   }

   public static byte[] compress(byte[] input, String algo) throws TechnicalConnectorException {
      if (!ArrayUtils.isEmpty(input) && !StringUtils.isEmpty(algo)) {
         ByteArrayOutputStream out = null;
         ByteArrayInputStream in = null;

         byte[] var4;
         try {
            in = new ByteArrayInputStream(input);
            out = new ByteArrayOutputStream();
            compress(in, out, algo);
            out.flush();
            var4 = out.toByteArray();
         } catch (IOException var8) {
            LOG.error(var8.getClass().getSimpleName() + ": " + var8.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_COMPRESSION, var8, new Object[0]);
         } finally {
            closeQuietly(in, out);
         }

         return var4;
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      }
   }

   public static void decompress(InputStream in, OutputStream out, boolean noWrap) throws TechnicalConnectorException {
      InflaterOutputStream decompressed = null;

      try {
         decompressed = new InflaterOutputStream(out, new Inflater(noWrap));
         IOUtils.copy(in, decompressed);
      } catch (Exception var8) {
         LOG.error(var8.getClass().getSimpleName() + ": " + var8.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_COMPRESSION, var8, new Object[0]);
      } finally {
         closeQuietly((Object)decompressed);
      }

   }

   public static byte[] decompress(byte[] input) throws TechnicalConnectorException {
      ByteArrayInputStream in = null;

      byte[] var2;
      try {
         in = new ByteArrayInputStream(input);
         var2 = getBytes(decompress((InputStream)in));
      } finally {
         closeQuietly((Object)in);
      }

      return var2;
   }

   public static InputStream decompress(InputStream input) throws TechnicalConnectorException {
      Validate.notNull(input);
      BufferedInputStream is = new BufferedInputStream(input);
      is.mark(1024);

      try {
         try {
            LOG.debug("Using createCompressorInputStream");
            return factory.createCompressorInputStream(is);
         } catch (Exception var6) {
            LOG.debug("[CompressionFactory]   " + var6.getClass().getSimpleName() + ": " + var6.getMessage());
            is.reset();

            try {
               LOG.debug("Using createArchiveInputStream");
               return asFactory.createArchiveInputStream(is);
            } catch (Exception var5) {
               LOG.debug("[ArchiveStreamFactory] " + var5.getClass().getSimpleName() + ": " + var5.getMessage());
               is.reset();

               try {
                  LOG.debug("Using deflater noWrap");
                  return deflater(is, true);
               } catch (Exception var4) {
                  LOG.debug("[Deflater noWrap] " + var4.getClass().getSimpleName() + ": " + var4.getMessage());
                  is.reset();

                  try {
                     LOG.debug("Using deflater wrap");
                     return deflater(is, false);
                  } catch (Exception var3) {
                     LOG.debug("[Deflater wrap] " + var3.getClass().getSimpleName() + ": " + var3.getMessage());
                  }
               }
            }
         }
      } catch (IOException var7) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_COMPRESSION, var7, new Object[]{var7.getMessage()});
      }

      throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_COMPRESSION, new Object[]{"Unsupported compression algorithm."});
   }

   private static InputStream deflater(final InputStream is, final boolean noWrap) throws Exception {
      InputStream result = new InputStreamFromOutputStream<Void>() {
         protected Void produce(OutputStream sink) throws Exception {
            ConnectorIOUtils.decompress(is, sink, noWrap);
            return null;
         }
      };
      FileOutputStream fos = null;

      ConnectorIOUtils.AutoDeleteFileInputStream var5;
      try {
         File temp = File.createTempFile("connector-io-", ".tmp");
         fos = new FileOutputStream(temp);
         IOUtils.copy(result, fos);
         var5 = new ConnectorIOUtils.AutoDeleteFileInputStream(temp);
      } finally {
         closeQuietly((Object)fos);
      }

      return var5;
   }

   /** @deprecated */
   @Deprecated
   public static byte[] decompress(boolean noWrap, byte[] input) throws TechnicalConnectorException {
      return decompress(input);
   }

   public static InputStream getResourceAsStream(String location) throws TechnicalConnectorException {
      return getResourceAsStream(location, true);
   }

   public static String getResourceAsString(String location) throws TechnicalConnectorException {
      return convertStreamToString(getResourceAsStream(location));
   }

   public static byte[] getResourceAsByteArray(String location) throws TechnicalConnectorException {
      return getBytes(getResourceAsStream(location));
   }

   public static InputStream getResourceAsStream(String location, boolean bootstrap) throws TechnicalConnectorException {
      if (location == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else {
         LOG.debug("Loading [" + location + "] as ResourceAsStream.");
         InputStream stream = ConnectorIOUtils.class.getResourceAsStream(location);
         if (stream == null) {
            File file = new File(location);
            if (!file.exists()) {
               try {
                  LOG.debug("Loading [" + location + "] as URL.");
                  if (bootstrap) {
                     ConfigFactory.getConfigValidator().getConfig();
                  }

                  URL resource = new URL(location);
                  return resource.openStream();
               } catch (Exception var5) {
                  LOG.error("Location [" + location + "] could not be retrieved as URL, classpath resource or file.");
                  throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var5, new Object[]{location});
               }
            }

            try {
               LOG.debug("Loading [" + location + "] as FileInputStream.");
               stream = new FileInputStream(file);
            } catch (FileNotFoundException var6) {
               LOG.error(var6.getClass().getSimpleName() + ": " + var6.getMessage());
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var6, new Object[]{location});
            }
         }

         return (InputStream)stream;
      }
   }

   public static File getResourceAsFile(String location) throws TechnicalConnectorException {
      InputStream in = null;
      FileOutputStream out = null;

      File var4;
      try {
         File tempFile = File.createTempFile("connector-io", ".tmp");
         tempFile.deleteOnExit();
         out = new FileOutputStream(tempFile);
         in = getResourceAsStream(location);
         IOUtils.copy(in, out);
         var4 = tempFile;
      } catch (IOException var8) {
         LOG.error(var8.getClass().getSimpleName() + ": " + var8.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var8, new Object[]{location});
      } finally {
         closeQuietly(in, out);
      }

      return var4;
   }

   public static String getResourceFilePath(String location) throws TechnicalConnectorException {
      if (location == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else {
         String filePath = null;
         LOG.debug("Loading " + location + " as ResourceAsString");
         InputStream stream = null;

         try {
            stream = ConnectorIOUtils.class.getResourceAsStream(location);
            if (stream != null) {
               LOG.debug("Location found as Resource.");
               filePath = ConnectorIOUtils.class.getResource(location).getFile();
            } else {
               File file = new File(location);
               if (!file.exists()) {
                  try {
                     URL resource = new URL(location);
                     filePath = resource.getFile();
                     LOG.debug("Location found as URL.");
                  } catch (MalformedURLException var8) {
                     LOG.error("location [" + location + "] could not be retrieved as URL, classpath resource or file.");
                     throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, var8, new Object[]{"location ]" + location + "[ errorMessage :" + var8.getMessage()});
                  }
               } else {
                  LOG.debug("Location found as File.");
                  filePath = location;
               }
            }
         } finally {
            closeQuietly((Object)stream);
         }

         return filePath;
      }
   }

   public static String convertStreamToString(InputStream is) throws TechnicalConnectorException {
      if (is == null) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[0]);
      } else {
         String result;
         try {
            result = IOUtils.toString(is, Charset.UTF_8.getName());
         } catch (IOException var6) {
            LOG.error(var6.getClass().getSimpleName() + ": " + var6.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, var6, new Object[]{var6.getMessage()});
         } finally {
            closeQuietly((Object)is);
         }

         return result;
      }
   }

   public static void closeQuietly(Object closeable) {
      try {
         if (closeable != null) {
            Method closeMethod = closeable.getClass().getMethod("close");
            closeMethod.invoke(closeable);
         }
      } catch (SecurityException var2) {
      } catch (NoSuchMethodException var3) {
      } catch (IllegalArgumentException var4) {
      } catch (IllegalAccessException var5) {
      } catch (InvocationTargetException var6) {
      }

   }

   public static void closeQuietly(Object... closeables) {
      Object[] arr$ = closeables;
      int len$ = closeables.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Object closeable = arr$[i$];
         closeQuietly(closeable);
      }

   }

   /** @deprecated */
   @Deprecated
   public static byte[] base64Decode(byte[] input) throws TechnicalConnectorException {
      return base64Decode(input, false);
   }

   public static byte[] base64Decode(byte[] input, boolean recursive) throws TechnicalConnectorException {
      byte[] result = ArrayUtils.clone(input);
      String content = toString(result, Charset.UTF_8);
      if (content.matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$")) {
         result = Base64.decode(result);
         if (recursive) {
            result = base64Decode(result, recursive);
         }
      }

      return result;
   }

   public static File createTempFile(String name) throws TechnicalConnectorException {
      if (name != null && !name.isEmpty()) {
         String tempDirectory = System.getProperty("java.io.tmpdir");
         if (tempDirectory != null && !tempDirectory.isEmpty()) {
            File tempFile = new File(tempDirectory, name);

            try {
               tempFile.createNewFile();
               return tempFile;
            } catch (IOException var4) {
               LOG.error("IOException while creating temporary file {0}", tempFile.getPath());
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_IOEXCEPTION, new Object[]{var4.getMessage(), var4, "creating temporary file" + tempFile.getPath()});
            }
         } else {
            LOG.error("The property 'java.io.tmpdir' not found in the system properties");
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"The property 'java.io.tmpdir' not found in the system properties"});
         }
      } else {
         LOG.error("The name given for the tempFile is empty");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"The name given for the tempFile is empty"});
      }
   }

   public static String getTempFileLocation(String name) throws TechnicalConnectorException {
      if (name != null && !name.isEmpty()) {
         String tempDirectory = System.getProperty("java.io.tmpdir");
         if (tempDirectory != null && !tempDirectory.isEmpty()) {
            File tempFile = new File(tempDirectory, name);
            return tempFile.getPath();
         } else {
            LOG.error("The property 'java.io.tmpdir' not found in the system properties");
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, new Object[]{"The property 'java.io.tmpdir' not found in the system properties"});
         }
      } else {
         LOG.error("The name given for the tempFile is empty");
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_INPUT_PARAMETER_NULL, new Object[]{"The name given for the tempFile is empty"});
      }
   }

   private static class AutoDeleteFileInputStream extends FileInputStream {
      private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorIOUtils.AutoDeleteFileInputStream.class);
      private File file;
      private boolean isClosed;
      private boolean isDeleted;

      public AutoDeleteFileInputStream(File file) throws FileNotFoundException {
         super(file);
         this.file = file;
         this.file.deleteOnExit();
      }

      public void close() {
         if (this.isClosed) {
            LOGGER.debug("stream already closed");
         } else {
            LOGGER.debug("closing stream :{}" + this.file);
            this.isClosed = true;

            try {
               super.close();
               this.isDeleted = this.file.delete();
            } catch (IOException var2) {
               LOGGER.warn("Failed to close stream: {}", this, var2);
            } catch (RuntimeException var3) {
               LOGGER.warn("Failed to delete underlying file: {}", this.file, var3);
            }

            LOGGER.debug("close()- file [{}] deleted: {}", this.file, this.isDeleted);
         }
      }
   }
}
