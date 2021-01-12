package com.github.rahulsom.jansitable


import org.fusesource.jansi.AnsiString
import spock.lang.Specification

import static com.github.rahulsom.jansitable.Column.Alignment.RIGHT

class TableBuilderSpec extends Specification {
    def "build simple table"() {
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
        new AnsiString(actual).plain == expectation

    }
}
