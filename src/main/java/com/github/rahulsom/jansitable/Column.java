package com.github.rahulsom.jansitable;

/**
 * Represents a column in a table.
 *
 * @author Rahul Somasunderam
 */
public class Column {
    /**
     * The alignment of content in a column.
     */
    public enum Alignment {
        /**
         * Left aligned
         */
        LEFT,
        /**
         * Right aligned
         */
        RIGHT
    }

    int width;
    Alignment alignment = Alignment.LEFT;

    /**
     * Creates a column with a given width and alignment.
     *
     * @param width     The width of the column
     * @param alignment The alignment of the column
     */
    public Column(int width, Alignment alignment) {
        this.width = width;
        this.alignment = alignment;
    }

    /**
     * Creates a column with a given width and default alignment (LEFT).
     *
     * @param width The width of the column
     */
    public Column(int width) {
        this.width = width;
    }
}
