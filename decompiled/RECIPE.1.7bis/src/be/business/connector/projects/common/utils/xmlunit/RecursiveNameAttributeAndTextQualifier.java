package org.taktik.connector.business.recipeprojects.common.utils.xmlunit;

import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.ElementNameQualifier;
import org.custommonkey.xmlunit.ElementQualifier;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Compares all Element,Attributes and Text nodes in two pieces of XML. Allows elements of complex, deeply nested types that are returned in different
 * orders but have the same content to be recognized as comparable.
 *
 * @author EHP
 */
public class RecursiveNameAttributeAndTextQualifier implements ElementQualifier {

    private static final ElementNameAndAttributeQualifier NAME_ATTRIBUTE_QUALIFIER = new ElementNameAndAttributeQualifier();

    /**
     * Returns result of recursive comparison of all the nodes of a control and test element.
     */
    public boolean qualifyForComparison(Element currentControl, Element currentTest) {
        return compareNodes(currentControl, currentTest);
    }

    private boolean compareNodes(Node currentControl, Node currentTest) {
        try {

            // if they are elements, compare names and attributes of the two nodes
            if (!NAME_ATTRIBUTE_QUALIFIER.qualifyForComparison((Element) currentControl, (Element) currentTest)) {
                return false;
            }

            // Compare the control and test elements' children
            NodeList controlNodes = null;
            NodeList testNodes = null;

            // Check that both nodes have children and, if so, get lists of them
            if (currentControl.hasChildNodes() && currentTest.hasChildNodes()) {
                controlNodes = currentControl.getChildNodes();
                testNodes = currentTest.getChildNodes();
            } else if (currentControl.hasChildNodes() || currentTest.hasChildNodes()) {
               return false;
                // if both nodes are empty, they are comparable
            } else {
                return true;
            }

            // check that both node lists have the same length
            if (countNodesWithoutConsecutiveTextNodes(controlNodes) != countNodesWithoutConsecutiveTextNodes(testNodes)) {
                return false;
            }

            // Do checks of test and control nodes' children
            final int cNodes = controlNodes.getLength();
            final int tNodes = testNodes.getLength();

            int i, j;
            for (i = j = 0; i < cNodes && j < tNodes; i++, j++) {
                Node controlNode = controlNodes.item(i);
                Node testNode = testNodes.item(j);

                // check if both node are same type
                if (controlNode.getNodeType() != testNode.getNodeType()) {
                    return false;
                }
                // compare text nodes
                if (controlNode.getNodeType() == Node.TEXT_NODE) {
                    // compare concatenated, trimmed text nodes
                    if (!catText(controlNode).equals(catText(testNode))) {
                        return false;
                    }

                    // swallow adjacent Text nodes
                    for (; i < cNodes - 1 && controlNodes.item(i + 1).getNodeType() == Node.TEXT_NODE; i++);
                    for (; j < tNodes - 1 && testNodes.item(j + 1).getNodeType() == Node.TEXT_NODE; j++);

                    // recursive check of current child control and test nodes'
                    // children

                } else if (!compareNodes((Element) controlNode, (Element) testNode)) {
                    return false;
                }
            }
            if (i != cNodes || j != tNodes) {
                return false;
            }

            // All descendants of current control and test nodes are comparable
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Concatenates contiguous Text nodes and removes all leading and trailing whitespace.
     * 
     * @param textNode
     * @return
     */
    private static String catText(Node textNode) {
        StringBuffer text = new StringBuffer();
        Node next = textNode;

        do {
            if (next.getNodeValue() != null) {
                text.append(next.getNodeValue().trim());
                next = next.getNextSibling();
            }
        } while (next != null && next.getNodeType() == Node.TEXT_NODE);

        return text.toString();
    }

    /**
     * Calculates the number of Nodes that are either not Text nodes or are Text nodes whose previous sibling isn't a Text node as well.
     * I.e. consecutive Text nodes are counted as a single node.
     */
    private static int countNodesWithoutConsecutiveTextNodes(NodeList l) {
        int count = 0;
        boolean lastNodeWasText = false;
        final int length = l.getLength();
        for (int i = 0; i < length; i++) {
            Node n = l.item(i);
            if (!lastNodeWasText || n.getNodeType() != Node.TEXT_NODE) {
                count++;
            }
            lastNodeWasText = n.getNodeType() == Node.TEXT_NODE;
        }
        return count;
    }
}
