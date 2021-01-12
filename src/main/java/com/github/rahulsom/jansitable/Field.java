package com.github.rahulsom.jansitable;

import org.fusesource.jansi.AnsiString;

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
		this(content, new AnsiString(content).length());
	}
}
