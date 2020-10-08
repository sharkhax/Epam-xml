package com.drobot.xml.handler.impl;

import com.drobot.xml.entity.PapersType;
import com.drobot.xml.entity.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PaperHandler extends AbstractPapersHandler<Paper> {

    private static final Logger LOGGER = LogManager.getLogger(PaperHandler.class);

    public PaperHandler() {
        super();
        withText = EnumSet.range(PapersType.TITLE, PapersType.INDEX);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(PapersType.PAPER.getValue())) {
            current = new Paper();
            setObjectAttributes(current, attributes);
        } else {
            setCurrentType(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PapersType.PAPER.getValue())) {
            objects.add(current);
            current = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        if (!setObjectValueIfPresent(value) && currentType != null && current != null) {
            switch (currentType) {
                case COLORED -> {
                    Paper.Chars chars = current.getChars();
                    chars.setColored(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                case VOLUME -> {
                    Paper.Chars chars = current.getChars();
                    chars.setVolume(Integer.parseInt(value));
                    current.setChars(chars);
                } case INDEX -> {
                    Paper.Chars chars = current.getChars();
                    chars.setIndex(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                default -> throw new EnumConstantNotPresentException(PapersType.class, currentType.toString());
            }
        }
        currentType = null;
    }

    @Override
    public Set<Paper> handle(Element root) {
        Set<Paper> result = new HashSet<>();
        NodeList nodeList = root.getElementsByTagName(PapersType.PAPER.getValue());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            Paper paper = new Paper();
            setCommonFields(element, paper);
            buildPaper(element, paper);
            result.add(paper);
        }
        LOGGER.log(Level.INFO, "Papers set have been created");
        return result;
    }

    @Override
    public Paper handle(XMLStreamReader reader) throws XMLStreamException {
        Paper result = null;
        String name = reader.getLocalName();
        if (name.equals(PapersType.PAPER.getValue())) {
            result = new Paper();
            buildObject(reader, result);
        }
        LOGGER.log(Level.DEBUG, "Paper has been created");
        return result;
    }

    @Override
    protected void buildChars(XMLStreamReader reader, Paper paper) throws XMLStreamException {
        Paper.Chars chars = paper.getChars();
        while (reader.hasNext()) {
            int type = reader.next();
            String value;
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    value = reader.getLocalName();
                    PapersType papersType = PapersType.valueOf(value.toUpperCase());
                    switch (papersType) {
                        case COLORED -> chars.setColored(Boolean.parseBoolean(getXMLText(reader)));
                        case VOLUME -> chars.setVolume(Integer.parseInt(getXMLText(reader)));
                        case INDEX -> chars.setIndex(Boolean.parseBoolean(getXMLText(reader)));
                        default -> throw new EnumConstantNotPresentException(PapersType.class, papersType.name());
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    value = reader.getLocalName();
                    if (value.equals(PapersType.CHARS.getValue())) {
                        paper.setChars(chars);
                        LOGGER.log(Level.DEBUG, "Paper's chars have been created");
                        return;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element");
    }

    private void buildPaper(Element element, Paper paper) {
        NodeList charsList = element.getElementsByTagName(PapersType.CHARS.getValue());
        Element chars = (Element) charsList.item(0);
        String stringColored = getElementField(chars, PapersType.COLORED);
        String stringVolume = getElementField(chars, PapersType.VOLUME);
        String stringIndex = getElementField(chars, PapersType.INDEX);
        Paper.Chars charsObject = paper.getChars();
        charsObject.setColored(Boolean.parseBoolean(stringColored));
        charsObject.setVolume(Integer.parseInt(stringVolume));
        charsObject.setIndex(Boolean.parseBoolean(stringIndex));
        paper.setChars(charsObject);
    }
}
