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

	public static String word = "chocolate";

	public static void main(String[] args) {

		// Create an array to contain all objects of the Letter class;
		Letter wordArr[] = new Letter[word.length()];
		// Convert the chosen word from a String to a char array (for some reason
		// there is a difference - maybe this is a convention I am too C to understand)
		// Additional info: this won't be needed when reading words from file
		char wordAsCharArray[] = word.toCharArray();

		// Fill the Letter array with objects and pass through the individual
		// character to the setter - Letter(char c) calls Letter.setLetter(char c)
		for (int i=0; i<word.length(); i++) {
			wordArr[i] = new Letter(wordAsCharArray[i]);
		}

		// Testing to see if it actually works
		System.out.println(wordArr[8].getLetter());

	}

}
