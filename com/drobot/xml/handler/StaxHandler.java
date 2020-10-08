package com.drobot.xml.handler;

import com.drobot.xml.entity.AbstractPaper;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public interface StaxHandler<T extends AbstractPaper> {
    T handle(XMLStreamReader reader) throws XMLStreamException;
}
