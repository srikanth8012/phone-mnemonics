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

		int length = phoneNumber.length();

		for (int i = 0; i < length; i++) {
			char[] buffer = new char[i + 1];
			int[] postion= new int[buffer.length];
			prepareMnemonics(numberList, buffer, 0, 0, patternList, phoneNumber, postion);
		}
		return patternList;
	}
	
	private String replacePhonenumber(String phoneNumber, char[] buffer, int[] postion) {
		for(int i =0; i < postion.length; i++) {
			phoneNumber = phoneNumber.substring(0, postion[i]) 
		              + buffer[i]
		              + phoneNumber.substring(postion[i] + 1); 
		}
		return phoneNumber;
	}

	private void prepareMnemonics(int[] a, char[] buffer, int aIndex, int bufferIndex, List<PhoneMnemonics> patternList,
			String phoneNumber, int[] postion) {
		if ( (bufferIndex >= buffer.length) || aIndex >= a.length) {
			PhoneMnemonics phone = new PhoneMnemonics();
			phone.setPhoneNumber(phoneNumber);
			phone.setMnemonic(replacePhonenumber(phoneNumber,buffer, postion));
			if(!patternList.contains(phone))
			patternList.add(phone);
			return;
		} else if( bufferIndex > 0) {
			aIndex ++;
		}
		
		while(aIndex < a.length) {
			char[] letters = getLetters(a[aIndex]);
			if (letters.length == 0) {
				buffer[bufferIndex] = (char)(a[aIndex]+'0');    
				postion= new int[buffer.length];
				postion[bufferIndex] = aIndex;
				 prepareMnemonics(a, buffer, aIndex, bufferIndex +1, patternList, phoneNumber, postion);
			}
				
			for (char letter : letters) {
				if(aIndex == 0) {
					postion= new int[buffer.length];
					buffer = new char[buffer.length];
				}
					
				postion[bufferIndex] = aIndex;
				buffer[bufferIndex] = letter;
				prepareMnemonics(a, buffer, aIndex, bufferIndex + 1, patternList, phoneNumber, postion);
			}
			
			aIndex ++;
			
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

		return Pattern.matches("[0-9]+", phoneNumber);
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
