package be.fgov.ehealth.technicalconnector.signature.impl;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class DomUtils {
   private DomUtils() {
      throw new UnsupportedOperationException();
   }

   public static NodeList getMatchingChilds(Node node, String namespace, String localName) {
      NodeList childs = node.getChildNodes();
      ArrayNodeList result = new ArrayNodeList();

      int i;
      Node child;
      for(i = 0; i < childs.getLength(); ++i) {
         child = childs.item(i);
         if (child.getNodeType() == 1) {
            String ns = child.getNamespaceURI() == null ? "" : child.getNamespaceURI();
            String tag = child.getLocalName();
            if (tag.equals(localName) && ns.equals(namespace)) {
               result.addNode(child);
            }
         }
      }

      if (result.getLength() == 0) {
         for(i = 0; i < childs.getLength(); ++i) {
            child = childs.item(i);
            NodeList list = getMatchingChilds(child, namespace, localName);

            for(int j = 0; j < list.getLength(); ++j) {
               result.addNode(list.item(j));
            }
         }
      }

      return result;
   }

   public static class ArrayNodeList implements NodeList {
      private List<Node> result = new ArrayList();

      public ArrayNodeList() {
      }

      public Node item(int index) {
         return (Node)this.result.get(index);
      }

      public int getLength() {
         return this.result.size();
      }

      public void addNode(Node node) {
         this.result.add(node);
      }
   }
}
