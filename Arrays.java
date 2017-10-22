/* QUESTION 1.1 - Check if string has all unique characters */
//Assume string is ASCII code
boolean isUniqueChars(String str) {
	if(str.length() > 128) {
		return false;
	}

	boolean[] char_set = new boolean[128];

	for(int i=0; i < str.length; i++) {
		int val = str.charAt(i);

		if(char_set[val]) {
			return false;
		}

		char_set[val] = true;
	}

	return true;
}

/* QUESTION 1.2 - Check if 2 strings are permutations */
// Solution 1 - Sort string
String sort(String s) {
	char[] content = s.toCharArray();
	java.util.Arrays.sort(content);

	return new String(content);
}

boolean permutations(String s, String t) {
	if(s.length() != t.length()) {
		return false;
	}

	return sort(s).equals(sort(t));
}

// Solution 2 - Count characters
boolean permutations(String s, String t) {
	if(s.length() != t.length()) {
		return false;
	}

	int[] letters = new int[128]; //Assume is a ASCII string

	for(int i=0; i<s.length(); i++) {
		letters[s.charAt(i)]++;
	}

	for(int i=0; i<t.length; t++) {
		letters[t.charAt(i)]--;

		if(letters[t.charAt(i)] < 0) {
			return false;
		}
	}

	return true;
}

/* QUESTION 1.3 - Replace spaces by "%20" */
void replaceSpaces(char[] str, int trueLength) {
	int spaceCount = 0;
	int index = 0;
	int i = 0;

	for(i=0; i<trueLength; i++) {
		if(str[i] == ' ') {
			spaceCount++;
		}
	}

	index = trueLength + spaceCount * 2;

	if(trueLength < str.length) {
		str[trueLength] = '\0';
	}

	for(i = trueLength - 1; i >=0; i--) {
		if(str[i] == ' ') {
			str[index - 1] = '0';
			str[index - 2] = '2';
			str[index - 3] = '%';
			index = index - 3;
		} else {
			str[index - 1] = str[i];
			index--;
		}
	}
}

// In PHP is much simpler because strings are permutable
<?php
	function replaceString($s) {
		return str_replace(" ", "%20", $s);
	}
?>

/* QUESTION 1.4 - Permutation of Palindrome */
boolean isPermutationOfPalindrome(String phrase) {
	int countOdd = 0;
	int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

	for(char c : phrase.toCharArray()) {
		int x = getCharArray(c);

		if(x != -1) {
			table[x]++;

			if(table[x] % 2 == 1) {
				countOdd++;
			} else {
				countOdd--;
			}
		}
	}

	return countOdd <= 1;
}

// Map each character to a number ( a -> 0, b -> 1, c -> 2, etc). This is case insensitive. Non-letter chars map to -1
int getCharNumber(Character c) {
	int a = Character.getNumericValue('a');
	int z = Character.getNumericValue('z');
	int val = Character.getNumericValue(c);

	if(a <= val && val <= z) {
		return val - a;
	} 

	return -1;
}

/* QUESTION 1.5 - One string away */
boolean oneEditAway(String first, String second) {
	if(first.length() == second.length()) {
		return oneEditReplace(first, second);
	} else if (first.length() + 1 == second.length()) {
		return oneEditInsert(first, second);
	} else if(first.length() - 1 == second.length()) {
		return oneEditInsert(second, first);
	}

	return false;
}

boolean oneEditReplace(String s1, String s2) {
	boolean foundDifference = false;

	for(int i=0; i<s1.length(); i++) {
		if(s1.charAt(i) != s2.charAt(i)) {
			if(foundDifference) {
				return false;
			}

			foundDifference = true;
		}
	}

	return true;
}

boolean oneEditInsert(Strig s1, String s2) {
	int index1 = 0;
	int index2 = 0;

	while(index2 < s2.length() && index1 < s1.length()) {
		if(s1.charAt(index1) != s2.chartAt(index2)) {
			if(index1 != index2) {
				return false;
			}

			index2++;
		} else {
			index1++;
			index2++;
		}
	}

	return true;
}

/* QUESTION 1.6 - Compress String aabccdd = a2b1c2d2 */
String compress(String str) {
	String compressString = "";
	int countConsecutive = 0;

	for(int i=0; i<str.length(); i++) {
		countConsecutive++;

		if(i+1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
			compressedString = compressedString + str.charAt(i) + countConsecutive;
			countConsecutive = 0;
		}
	}

	return compressedString.length() < str.length() ? compressedString : str;
}

/* QUESTION 1.9 - Given a method isSubstring, verify if s2 is a rotation of s1 */
// xy = s1, yx = s2 -> s2 = xyxy
boolean isRotation(String s1, String s2) {
	int len = s1.length();
	if(len == s2.length() && len > 0) {
		String s1s1 = s1 + s1;
		return isSubstring(s1s1, s2);
	}
	return false;
}
