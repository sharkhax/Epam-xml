package com.drobot.xml.handler.impl;

import com.drobot.xml.entity.PapersType;
import com.drobot.xml.entity.Magazine;
import com.drobot.xml.handler.impl.AbstractPapersHandler;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;

import javax.xml.stream.XMLStreamReader;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MagazineHandler extends AbstractPapersHandler<Magazine> {

    public MagazineHandler() {
        super();
        withText = EnumSet.range(PapersType.TITLE, PapersType.GLOSS);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(PapersType.MAGAZINE.getValue())) {
            current = new Magazine();
            setObjectAttributes(current, attributes);
        } else {
            setCurrentType(qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PapersType.MAGAZINE.getValue())) {
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
                    Magazine.Chars chars = current.getChars();
                    chars.setColored(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                case VOLUME -> {
                    Magazine.Chars chars = current.getChars();
                    chars.setVolume(Integer.parseInt(value));
                    current.setChars(chars);
                }
                case INDEX -> {
                    Magazine.Chars chars = current.getChars();
                    chars.setIndex(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                case GLOSS -> {
                    Magazine.Chars chars = current.getChars();
                    chars.setGloss(Boolean.parseBoolean(value));
                    current.setChars(chars);
                }
                default -> throw new EnumConstantNotPresentException(PapersType.class, current.toString());
            }
        }
        currentType = null;
    }

    @Override
    public Set<Magazine> handle(Element root) {
        Set<Magazine> result = new HashSet<>();
        NodeList nodeList = root.getElementsByTagName(PapersType.MAGAZINE.getValue());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            Magazine magazine = new Magazine();
            setCommonFields(element, magazine);
            buildMagazine(element, magazine);
            result.add(magazine);
        }
        return result;
    }

    @Override
    public Magazine handle(XMLStreamReader reader) {
        return null;
    }

    private void buildMagazine(Element element, Magazine magazine) {
        NodeList charsList = element.getElementsByTagName(PapersType.CHARS.getValue());
        Element chars = (Element) charsList.item(0);
        String stringColored = getElementField(chars, PapersType.COLORED);
        String stringVolume = getElementField(chars, PapersType.VOLUME);
        String stringIndex = getElementField(chars, PapersType.INDEX);
        String stringGloss = getElementField(chars, PapersType.GLOSS);
        Magazine.Chars charsObject = magazine.getChars();
        charsObject.setColored(Boolean.parseBoolean(stringColored));
        charsObject.setVolume(Integer.parseInt(stringVolume));
        charsObject.setIndex(Boolean.parseBoolean(stringIndex));
        charsObject.setGloss(Boolean.parseBoolean(stringGloss));
        magazine.setChars(charsObject);
    }
}
