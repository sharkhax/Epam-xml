package com.drobot.xml.builder;

import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.entity.PapersType;
import com.drobot.xml.exception.ReaderException;
import com.drobot.xml.handler.DomHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.HashSet;

public class DomBuilder<T extends AbstractPaper> extends AbstractPapersBuilder<T> {

    private static final Logger LOGGER = LogManager.getLogger(DomBuilder.class);
    private DocumentBuilder documentBuilder;
    private final DomHandler<T> handler;

    public DomBuilder(DomHandler<T> handler) {
        papers = new HashSet<>();
        this.handler = handler;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.FATAL, "Parser config error", e);
        }
    }

    @Override
    public void buildPapersSet(String xmlPath) throws ReaderException {
        Document document = null;
        try {
            document = documentBuilder.parse(xmlPath);
            Element root = document.getDocumentElement();
            papers = handler.handle(root);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error while reading the file", e);
            throw new ReaderException("Error while reading the file", e);
        } catch (SAXException e) {
            LOGGER.log(Level.FATAL, "Error while parsing the file", e);
            throw new ReaderException("Error while parsing the file", e);
        }
    }
}
