package com.github.rahulsom.jansitable;

import org.fusesource.jansi.AnsiOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
    this(content, chew(content).length());
	}

  private static CharSequence chew(final CharSequence str) {
    assert str != null;

    ByteArrayOutputStream buff = new ByteArrayOutputStream();
    AnsiOutputStream out = new AnsiOutputStream(buff);

    try {
      out.write(str.toString().getBytes());
      out.flush();
      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return buff.toString();
  }

}
