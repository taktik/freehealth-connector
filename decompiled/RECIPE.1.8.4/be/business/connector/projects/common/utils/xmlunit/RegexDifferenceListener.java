package be.business.connector.projects.common.utils.xmlunit;

import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public class RegexDifferenceListener implements DifferenceListener {
   private static final Logger LOG = Logger.getLogger(RegexDifferenceListener.class);

   public int differenceFound(Difference difference) {
      Node controlNode = difference.getControlNodeDetail().getNode();
      Node testNode = difference.getTestNodeDetail().getNode();
      if (difference.getId() == 3 && this.isXSIType(controlNode) && this.isXSIType(testNode)) {
         if (this.getNameSpaceFromPrefix(controlNode).compareTo(this.getNameSpaceFromPrefix(testNode)) != 0) {
            return 0;
         }

         String withoutPrefixControl = this.getNameWithoutPrefix(controlNode);
         String withoutPrefixTest = this.getNameWithoutPrefix(testNode);
         if (withoutPrefixControl.compareTo(withoutPrefixTest) == 0) {
            return 1;
         }
      }

      return difference.getControlNodeDetail().getValue() != null && difference.getControlNodeDetail().getValue().startsWith("${") && difference.getControlNodeDetail().getValue().endsWith("}") ? this.checkSpecialValue(difference) : 0;
   }

   boolean isXSIType(Node node) {
      return node.getNodeType() == 2 && node.getLocalName().compareTo("type") == 0 && node.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance");
   }

   private String getNameSpaceFromPrefix(Node node) {
      int beginIndex = node.getNodeValue().indexOf(":");
      return beginIndex == -1 ? "" : node.lookupNamespaceURI(node.getNodeValue().substring(0, beginIndex));
   }

   private String getNameWithoutPrefix(Node controlNode) {
      int beginIndex = controlNode.getNodeValue().indexOf(":");
      return beginIndex == -1 ? controlNode.getNodeValue() : controlNode.getNodeValue().substring(beginIndex);
   }

   public void skippedComparison(Node node, Node node1) {
   }

   private int checkSpecialValue(Difference inDifference) {
      String testValue = inDifference.getControlNodeDetail().getValue();
      if (StringUtils.equals("${ignore}", testValue)) {
         LOG.debug("accept diff: " + inDifference.getControlNodeDetail().getValue() + " <-->" + inDifference.getTestNodeDetail().getValue());
         return 2;
      } else {
         if (testValue.trim().startsWith("${regex=") && testValue.trim().endsWith("}")) {
            String regex = inDifference.getControlNodeDetail().getValue();
            String[] regexArr = regex.split("=", 2);
            if (regexArr.length >= 2) {
               boolean isCompliant = Pattern.matches(regexArr[1].substring(0, regexArr[1].length() - 1), inDifference.getTestNodeDetail().getValue());
               LOG.debug("accept diff: Pattern.matches(" + regexArr[1] + ", " + inDifference.getTestNodeDetail().getValue() + ") = " + isCompliant);
               if (isCompliant) {
                  return 2;
               }
            }
         }

         return 0;
      }
   }
}
