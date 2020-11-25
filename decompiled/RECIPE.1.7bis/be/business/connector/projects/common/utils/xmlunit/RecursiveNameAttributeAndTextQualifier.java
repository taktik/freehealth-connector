package be.business.connector.projects.common.utils.xmlunit;

import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.ElementQualifier;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RecursiveNameAttributeAndTextQualifier implements ElementQualifier {
   private static final ElementNameAndAttributeQualifier NAME_ATTRIBUTE_QUALIFIER = new ElementNameAndAttributeQualifier();

   public boolean qualifyForComparison(Element currentControl, Element currentTest) {
      return this.compareNodes(currentControl, currentTest);
   }

   private boolean compareNodes(Node currentControl, Node currentTest) {
      try {
         if (!NAME_ATTRIBUTE_QUALIFIER.qualifyForComparison((Element)currentControl, (Element)currentTest)) {
            return false;
         } else {
            NodeList controlNodes = null;
            NodeList testNodes = null;
            if (currentControl.hasChildNodes() && currentTest.hasChildNodes()) {
               controlNodes = currentControl.getChildNodes();
               testNodes = currentTest.getChildNodes();
               if (countNodesWithoutConsecutiveTextNodes(controlNodes) != countNodesWithoutConsecutiveTextNodes(testNodes)) {
                  return false;
               } else {
                  int cNodes = controlNodes.getLength();
                  int tNodes = testNodes.getLength();
                  int j = 0;

                  int i;
                  for(i = 0; i < cNodes && j < tNodes; ++j) {
                     Node controlNode = controlNodes.item(i);
                     Node testNode = testNodes.item(j);
                     if (controlNode.getNodeType() != testNode.getNodeType()) {
                        return false;
                     }

                     if (controlNode.getNodeType() == 3) {
                        if (!catText(controlNode).equals(catText(testNode))) {
                           return false;
                        }

                        while(i < cNodes - 1 && controlNodes.item(i + 1).getNodeType() == 3) {
                           ++i;
                        }

                        while(j < tNodes - 1 && testNodes.item(j + 1).getNodeType() == 3) {
                           ++j;
                        }
                     } else if (!this.compareNodes((Element)controlNode, (Element)testNode)) {
                        return false;
                     }

                     ++i;
                  }

                  return i == cNodes && j == tNodes;
               }
            } else {
               return !currentControl.hasChildNodes() && !currentTest.hasChildNodes();
            }
         }
      } catch (Exception var11) {
         return false;
      }
   }

   private static String catText(Node textNode) {
      StringBuffer text = new StringBuffer();
      Node next = textNode;

      do {
         if (next.getNodeValue() != null) {
            text.append(next.getNodeValue().trim());
            next = next.getNextSibling();
         }
      } while(next != null && next.getNodeType() == 3);

      return text.toString();
   }

   private static int countNodesWithoutConsecutiveTextNodes(NodeList l) {
      int count = 0;
      boolean lastNodeWasText = false;
      int length = l.getLength();

      for(int i = 0; i < length; ++i) {
         Node n = l.item(i);
         if (!lastNodeWasText || n.getNodeType() != 3) {
            ++count;
         }

         lastNodeWasText = n.getNodeType() == 3;
      }

      return count;
   }
}
