package com.drobot.xml.handler.impl;

import com.drobot.xml.entity.PapersType;
import com.drobot.xml.entity.Booklet;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import javax.xml.stream.XMLStreamReader;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BookletHandler extends AbstractPapersHandler<Booklet> {

    public BookletHandler() {
        super();
        withText = EnumSet.range(PapersType.TITLE, PapersType.VOLUME);
        withText.add(PapersType.GLOSS);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(PapersType.BOOKLET.getValue())) {
            current = new Booklet();
            setObjectAttributes(current, attributes);
        } else {
            setCurrentType(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PapersType.BOOKLET.getValue())) {
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
                    Booklet.Chars chars = current.getChars();
                    chars.setColored(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                case VOLUME -> {
                    Booklet.Chars chars = current.getChars();
                    chars.setVolume(Integer.parseInt(value));
                    current.setChars(chars);
                }
                case GLOSS -> {
                    Booklet.Chars chars = current.getChars();
                    chars.setGloss(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                default -> throw new EnumConstantNotPresentException(PapersType.class, currentType.toString());
            }
        }
        currentType = null;
    }

    @Override
    public Set<Booklet> handle(Element root) {
        Set<Booklet> result = new HashSet<>();
        NodeList nodeList = root.getElementsByTagName(PapersType.BOOKLET.getValue());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            Booklet booklet = new Booklet();
            setCommonFields(element, booklet);
            buildBooklet(element, booklet);
            result.add(booklet);
        }
        return result;
    }

    @Override
    public Booklet handle(XMLStreamReader reader) {
        Booklet result;
        String name = reader.getLocalName();

    }

    private void buildBooklet(Element element, Booklet booklet) {
        NodeList charsList = element.getElementsByTagName(PapersType.CHARS.getValue());
        Element chars = (Element) charsList.item(0);
        String stringColored = getElementField(chars, PapersType.COLORED);
        String stringVolume = getElementField(chars, PapersType.VOLUME);
        String stringGloss = getElementField(chars, PapersType.GLOSS);
        Booklet.Chars charsObject = booklet.getChars();
        charsObject.setColored(Boolean.parseBoolean(stringColored));
        charsObject.setVolume(Integer.parseInt(stringVolume));
        charsObject.setGloss(Boolean.parseBoolean(stringGloss));
        booklet.setChars(charsObject);
    }
}
