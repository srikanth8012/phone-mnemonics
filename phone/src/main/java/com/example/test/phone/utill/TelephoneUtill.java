package com.example.test.phone.utill;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.example.test.phone.model.PhoneMnemonics;

@Component
public class TelephoneUtill {

	public List<PhoneMnemonics> prepareList(String phoneNumber, int[] numberList) {
		List<PhoneMnemonics> patternList = new ArrayList<PhoneMnemonics>();
		char[] buffer = new char[numberList.length];
		prepareMnemonics(numberList, buffer, 0, 0, patternList, phoneNumber);
		return patternList;
	}

	private void prepareMnemonics(int[] a, char[] buffer, int aIndex, int bufferIndex, List<PhoneMnemonics> patternList,
			String phoneNumber) {
		if (bufferIndex >= buffer.length || aIndex >= a.length) {
			PhoneMnemonics phone = new PhoneMnemonics();
			phone.setPhoneNumber(phoneNumber);
			phone.setMnemonic(String.valueOf(buffer));
			patternList.add(phone);
			return;
		}
		char[] letters = getLetters(a[aIndex]);
		if (letters.length == 0)
			prepareMnemonics(a, buffer, aIndex + 1, bufferIndex, patternList, phoneNumber);
		for (char letter : letters) {
			buffer[bufferIndex] = letter;
			prepareMnemonics(a, buffer, aIndex + 1, bufferIndex + 1, patternList, phoneNumber);
		}
	}

	public int[] convertStringToIntegerArray(String phoneNumber) throws Exception {
		int[] numberList = null;
				numberList = new int[phoneNumber.length()];
				for (int i = 0; i < phoneNumber.length(); i++) {
					Character c = phoneNumber.charAt(i);
					numberList[i] = Integer.parseInt(c.toString());
				}
				return numberList;
			
	}
	
	
	public boolean isValidPhoneNumber(String phoneNumber) {
		
		return  Pattern.matches("[0-9]+", phoneNumber);
	}

	private char[] getLetters(int digit) {
		switch (digit) {
		case 0:
			return new char[] {};
		case 1:
			return new char[] {};
		case 2:
			return new char[] { 'a', 'b', 'c' };
		case 3:
			return new char[] { 'd', 'e', 'f' };
		case 4:
			return new char[] { 'g', 'h', 'i' };
		case 5:
			return new char[] { 'j', 'k', 'l' };
		case 6:
			return new char[] { 'm', 'n', 'o' };
		case 7:
			return new char[] { 'p', 'q', 'r', 's' };
		case 8:
			return new char[] { 't', 'u', 'v' };
		case 9:
			return new char[] { 'w', 'x', 'y', 'z' };
		}
		throw new IllegalArgumentException("Invalid Digit " + digit);
	}

}
