/*
    Hangman.java - a simple hangman game written in Java
    Copyright (C) 2022  Vlad M.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package com.github.venem;

public class HangmanStage {

	public static final char[] ALPHABET = {
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
	};

	private final String[] HANG_STAGES = {
			""
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "‍\n",
			""
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "\n"
			+ "___\n",
			""
			+ "\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |   |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |   |\n"
			+ " |   O\n"
			+ " |\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |   |\n"
			+ " |   O\n"
			+ " |  ¯¯¯\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |   |\n"
			+ " |   O\n"
			+ " |  ¯|¯\n"
			+ " |\n"
			+ " |\n"
			+ "___\n",
			""
			+ " _____\n"
			+ " |   |\n"
			+ " |   X\n"
			+ " |  ¯|¯\n"
			+ " |  / \\\n"
			+ " |\n"
			+ "___\n"
	};

	private final int TOTAL_STAGES = HANG_STAGES.length;

	private final int WRONG_LETTERS_COLUMN_WIDTH = 5;

	// current hanging stage
	private int stage = 0;
	// can't get more wrong letters than hanging stages so cap array
	private char[] wrongLetters = new char[TOTAL_STAGES];

	public void increment(char c) {
		boolean charFound = false;
		for (int i=0; i<wrongLetters.length; i++)
			if (c == this.wrongLetters[i])
				charFound = true;
		if (!charFound) {
			this.wrongLetters[this.stage] = c;
			stage++;
		}
	}

	public String getStage() {
		return HANG_STAGES[stage];
	}

	public String getWrongLetters() {
		String currentLine = "";
		for (int i=0; i<ALPHABET.length; i++) {
			boolean foundALetter = false;
			for (int j=0; j<wrongLetters.length; j++)
				if (ALPHABET[i] == wrongLetters[j])
					foundALetter = true;
			if (foundALetter)
				currentLine = currentLine + ALPHABET[i] + " ";
			else
				currentLine = currentLine + "_ ";

			if ((i + 1) % WRONG_LETTERS_COLUMN_WIDTH == 0)
				currentLine = currentLine + "\n";
		}
		return currentLine;
	}

}
