package be.business.connector.common;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class PrettyPrintUtil {
   public static void main(String[] args) throws Exception {
      String xmlString = " <r><e>d</e></r>";
      System.out.println(prettyPrint(xmlString));
   }

   public static final String prettyPrint(String xmlString) throws Exception {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new InputSource(new StringReader(xmlString)));
      Transformer tf = TransformerFactory.newInstance().newTransformer();
      tf.setOutputProperty("encoding", "UTF-8");
      tf.setOutputProperty("indent", "yes");
      Writer out = new StringWriter();
      tf.transform(new DOMSource(document), new StreamResult(out));
      return out.toString();
   }
}
