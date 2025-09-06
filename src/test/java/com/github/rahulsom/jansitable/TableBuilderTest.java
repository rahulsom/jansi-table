package com.github.rahulsom.jansitable;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static com.github.rahulsom.jansitable.Column.Alignment.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TableBuilderTest {

    @Test
    void buildFancyTable() {
        var baos = new ByteArrayOutputStream();

        var table = new TableBuilder()
                .addColumn(30)
                .addColumn(10, RIGHT)
                .writeTo(baos)
                .build();

        table.printHeader()
                .print("Text", "Number")
                .printDivider()
                .print("Hello", 12)
                .printFooter();

        var expectation = """
            ┌────────────────────────────────┬────────────┐
            │ Text                           │     Number │
            ├────────────────────────────────┼────────────┤
            │ Hello                          │         12 │
            └────────────────────────────────┴────────────┘
            """.stripIndent();
        var actual = new String(baos.toByteArray());
        assertEquals(expectation, Field.plain(actual));
    }

    @Test
    void buildBasicTable() {
        var baos = new ByteArrayOutputStream();

        var table = new TableBuilder()
                .basic()
                .addColumn(30)
                .addColumn(10, RIGHT)
                .writeTo(baos)
                .build();

        table.printHeader()
                .print("Text", "Number")
                .printDivider()
                .print("Hello", 12)
                .printFooter();

        var expectation = """
            +--------------------------------+------------+
            | Text                           |     Number |
            +--------------------------------+------------+
            | Hello                          |         12 |
            +--------------------------------+------------+
            """.stripIndent();
        var actual = new String(baos.toByteArray());
        assertEquals(expectation, Field.plain(actual));
    }
}
