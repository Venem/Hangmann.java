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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	private static String word = "chocolate";
	private static int lettersFound;
	// Holds .length() value of current word (not constant because the game has multiple rounds)
	// More efficient than running .length() over and over
	private static int WORD_LENGTH;

	private static Scanner inputScanner = new Scanner(System.in);

	public static HangmanStage hangmanStage;
	public static Letter wordArr[];

	public static void main(String[] args) {

		while (true) {

			initialise();

			while (lettersFound < WORD_LENGTH) {
				printKnownLetters();
				char c = inputScanner.next().charAt(0);
				fillLetters(c);
				System.out.println(hangmanStage.getStage());
				System.out.println(hangmanStage.getWrongLetters());
			}

		}

	}

	public static void initialise() {
		lettersFound = 0;
		hangmanStage = new HangmanStage();

		WORD_LENGTH = word.length();

		// Create an array to contain all objects of the Letter class;
		wordArr = new Letter[WORD_LENGTH];
		// Convert the chosen word from a String to a char array (for some reason
		// there is a difference - maybe this is a convention I am too C to understand)
		// Additional info: this won't be needed when reading words from file
		char wordAsCharArray[] = word.toCharArray();

		// Fill the Letter array with objects and pass through the individual
		// character to the setter - Letter(char c) calls Letter.setLetter(char c)
		for (int i=0; i<WORD_LENGTH; i++) {
			wordArr[i] = new Letter(wordAsCharArray[i]);
		}
	}

	public static void fillLetters(char c) {
		boolean foundALetter = false;
		for (int i=0; i<WORD_LENGTH; i++) {
			// foundLetter can return 5 options:
			// 0: letter != char
			// 1: letter == char (only returned the first time the letter is found)
			// 2: letter == char
			// 3: letter not found (only returned if no argument is passed)
			// 4: letter found (only returned if no argument is passed)
			// 5: unknown
			switch (wordArr[i].foundLetter(c)) {
				case 1:
					lettersFound++;
					foundALetter = true;
					break;
				case 2:
					foundALetter = true;
					break;
			}
		}
		if (!foundALetter)
			hangmanStage.increment(c);
	}

	public static void printKnownLetters() {
		for (int k=0; k<WORD_LENGTH; k++) {
			System.out.print(wordArr[k].getLetter());
		}
		System.out.println(" ("+WORD_LENGTH+")");
	}

}
