package com.drobot.xml.reader;

import com.drobot.xml.exception.ReaderException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxReader {

    private static final Logger LOGGER = LogManager.getLogger(SaxReader.class);

    public XMLReader createParser(DefaultHandler handler) throws ReaderException {
        XMLReader reader;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setContentHandler(handler);
            LOGGER.log(Level.INFO, "SAX reader has been initialized");
        } catch (SAXException | ParserConfigurationException e) {
            LOGGER.log(Level.FATAL, "SAX parser error", e);
            throw new ReaderException("SAX parser error", e);
        }
        return reader;
    }
}
