package com.github.venem;

import java.util.Random;
import java.util.Scanner;

public class Hangmann {

	static int tries = 0;
	static boolean done = false;

	static char[] wrongLetters = new char[0];
	static char[] knownLetters = new char[0];

	static char[] wrongLettersCpy = new char[0];
	static char[] knownLettersCpy = new char[0];

	static char[] wordArray;

	public static void main(String[] args) {
		Random randInt = new Random();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		// Pick a word
		String word = HangmannWords.words[randInt.nextInt(HangmannWords.words.length)];
		// Dissect word into an array
		/*wordArray = new char[word.length()];
		for (int i=0; i<word.length(); i++) {
			wordArray[i] = word.charAt(i);
		}*/

//		for (char k: wordArray) {
//			System.out.print(k+" ");
//		}

		wordArray = word.toCharArray();

		int maxTries = 8;
		char guess;
		Hangmann.printMessage();
		Hangmann.printWrong();
		Hangmann.printKnown();

		while (tries < maxTries && !done) {
			done=true;
			System.out.print("Enter a letter: ");
			guess = input.next().charAt(0);
//			System.out.print(Hangmann.sortChars(guess));
			Hangmann.sortChars(guess);
			System.out.print("\n\n\n\n\n\n\n\n\n\n");
			Hangmann.printMessage();
			Hangmann.printWrong();
			Hangmann.printKnown();
//			for (char i: knownLetters) System.out.print(i);
		}
		if (done)
			System.out.print("YOU WIN!");
		else
			System.out.print("YOU DIED\nThe word was: " + word);
	}

	private static void printMessage() {
		System.out.print(""
			+ "__        __   _                            _____       _   _\n"
			+ "\\ \\      / /__| | ___ ___  _ __ ___   ___  |_   _|__   | | | | __ _ _ __   __ _ _ __ ___   __ _ _ __  _ __\n"
			+ " \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\   | |/ _ \\  | |_| |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\| '_ \\\n"
			+ "  \\ V  V /  __/ | (_| (_) | | | | | |  __/   | | (_) | |  _  | (_| | | | | (_| | | | | | | (_| | | | | | | |\n"
			+ "   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|   |_|\\___/  |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|_| |_|\n"
			+ "                                                                          |___/\n"
			+ "");
	}

	private static boolean sortChars(char c) {
		for (char i: wordArray) {
			if (i==c) {
				// Check to see if "c" is already in the knownLetters array
				for (char j: knownLetters) {
					if (j==c)
						return true;
				}
				knownLettersCpy = new char[knownLetters.length+1];
				for (int k=0; k<knownLetters.length; k++) {
					knownLettersCpy[k] = knownLetters[k];
				}
				knownLettersCpy[knownLettersCpy.length-1] = c;
				knownLetters = knownLettersCpy;

				return true;
			}
		}
		// Check to see if "c" is already in the wrongLetters array
		for (char l: wrongLetters) {
			if (l==c)
				return false;
		}
		tries++;
		wrongLettersCpy = new char[wrongLetters.length+1];
		for (int m=0; m<wrongLetters.length; m++) {
			wrongLettersCpy[m] = wrongLetters[m];
		}
		wrongLettersCpy[wrongLettersCpy.length-1] = c;
		wrongLetters = wrongLettersCpy;

		return false;
	}

	private static boolean isKnown(char c) {
		for (char i: knownLetters) {
			if (i==c)
				return true;
		}
		return false;
	}

	private static boolean isWrong(char c) {
		for (char i: wrongLetters) {
			if (i==c)
				return true;
		}
		return false;
	}

	private static void printKnown() {
		System.out.print("~~~~~~~~~~~~~~~\nWORD:\n");
		for (char i: wordArray) {
			if (isKnown(i)) {
				System.out.print(i);
			} else {
				System.out.print("_");
				done = false;
			}
		}
		System.out.print("\t("+wordArray.length+")\n");
	}

	private static void printWrong() {
		int counter=1;
		System.out.print("WRONG LETTERS:");
		System.out.print("\t"+HangmannWords.stages[tries].split("\n")[0]+"\n");
		for (char i: HangmannWords.alphabet) {
			if (isWrong(i)) {
				System.out.print(i+" ");
			} else {
				System.out.print("_ ");
			}
			if (counter%5==0)
				System.out.print("\t"+HangmannWords.stages[tries].split("\n")[counter/5]+"\n");
			counter++;
		}
		System.out.print("\t\t"+HangmannWords.stages[tries].split("\n")[counter/5+1]+"\n");
	}

}
