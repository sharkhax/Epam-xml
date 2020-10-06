package com.drobot.xml.entity;

public enum PapersType {
    PAPERS,
    ID,
    DATE,
    PAPER,
    MAGAZINE,
    BOOKLET,
    TITLE,
    TYPE,
    MONTHLY,
    COLORED,
    VOLUME,
    INDEX,
    GLOSS,
    CHARS;

    public String getValue() {
        return toString().toLowerCase();
    }
}
