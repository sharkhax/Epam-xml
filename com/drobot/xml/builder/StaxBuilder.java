package com.drobot.xml.builder;

import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.exception.ReaderException;
import com.drobot.xml.handler.StaxHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

public class StaxBuilder<T extends AbstractPaper> extends AbstractPapersBuilder<T> {

    private static final Logger LOGGER = LogManager.getLogger(StaxBuilder.class);
    private final StaxHandler<T> handler;

    public StaxBuilder(StaxHandler<T> handler) {
        papers = new HashSet<>();
        this.handler = handler;
    }

    @Override
    public void buildPapersSet(String xmlPath) throws ReaderException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(xmlPath))) {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    T t = handler.handle(reader);
                    if (t != null) {
                        papers.add(t);
                    }
                }
            }
            LOGGER.log(Level.INFO, "Objects have been built");
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error while reading file");
            throw new ReaderException("Error while reading file", e);
        } catch (XMLStreamException e) {
            LOGGER.log(Level.FATAL, "Error while creating reader", e);
            throw new ReaderException("Error while creating reader", e);
        }
    }
}
