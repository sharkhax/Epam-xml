package com.drobot.xml.builder;

import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.exception.ReaderException;
import com.drobot.xml.handler.impl.AbstractPapersHandler;
import com.drobot.xml.reader.SaxReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;

public class SaxBuilder<T extends AbstractPaper> extends AbstractPapersBuilder<T> {

    private static final Logger LOGGER = LogManager.getLogger(SaxBuilder.class);
    private final XMLReader reader;
    private final AbstractPapersHandler<T> handler;

    public SaxBuilder(AbstractPapersHandler<T> handler) throws ReaderException {
        SaxReader saxReader = new SaxReader();
        this.handler = handler;
        reader = saxReader.createParser(handler);
    }

    @Override
    public void buildPapersSet(String xmlPath) throws ReaderException {
        try {
            reader.parse(xmlPath);
            LOGGER.log(Level.INFO, "Objects have been built");
        } catch (SAXException | IOException e) {
            LOGGER.log(Level.FATAL, "Error while reading file", e);
            throw new ReaderException("Error while reading file", e);
        }
        papers = handler.getObjects();
    }
}
