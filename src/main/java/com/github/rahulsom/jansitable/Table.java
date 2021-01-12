package com.github.rahulsom.jansitable;

import org.fusesource.jansi.Ansi;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static com.github.rahulsom.jansitable.Column.Alignment.RIGHT;
import static org.fusesource.jansi.Ansi.Color.BLACK;

public class Table {
    private final List<Column> columns;
    private final int padding;
    private final PrintStream out;

    Table(List<Column> columns, int padding, PrintStream out) {
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

    public Table printHeader() {
        return line(INTERSECTIONS[0]);
    }

    public Table printDivider() {
        return line(INTERSECTIONS[1]);
    }

    public Table printFooter() {
        return line(INTERSECTIONS[2]);
    }

    private static String repeat(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private Table line(String chars) {
        Ansi ansi = Ansi.ansi().fg(BLACK);
        for (int i = 0; i <= columns.size(); i++) {
            if (i == 0) {
                ansi.a(chars.charAt(0)).a(repeat(H_BORDER, columns.get(i).width + padding));
            } else if (i == columns.size()) {
                ansi.a(repeat(H_BORDER, padding)).a(chars.charAt(2));
            } else {
                ansi.a(repeat(H_BORDER, padding))
                        .a(chars.charAt(1))
                        .a(repeat(H_BORDER, columns.get(i).width + padding));
            }
        }
        out.println(ansi.reset().toString());
        return this;
    }

    public Table print(Object... data) {
        List<Field> list = new ArrayList<>();
        for (Object it : data) {
            list.add(it instanceof Field ? (Field) it : new Field(it.toString()));
        }
        Field[] fields = list.toArray(new Field[0]);
        return print(fields);
    }

    public Table print(Field... data) {
        assert data.length == columns.size();
        Ansi ansi = Ansi.ansi();
        for (int i = 0; i <= columns.size(); i++) {
            if (i < columns.size()) {
                ansi.fg(BLACK).a(V_BORDER).reset().a(repeat(' ', padding));
                Column column = columns.get(i);
                Field field = data[i];
                int cellPadding = column.width - field.width;
                if (column.alignment == RIGHT) {
                    ansi.a(repeat(' ', cellPadding)).a(field.content);
                } else {
                    ansi.a(field.content).a(repeat(' ', cellPadding));
                }
                ansi.a(repeat(' ', padding));
            } else {
                ansi.a(Ansi.ansi().fg(BLACK).a(V_BORDER).reset());
            }
        }
        out.println(ansi);
        return this;
    }

}
