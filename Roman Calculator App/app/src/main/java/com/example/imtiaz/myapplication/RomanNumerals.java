package com.example.imtiaz.myapplication;

import java.util.ArrayList;

/*
   _____       ____                              ________                      ____                    
  / ___/____ _/ __/      ______ _____ _____     / ____/ /_  ____ _      ______/ / /_  __  _________  __
  \__ \/ __ `/ /_| | /| / / __ `/ __ `/ __ \   / /   / __ \/ __ \ | /| / / __  / __ \/ / / / ___/ / / /
 ___/ / /_/ / __/| |/ |/ / /_/ / /_/ / / / /  / /___/ / / / /_/ / |/ |/ / /_/ / / / / /_/ / /  / /_/ / 
/____/\__,_/_/   |__/|__/\__,_/\__,_/_/ /_/   \____/_/ /_/\____/|__/|__/\__,_/_/ /_/\__,_/_/   \__, /  
                                                                                              /____/   
@Safwaan Chowdhury
 */
public class RomanNumerals {
	private int value;
	private String roman;

	public RomanNumerals () {
    }

	/**
	 * 
	 * @param romanNum is used to be converted into a integer.
	 */
	public RomanNumerals(String romanNum) {
		if (!set(romanNum)) {
			throw new java.lang.RuntimeException("The roman numeral that was inputted is invalid.");
		}

	}

	/**
	 * 
	 * @param romanInt any integer between 1 and 4999.
	 */
	public RomanNumerals(int romanInt) {
		if (!set(romanInt)) {
			throw new java.lang.RuntimeException("The range of the integer must be between 1 and 4999.");
		}
	}

	/**
	 * Converting integers to numerals. The method StringBuilder() was used to
	 * combine strings produced by the method.
	 * 
	 * @param rnInt is the positive integer that is converted into a roman numeral.
	 * @return returns the roman numeral for rnInt.
	 */
	public static String convertToString(int rnInt) {

		StringBuilder sb = new StringBuilder();
		int times = 0;
		String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
		int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
		for (int i = ints.length - 1; i >= 0; i--) {
			times = rnInt / ints[i];
			rnInt %= ints[i];
			while (times > 0) {
				sb.append(romans[i]);
				times--;
			}
		}
		return sb.toString();
	}

	/**
	 * The method prints out a decimal that is equivalent to the roman numeral.
	 * 
	 * @param romanNumber is the roman numeral string that converts into a numeral.
	 */
	public static int convertToInt(String romanNumber) {
		int decimal = 0;
		int lastNumber = 0;
		String romanNumeral = romanNumber.toUpperCase();

		for (int x = romanNumeral.length() - 1; x >= 0; x--) {
			char convertToDecimal = romanNumeral.charAt(x);

			switch (convertToDecimal) {
			case 'M':
				decimal = processDecimal(1000, lastNumber, decimal);
				lastNumber = 1000;
				break;
			case 'D':
				decimal = processDecimal(500, lastNumber, decimal);
				lastNumber = 500;
				break;
			case 'C':
				decimal = processDecimal(100, lastNumber, decimal);
				lastNumber = 100;
				break;
			case 'L':
				decimal = processDecimal(50, lastNumber, decimal);
				lastNumber = 50;
				break;
			case 'X':
				decimal = processDecimal(10, lastNumber, decimal);
				lastNumber = 10;
				break;
			case 'V':
				decimal = processDecimal(5, lastNumber, decimal);
				lastNumber = 5;
				break;
			case 'I':
				decimal = processDecimal(1, lastNumber, decimal);
				lastNumber = 1;
				break;
			}
		}
		return decimal;
	}

	public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
		if (lastNumber > decimal) {
			return lastDecimal - decimal;
		} else {
			return lastDecimal + decimal;
		}
	}

	/**
	 * 
	 * @return integer value of inputed roman string.
	 */
	public int toInt() {
		return this.value;
	}

	/**
	 * 
	 * @return roman string of inputed roman string.
	 */
	public String toRoman() {
		return this.roman;
	}

	/**
	 * 
	 * @param romanNum is a the roman numeral that is used to test for invalid inputs.
	 * @return true if the romanNum is valid, false if the romanNum is invalid. 
	 */
	public boolean set(String romanNum) {
		this.value = convertToInt(romanNum);
		this.roman = romanNum;
		String validchar = "MDCLXVI";

		// tests for valid roman chars.
		boolean tf = true;
		for (int x = 0; x < romanNum.length(); x++) {
			for (int i = 0; i < validchar.length(); i++) {
				if (validchar.charAt(i) == romanNum.charAt(x)) {
					tf = true;
					break;
				} else
					tf = false;
			}
		}

		// tests for properly formatted roman chars.
		int romanint = this.value;
		String romanProperNum = convertToString(romanint);
		boolean tf1 = true;
		if (romanNum.equals(romanProperNum)) 
			tf1 = true;
		if (!romanNum.equals(romanProperNum))
			tf1 = false;

		boolean tf2 = true;
		if (romanint < 1 || romanint > 5000)
			tf2 = false;
		
		return tf && tf1 && tf2;
	}

	/**
	 * 
	 * @param romanInt is used to test whether or not it can be a roman integer.
	 * @return true if it can, false if it can't.
	 */
	public boolean set(int romanInt) {
		this.value = romanInt;
		this.roman = convertToString(romanInt);
		if (romanInt < 1 || romanInt > 4999)
			return false;
		else
			return true;
	}

	/**
	 * 
	 * @param i integer that is used to add to the roman integer.
	 * @return true to store new value and roman values.
	 */
	public boolean add(int i) {
		this.value += i;
		this.roman = convertToString(this.value);
		return true;
	}

	/**
	 * 
	 * @param i integer that is used to subtract from the roman integer.
	 * @return true to store new value and roman values.
	 */
	public boolean subtract(int i) {
		this.value -= i;
		this.roman = convertToString(this.value);
		return true;
	}
}