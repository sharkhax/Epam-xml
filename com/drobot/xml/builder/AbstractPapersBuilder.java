package com.drobot.xml.builder;

import com.drobot.xml.entity.AbstractPaper;
import com.drobot.xml.exception.ReaderException;

import java.util.Collections;
import java.util.Set;

public abstract class AbstractPapersBuilder<T extends AbstractPaper> {

    protected Set<T> papers;

    public Set<T> getPapers() {
        return Collections.unmodifiableSet(papers);
    }

    public abstract void buildPapersSet(String xmlPath) throws ReaderException;

    public void buildPapersSet() throws ReaderException {
        buildPapersSet(DefaultPath.NEWSPAPERS_XML);
    }
}
