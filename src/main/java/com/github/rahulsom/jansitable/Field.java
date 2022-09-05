package com.github.rahulsom.jansitable;

import org.fusesource.jansi.AnsiColors;
import org.fusesource.jansi.AnsiMode;
import org.fusesource.jansi.AnsiType;
import org.fusesource.jansi.io.AnsiOutputStream;
import org.fusesource.jansi.io.AnsiProcessor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Field {
	String content;
	/**
	 * Knowing the print width of a string is nearly impossible. If you want to override the
	 * value of String.length(), please do that here.
	 */
	int width;

	public Field(String content, int width) {
		this.content = content;
		this.width = width;
	}

	public Field(String content) {
		this(content, plain(content).length());
	}

  static String plain(String input) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    AnsiOutputStream out = new AnsiOutputStream(
        baos, () -> 200, AnsiMode.Strip, new AnsiProcessor(baos),
        AnsiType.VirtualTerminal, AnsiColors.Colors16, StandardCharsets.UTF_8,
        () -> {}, () -> {}, true);
    try {
      out.write(input.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return baos.toString();
  }
}
