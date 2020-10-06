package com.drobot.xml.handler.impl;

import com.drobot.xml.entity.PapersType;
import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.handler.DomHandler;
import com.drobot.xml.handler.StaxHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPapersHandler<T extends AbstractPaper> extends DefaultHandler
        implements DomHandler<T>, StaxHandler<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPapersHandler.class);
    protected Set<T> objects;
    protected T current = null;
    protected PapersType currentType = null;
    protected EnumSet<PapersType> withText = null;

    protected AbstractPapersHandler() {
        objects = new HashSet<>();
    }

    public Set<T> getObjects() {
        return objects;
    }

    @Override
    public void warning(SAXParseException e) {
        LOGGER.log(Level.WARN, e);
    }

    @Override
    public void error(SAXParseException e) {
        LOGGER.log(Level.ERROR, e);
    }

    @Override
    public void fatalError(SAXParseException e) {
        LOGGER.log(Level.FATAL, e);
    }

    @Override
    public void startDocument() {
        LOGGER.log(Level.DEBUG, "Document parsing is started");
    }

    @Override
    public void endDocument() {
        LOGGER.log(Level.DEBUG, "Document parsing is finished");
    }

    protected void setObjectAttributes(T object, Attributes attributes) {
        if (object != null && attributes != null) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String currentAttribute = attributes.getLocalName(i);
                if (currentAttribute.equals(PapersType.ID.getValue())) {
                    current.setId(attributes.getValue(i));
                } else {
                    current.setDate(attributes.getValue(i));
                }
            }
        } else {
            LOGGER.log(Level.ERROR, "Object or attributes are null");
        }
    }

    protected void setCurrentType(String qName) {
        if (qName != null) {
            PapersType temp = null;
            try {
                temp = PapersType.valueOf(qName.toUpperCase());
            } catch (IllegalArgumentException e) {
                LOGGER.log(Level.DEBUG, "Unnecessary tag");
            }
            if (temp != null && withText.contains(temp)) {
                currentType = temp;
            }
        } else {
            LOGGER.log(Level.ERROR, "qName is null");
        }
    }

    protected boolean setObjectValueIfPresent(String value) {
        boolean result = true;
        if (value != null && currentType != null && current != null) {
            switch (currentType) {
                case TITLE -> current.setTitle(value);
                case TYPE -> current.setType(AbstractPaper.Type.valueOf(value.toUpperCase()));
                case MONTHLY -> current.setMonthly(Boolean.parseBoolean(value));
                default -> result = false;
            }
        } else {
            result = false;
        }
        return result;
    }

    protected void setCommonFields(Element element, T t) {
        String id = element.getAttribute(PapersType.ID.getValue());
        t.setId(id);
        String date = element.getAttribute(PapersType.DATE.getValue());
        t.setDate(date);
        String title = getElementField(element, PapersType.TITLE);
        String type = getElementField(element, PapersType.TYPE);
        String stringMonthly = getElementField(element, PapersType.MONTHLY);
        t.setTitle(title);
        t.setType(AbstractPaper.Type.valueOf(type.toUpperCase()));
        t.setMonthly(Boolean.parseBoolean(stringMonthly));
    }

    protected String getElementField(Element element, PapersType fieldType) {
        String fieldName = fieldType.getValue();
        NodeList list = element.getElementsByTagName(fieldName);
        Node child = list.item(0);
        String field = child.getTextContent();
        return field;
    }
}
