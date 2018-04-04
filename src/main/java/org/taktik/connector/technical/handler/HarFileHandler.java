package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarFileHandler extends AbstractSOAPHandler {
   private static final String TIMINGS = "timings";
   private static final Logger LOG = LoggerFactory.getLogger(HarFileHandler.class);
   private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
   private static final String MESSAGE_ENDPOINT_ADDRESS = "javax.xml.ws.service.endpoint.address";
   private JsonObject harJson;
   private Long start;
   private Long recieved;
   private Long split;
   private String outputdir = ConfigFactory.getConfigValidator().getProperty("connector.output.dir", System.getProperty("java.io.tmpdir"));
   private static Properties applicationProps = new Properties();

   public boolean handleFault(SOAPMessageContext ctx) {
      Boolean outbound = (Boolean)ctx.get("javax.xml.ws.handler.message.outbound");
      if (outbound.booleanValue()) {
         return false;
      } else {
         this.handleMessage(ctx);
         return true;
      }
   }

   public boolean handleInbound(SOAPMessageContext context) {
      this.setHandler();
      SOAPMessage msg = context.getMessage();

      try {
         String soapenv = this.getEnvelope(msg);
         JsonObject response = new JsonObject();
         response.addProperty("status", Integer.valueOf(200));
         response.addProperty("statusText", "OK");
         response.addProperty("httpVersion", "HTTP/1.1");
         response.add("headers", this.handleHeaders(msg.getMimeHeaders()));
         response.add("cookies", new JsonArray());
         JsonObject content = new JsonObject();
         content.addProperty("size", soapenv.getBytes().length);
         response.addProperty("headersSize", Integer.valueOf(-1));
         response.addProperty("bodySize", Integer.valueOf(-1));
         response.addProperty("redirectURL", "");
         content.addProperty("mimeType", "text/xml; charset=utf-8");
         if (msg.getMimeHeaders() != null) {
            String[] header = msg.getMimeHeaders().getHeader("Content-Type");
            if (header != null && header.length > 0) {
               content.addProperty("mimeType", header[0]);
            }
         }

         content.addProperty("text", soapenv);
         response.add("content", content);
         this.getEntry().add("response", response);
         this.getEntry().get("timings").getAsJsonObject().addProperty("wait", this.recieved.longValue() - this.split.longValue());
         long end = System.currentTimeMillis();
         this.getEntry().get("timings").getAsJsonObject().addProperty("receive", end - this.recieved.longValue());
         this.getEntry().addProperty("time", end - this.start.longValue());
         this.saveHar();
      } catch (Exception var8) {
         LOG.error(var8.getMessage(), var8);
      }

      return true;
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      this.setHandler();
      SOAPMessage msg = context.getMessage();

      try {
         JsonObject request = new JsonObject();
         request.addProperty("method", "POST");
         request.addProperty("url", context.get("javax.xml.ws.service.endpoint.address").toString());
         request.addProperty("httpVersion", "HTTP/1.1");
         request.add("headers", this.handleHeaders(msg.getMimeHeaders()));
         request.add("queryString", new JsonArray());
         request.add("cookies", new JsonArray());
         request.addProperty("headersSize", Integer.valueOf(-1));
         request.add("postData", this.getPostData(msg));
         request.addProperty("time", "1");
         request.addProperty("bodySize", Integer.valueOf(-1));
         this.split = System.currentTimeMillis();
         this.getEntry().get("timings").getAsJsonObject().addProperty("send", this.split.longValue() - this.start.longValue());
         this.getEntry().add("request", request);
      } catch (Exception var4) {
         LOG.error(var4.getMessage(), var4);
      }

      return true;
   }

   private void saveHar() throws IOException, TechnicalConnectorException {
      String fileName = IdGeneratorFactory.getIdGenerator("uuid").generateId() + ".har";
      File file = new File(this.outputdir, fileName);
      LOG.info("Writing har file on location: {}", file.getPath());
      Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
      gson.toJson(this.harJson, new JsonWriter(new FileWriter(file)));
   }

   private JsonObject getPostData(SOAPMessage msg) throws SOAPException, IOException {
      JsonObject postData = new JsonObject();
      postData.addProperty("mimeType", "multipart/form-data");
      postData.add("params", new JsonArray());
      postData.addProperty("text", this.getEnvelope(msg));
      return postData;
   }

   private String getEnvelope(SOAPMessage message) throws SOAPException, IOException {
      ByteArrayOutputStream stream = new ByteArrayOutputStream();

      String var3;
      try {
         message.writeTo(stream);
         if (stream.size() < 1232896) {
            var3 = stream.toString(Charset.UTF_8.getName());
            return var3;
         }

         var3 = "message to large to log";
      } finally {
         ConnectorIOUtils.closeQuietly((Object)stream);
      }

      return var3;
   }

   private JsonArray handleHeaders(MimeHeaders headers) throws IOException {
      JsonArray response = new JsonArray();
      if (headers != null) {
         Iterator headersIterator = headers.getAllHeaders();

         while(headersIterator.hasNext()) {
            MimeHeader mimheader = (MimeHeader)headersIterator.next();
            JsonObject header = new JsonObject();
            header.addProperty("name", mimheader.getName());
            header.addProperty("value", mimheader.getValue());
            response.add(header);
         }
      }

      return response;
   }

   private void prepareHarFile() {
      LOG.info("Start creating har file");
      JsonObject creator = new JsonObject();
      creator.addProperty("name", applicationProps.getProperty("application.name", "UNKOWN"));
      creator.addProperty("version", applicationProps.getProperty("application.version", "UNKOWN"));
      JsonArray entries = new JsonArray();
      JsonObject entry = new JsonObject();
      entry.addProperty("startedDateTime", this.dateFormatter.format(new Date()));
      entry.add("cache", new JsonArray());
      entry.add("timings", new JsonObject());
      entries.add(entry);
      JsonObject log = new JsonObject();
      log.addProperty("version", "1.2");
      log.add("creator", creator);
      log.add("entries", entries);
      this.harJson = new JsonObject();
      this.harJson.add("log", log);
   }

   private JsonObject getEntry() {
      JsonObject log = (JsonObject)this.harJson.get("log");
      JsonArray entries = (JsonArray)log.get("entries");
      return (JsonObject)entries.get(0);
   }

   private void setHandler() {
      if (this.start == null) {
         this.start = System.currentTimeMillis();
      } else {
         this.recieved = System.currentTimeMillis();
      }

      if (this.harJson == null) {
         this.prepareHarFile();
      }

   }

   static {
      InputStream is = null;

      try {
         is = ConnectorIOUtils.getResourceAsStream("/application.properties");
         applicationProps.load(is);
      } catch (TechnicalConnectorException var6) {
         LOG.error(var6.getMessage(), var6);
      } catch (IOException var7) {
         LOG.error(var7.getMessage(), var7);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }
}
