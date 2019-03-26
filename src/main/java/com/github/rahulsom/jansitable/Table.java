package com.github.rahulsom.jansitable;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiString;

import java.io.PrintStream;
import java.util.List;

import static com.github.rahulsom.jansitable.Column.Alignment.RIGHT;
import static org.fusesource.jansi.Ansi.Color.BLACK;

public class Table {
    private final List<Column> columns;
    private final int padding;
    private final PrintStream out;

    public Table(List<Column> columns, int padding, PrintStream out) {
        this.columns = columns;
        this.padding = padding;
        this.out = out;
    }

    private static final String[] INTERSECTIONS = {
            "┌┬┐",
            "├┼┤",
            "└┴┘"
    };
    private static final char H_BORDER = '─';
    private static final char V_BORDER = '│';

    Table printHeader() {
        return line(INTERSECTIONS[0]);
    }

    Table printDivider() {
        return line(INTERSECTIONS[1]);
    }

    Table printFooter() {
        return line(INTERSECTIONS[2]);
    }

    private Table line(String chars) {
        Ansi ansi = Ansi.ansi().fgBlack();
        for (int i = 0; i <= columns.size(); i++) {
            if (i == 0) {
                ansi.a(chars.charAt(0));
                for (int j = 0; j < columns.get(i).width + padding; j++) {
                    ansi.a(H_BORDER);
                }
            } else if (i == columns.size()) {
                for (int j = 0; j < padding; j++) {
                    ansi.a(H_BORDER);
                }
                ansi.a(chars.charAt(2));
            } else {
                for (int j = 0; j < padding; j++) {
                    ansi.a(H_BORDER);
                }
                ansi.a(chars.charAt(1));
                for (int j = 0; j < columns.get(i).width + padding; j++) {
                    ansi.a(H_BORDER);
                }
            }
        }
        out.println(ansi.toString());
        return this;
    }

    Table print(Object... data) {
        assert data.length == columns.size();
        Ansi ansi = Ansi.ansi();
        for (int i = 0; i <= columns.size(); i++) {
            if (i < columns.size()) {
                ansi.fg(BLACK).a(V_BORDER).reset();
                for (int j = 0; j < padding; j++) {
                    ansi.a(' ');
                }
                Column column = columns.get(i);
                String field = data[i].toString();
                AnsiString printable = new AnsiString(field);
                int cellPadding = column.width - printable.length();
                if (column.alignment == RIGHT) {
                    for (int j = 0; j < cellPadding; j++) {
                        ansi.a(' ');
                    }
                    ansi.a(field);
                } else {
                    ansi.a(field);
                    for (int j = 0; j < cellPadding; j++) {
                        ansi.a(' ');
                    }
                }
                for (int j = 0; j < padding; j++) {
                    ansi.a(' ');
                }
            } else {
                ansi.a(Ansi.ansi().fg(BLACK).a(V_BORDER).reset());
            }
        }
        out.println(ansi);
        return this;
    }

}
