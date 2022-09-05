package com.github.rahulsom.jansitable


import spock.lang.Specification

import static com.github.rahulsom.jansitable.Column.Alignment.RIGHT

class TableBuilderSpec extends Specification {
    def "build fancy table"() {
        setup:
        def baos = new ByteArrayOutputStream()

        when:
        def table = new TableBuilder()
                .addColumn(30)
                .addColumn(10, RIGHT)
                .writeTo(baos)
                .build()

        table.printHeader()
                .print("Text", "Number")
                .printDivider()
                .print("Hello", 12)
                .printFooter()

        def expectation = '''\
            ┌────────────────────────────────┬────────────┐
            │ Text                           │     Number │
            ├────────────────────────────────┼────────────┤
            │ Hello                          │         12 │
            └────────────────────────────────┴────────────┘
            '''.stripIndent()
        def actual = new String(baos.toByteArray())
        then:
        Field.plain(actual) == expectation

    }
    def "build basic table"() {
        setup:
        def baos = new ByteArrayOutputStream()

        when:
        def table = new TableBuilder()
				.basic()
                .addColumn(30)
                .addColumn(10, RIGHT)
                .writeTo(baos)
                .build()

        table.printHeader()
                .print("Text", "Number")
                .printDivider()
                .print("Hello", 12)
                .printFooter()

        def expectation = '''\
            +--------------------------------+------------+
            | Text                           |     Number |
            +--------------------------------+------------+
            | Hello                          |         12 |
            +--------------------------------+------------+
            '''.stripIndent()
        def actual = new String(baos.toByteArray())
        then:
        Field.plain(actual) == expectation

    }
}
