package com.vitrina.service;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author dn200978lak
 * @version 3.0
 */
public class ServiceParserAnalize extends DefaultHandler {

    private int totalCount = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /**
         * General-Tag Iissues'
         */
        if (qName.equals("issues")) {
            totalCount = Integer.parseInt(attributes.getValue("total_count"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    public int getTotalCount(){
        return totalCount;
    }
}
