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

public class Letter {

	// Variable that holds the letter
	// Tried to make it final but apparently you can't declare it without setting
	// and set the variable later so 🤷
	private char letter;
	private boolean found = false;

	// Pass args of class through to setters to lessen the code needed
	// when instantiating this class
	public Letter(char c) {
		setLetter(c);
	}

	// Getters and Setters
	public void setLetter(char c) {
		this.letter = c;
	}

	public char getLetter() {
		if (found)
			return this.letter;
		else
			return '_';
	}

	// foundLetter can return 5 options:
	// 0: letter != char
	// 1: letter == char (only returned the first time the letter is found)
	// 2: letter == char
	// 3: letter not found (only returned if no argument is passed)
	// 4: letter found (only returned if no argument is passed)
	// 5: unknown
	public int foundLetter(char c) {
		if (this.letter != c)
			return 0;
		else if (this.letter == c && !found) {
			this.found = true;
			return 1;
		} else if (this.letter == c)
			return 2;
		else if (c == '\0') {
			if (!found)
				return 3;
			else
				return 4;
		} else
			return 5;
	}

}
