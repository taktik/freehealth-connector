package org.taktik.connector.business.mycarenetdomaincommons.validator;

public abstract class AbstractMyCarenetValidator {
   protected static String addPath(String currentPath, String newPathNode) {
      return currentPath + "->" + newPathNode;
   }
}
