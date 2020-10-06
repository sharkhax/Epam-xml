package com.drobot.xml.handler;

import com.drobot.xml.entity.AbstractPaper;
import org.w3c.dom.Element;

import java.util.Set;

public interface DomHandler<T extends AbstractPaper> {
    Set<T> handle(Element root);
}
