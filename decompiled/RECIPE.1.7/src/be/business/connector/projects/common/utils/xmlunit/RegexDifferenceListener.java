/*
 * Copyright (c) eHealth
 */
package org.taktik.connector.business.recipeprojects.common.utils.xmlunit;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;

import org.w3c.dom.Node;


/**
 * ignores certain elements when comparing xmls.
 * 
 * @author EH076
 * 
 */
public class RegexDifferenceListener implements DifferenceListener {

    private static final Logger LOG = Logger.getLogger(RegexDifferenceListener.class);

    public RegexDifferenceListener() {
        super();
    }

    @Override
    public int differenceFound(Difference difference) {

        final Node controlNode = difference.getControlNodeDetail().getNode();
        final Node testNode = difference.getTestNodeDetail().getNode();
        if (difference.getId() == DifferenceConstants.ATTR_VALUE_ID && isXSIType(controlNode) && isXSIType(testNode)) {
            if (getNameSpaceFromPrefix(controlNode).compareTo(getNameSpaceFromPrefix(testNode)) != 0) {
                return RETURN_ACCEPT_DIFFERENCE;
            }
            String withoutPrefixControl = getNameWithoutPrefix(controlNode);
            String withoutPrefixTest = getNameWithoutPrefix(testNode);

            if (withoutPrefixControl.compareTo(withoutPrefixTest) == 0) {
                return RETURN_IGNORE_DIFFERENCE_NODES_IDENTICAL;
            }
        }

        if (difference.getControlNodeDetail().getValue() != null //
            && difference.getControlNodeDetail().getValue().startsWith("${") //
            && difference.getControlNodeDetail().getValue().endsWith("}")) {
            return checkSpecialValue(difference);
        }

        return RETURN_ACCEPT_DIFFERENCE;
    }

    boolean isXSIType(org.w3c.dom.Node node) {
        return node.getNodeType() == Node.ATTRIBUTE_NODE && node.getLocalName().compareTo("type") == 0 && node.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance");
    }

    private String getNameSpaceFromPrefix(Node node) {
        final int beginIndex = node.getNodeValue().indexOf(":");
        if (beginIndex == -1) {
            return "";
        }
        return node.lookupNamespaceURI(node.getNodeValue().substring(0, beginIndex));
    }

    private String getNameWithoutPrefix(Node controlNode) {
        final int beginIndex = controlNode.getNodeValue().indexOf(":");
        if (beginIndex == -1) {
            return controlNode.getNodeValue();
        }
        return controlNode.getNodeValue().substring(beginIndex);
    }

    @Override
    public void skippedComparison(org.w3c.dom.Node node, org.w3c.dom.Node node1) {

    }

    /**
     * returns the return value as defined in superclass.
     */
    private int checkSpecialValue(Difference inDifference) {
        String testValue = inDifference.getControlNodeDetail().getValue();
        if (StringUtils.equals("${ignore}", testValue)) {
            LOG.debug("accept diff: " + inDifference.getControlNodeDetail().getValue() + " <-->" + inDifference.getTestNodeDetail().getValue());
            return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;

        } else if (testValue.trim().startsWith("${regex=") && testValue.trim().endsWith("}")) {
            String regex = inDifference.getControlNodeDetail().getValue();
            String[] regexArr = regex.split("=", 2);
            if (regexArr.length >= 2) {
                // zonder closing brackets
                boolean isCompliant = Pattern.matches(regexArr[1].substring(0, regexArr[1].length() - 1), inDifference.getTestNodeDetail().getValue());
                LOG.debug("accept diff: Pattern.matches(" + regexArr[1] + ", " + inDifference.getTestNodeDetail().getValue() + ") = " + isCompliant);
                if (isCompliant) {
                    return RETURN_IGNORE_DIFFERENCE_NODES_SIMILAR;
                }
            }
        }
        return RETURN_ACCEPT_DIFFERENCE;
    }
}
