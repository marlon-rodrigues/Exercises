/* QUESTION 8.1 - Triple Step: A child is running up a staircase with n steps and can hop either 1 step,
   2 steps or 3 steps at a time. Implement a method to count how many possible ways the child can run up
   the stairs.*/
int countWays(int n) { //n is the nth step
	if(n < 0) {
		return 0;
	} else if (n == 0) {
		return 1;
	} else {
		return countWays(n-1) + countWays(n-2) + countWays(n-3);
	}
}

/* QUESTION 8.3 - A magic index in an array A[1...n-1] is defined to be an index such that A[i] = i. Given
   a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A. */
int magicFast(int[] array) {
	return magicFast(array, 0, array.length - 1);
}

int magicFast(int[] array, int start, int end) {
	if(end < start) {
		return -1;
	}
	int mid = (start + end) / 2;
	if(array[mid] == mid) {
		return mid;
	} else if (array[mid] > mid) {
		return magicFast(array, start, mid - 1);
	} else {
		return magicFast(array, mid + 1, end);
	}
}

/* QUESTION 8.5 - Write a recursive function to multiply two positive integers without using the * or / operators. 
   You can use addition, subtraction, and bit shifting, but you should minimize the number of those operations. */
int minProduct(int a, int b) {
	int bigger = a > b ? b : a;
	int smaller = a > b ? a : b;
	return minProductHelper(smaller, bigger);
}

int minProductHelper(int smaller, int bigger) {
	if(smaller == 0) return 0;
	else if (smaller == 1) return bigger;

	int s = smaller >> 1; //Divide by 2 -> bit shifting
	int halfProd = minProductHelper(s, bigger);

	if(smaller % 2 == 0) {
		return halfProd + halfProd;
	} else {
		return halfProd + halfProd + bigger;
	}
}

/* QUESTION 8.6 - Towers of Hanoi */
public void moveDisks(int n, Tower destination, Tower buffer) {
	if(n > 0) {
		moveDisks(n-1, buffer, destination);
		moveTopTo(destination);
		buffer.moveDisks(n-1, destination, this);
	}
}

public void moveTopTo(Tower t) {
	int top = disks.pop();
	t.add(top);
}

/* QUESTION 8.7 - Write a method to compute all permutations of a string of unique characters.
   Answer: try each character as the first character and then append the permutations */
ArrayList<String> getPerms(String str) {
	ArrayList<String> result = new ArrayList<String>();
	getPerms("", str, result);
	return result;
}

void getPerms(String prefix, String remainder, ArrayList<String> result) {
	if(remainder.length() == 0) result.add(prefix);

	int len = remainder.length();
	for(int i=0; i<len; i++) {
		String before = remainder.substring(0, i);
		String after = remainder.substring(i + 1, len);
		char c = remainder.charAt(i);
		getPerms(prefix + c, before + after, result);
	}

}


