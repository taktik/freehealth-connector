/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.business.connector.core.utils;

import javax.xml.namespace.NamespaceContext;
import javax.xml.XMLConstants;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Implements the NamespaceContext with the aid of a key-value map.
 */
public class MapNamespaceContext implements NamespaceContext {

    private Map<String, String> nsMappings;

    public MapNamespaceContext() {
        nsMappings = new TreeMap<>();
        nsMappings.put(XMLConstants.DEFAULT_NS_PREFIX, "http://www.ehealth.fgov.be/standards/kmehr/schema/v1");
        nsMappings.put(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
        nsMappings.put(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XMLNS_ATTRIBUTE_NS_URI);
    }


    public MapNamespaceContext(String... namespaceArray) {
        this();
        for (int i = 0; i < namespaceArray.length; i++) {
            getNsMappings().put("ns" + Integer.toString(i + 1), namespaceArray[i]);
        }
    }


    public MapNamespaceContext(Map<String, String> namespaceMap) {
        this();
        nsMappings = namespaceMap;
    }

    protected final Map<String, String> getNsMappings() {
        return nsMappings;
    }

    @Override
    public String getNamespaceURI(String prefix) {
        if (prefix == null)
            throw new IllegalArgumentException("prefix must be provided");
        return nsMappings.get(prefix);
    }

    @Override
    public String getPrefix(String namespaceURI) {
        if (namespaceURI == null)
            throw new IllegalArgumentException("namespaceURI must be provided");
        for (Map.Entry<String, String> entity : nsMappings.entrySet()) {
            if (entity.getValue().equals(namespaceURI)) return entity.getKey();
        }
        return null;
    }

    @Override
    public Iterator getPrefixes(String namespaceURI) {
        if (namespaceURI == null)
            throw new IllegalArgumentException("namespaceURI must be provided");
        List<String> prefixes = new LinkedList<>();
        for (Map.Entry<String, String> entity : nsMappings.entrySet()) {
            if (entity.getValue().equals(namespaceURI)) prefixes.add(entity.getKey());
        }
        return prefixes.iterator();
    }
}