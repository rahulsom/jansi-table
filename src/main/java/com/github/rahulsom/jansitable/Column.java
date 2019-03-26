package com.github.rahulsom.jansitable;

public class Column {
    static enum Alignment {
        LEFT, RIGHT
    }

    int width;
    Alignment alignment = Alignment.LEFT;

    public Column(int width, Alignment alignment) {
        this.width = width;
        this.alignment = alignment;
    }

    public Column(int width) {
        this.width = width;
    }
}
