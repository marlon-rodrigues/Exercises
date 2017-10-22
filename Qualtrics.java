/* Main Questions 

- Least common ancestor of 2 nodes
- Find the intersection between two linked lists
- Largest Island problem
- Find route problem
- white elephant gift exchange where you can't give to the person who gave to you or to yourself. 
- Determine if 2 cubes are the same
- Dynamic programming coin picking. Each player takes turns picking from a collection of coins. 
The game ends when all of the coins have been claimed, and the winner has the highest value. 
Program this game keeping in mind that each player wants to win. 
(http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
http://articles.leetcode.com/coins-in-line/
)

- Big O and comparisons between DT (http://bigocheatsheet.com/)
- RESTApi

*/

/*********************************************************************************/

/* Trie Tree 

Data structure that is kind of like a tree but is often used to store characterers. 
Use a hash map with the putIfAbsent function to add new elements
Traverse through the trie looking for child that match that value
*/

/*********************************************************************************/

/* Reverse a string */
public static void reverseString(String theString) {
	char[] stringChar = theString.toCharArray();
	for (int i = stringChar.length - 1; i >= 0; i--) {
		System.out.print(stringChar[i]);
	} 
}

/*********************************************************************************/

/* Palindrome - Reads the same backwards as forwards */
public static boolean isPalindrome(String theString) {
	int start = 0;
	int end = theString.length - 1;

	while(start < end) {
		String startString = theString.substring(start, start + 1);
		String endString = theString.substring(end, end+1);

		if(startString != endString) {
			return false;
		}

		start++;
		end--;
	}

	return true;
}

/*********************************************************************************/

/* Unrolled Linked List 

An unrolled linked list is a variation on the linked list which stores multiple elements in each node.
*/
Class Node {
	int id;
	Node next;
	int numElements = 10;
	int[] elements = new int[numElements];
}

/*********************************************************************************/

/* Serialize and Deserialize BST 

Serialize = storing a given tree in a file or array
Deserialize = construct the binary tree from file or array
*/

//Deserialize with sorted array with unique elements
TreeNode createMinimalBST(int array[]) {
	return createMinimalBST(array, 0, array.length - 1);
}

TreeNode createMinimalBST(int arr[], int start, int end) {
	if(end < start) {
		return null;
	}

	int mid = (start + end) / 2;
	TreeNode n = new TreeNode(arr[mid]);
	n.left = createMinimalBST(arr, start, mid - 1);
	n.right = createMinimalBST(arr, end, mid + 1);

	return n;
}

//Serialze
public void serializeTree(Node root) {
	serializeTree(root, new ArrayList<Integer>());
}

public static void serializeTree(Node root, ArrayList<Integer> theArray) {
	if(root != null) {
		serializeTree(root.left, theArray);
		theArray.add(root.data);
		serializeTree(root.right, theArray);
	}
} 

/*********************************************************************************/

/* Stack vs Heap 

The main difference between stacks and heaps is that while stack is a linear data structure, heap is a non linear data structure. 
Stack is an ordered list that follows the LIFO property, while the heap is a complete tree that follows the heap property.

What is Stack?
As mentioned earlier, stack is a data structure in which elements are added and removed from only one end called the top.

What is Heap?
As mentioned earlier, heap is a complete tree that satisfies the heap property. Heap property states that, if y is a child node of x 
then the value stored in node x should be greater than or equal to the value stored in node y (i.e. value(x) ≥ value(y)). 
This property implies that the node with the greatest value would always be placed at the root.
*/

/*********************************************************************************/

/* Linked List vs Array List 

LinkedList is fast for adding and deleting elements, but slow to access a specific element. ArrayList is fast for accessing a 
specific element but can be slow to add to either end, and especially slow to delete in the middle. ArrayList is essentially an array. 
LinkedList is implemented as a double linked list (list root has a link to the front and end of the list).
*/

/*********************************************************************************/

/* Binary Tree vs Binary Search Tree 

Binary tree: Tree where each node has up to two leaves

Binary Search Tree: A binary tree where the left child contains only nodes with values less than the parent node, and where the 
right child only contains nodes with values greater than or equal to the parent.
*/

/*********************************************************************************/

/* Given an array of strings, group the anagrams */
public static void getAnagrams(String words) {
	HashMap<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();

	for(String word : words) {
		String key = sortString(word);
		if(hmap.get(key) == null) {
			hmap.put(key, new ArrayList<String>());
		}
		hmap.get(key).add(word);
	}

	System.out.print(hmap);
}

public static String sortString(String word) {
	word = word.toLowerCase();
	char[] wordChar = word.toCharArray();
	Arrays.sort(wordChar);
	return new String(wordChar);
}

/*********************************************************************************/

/* Inorder traversal for a binary search tree */
public static void inOrderTraversal(Node root) {
	if(root != null) {
		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);
	}
}

/*********************************************************************************/

/*  Given two arrays, write a function that will return an array with the common values between the arrays. */
public static ArrayList<Integer> findCommonValues(int[] arr1, int[] arr2) {
	HashSet<Integer> hset = new HashSet<Integer>();
	ArrayList<Integer> results = new ArrayList<Integer>();

	for(int i=0; i<arr1.length; i++) {
		if(!hset.contains(arr1[i])) {
			hset.add(arr1[i]);
		}
	}

	for(int i=0; i<arr2.length; i++) {
		if(hset.contains(arr2[i])) {
			results.add(arr2[i]);
		}
	}

	return results;
}

/*********************************************************************************/

/* Verify a Binary Search Tree */
public static booelan checkBST(Node root) {
	checkBST(root, null, null);
}

public static boolean checkBST(root, Integer min, Integer max) {
	if(root == null) return true;

	if( (max != null && root.data > max) || (min != null & root.data < min) return false;

	return (checkBST(root.left, min, root.data) && checkBST(root.right, root.data, max));
}

/*********************************************************************************/

/* Binary Search */
public static int binarySearch(int[] arr, int value) {
	int start = 0;
	int end = arr.length - 1;

	while(start < length) {
		int medium = arr.length/2;

		if(arr[medium] > value) {
			end = medium - 1;
		} else if(arr[medium] < value) {
			start = medium + 1;
		} else {
			return medium;
		}
	}

	return -1;
}

/*********************************************************************************/

/* Depth First Search and Breadth First Search */
Class Node {
	int value;
	int id;
	ArrayList<Node> adjacents = new ArrayList<Node>();
}

public static boolean dfs(Node source, Node destination) {
	HashSet<Integer> visited = new HashSet<Integer>();
	return dfs(source, destination, visited);
}

public static booelan dfs(Node source, Node destination, HashSet<Integer> visited) {
	if(visited.contains(source.id)) {
		return false;
	}

	visited.add(source.id);

	if(source.id == destination.id) {
		return true;
	}

	for(Node child : source.adjacents) {
		if(dfs(child, destination, visited)) {
			return true;
		}
	}

	return false;
}

public static boolean bfs(Node source, Node destination) {
	HashSet<Integer> visited = new HashSet<Integer>();
	LinkedList<Node> nextToVisit = new LinkedList<Node>();

	nextToVisit.add(source);

	while(!nextToVisit.isEmpty()) {
		Node currNode = nextToVisit.remove();

		if(currNode.id == destination.id) {
			return  true;
		}

		if(visited.contains(currNode)) {
			continue;
		}

		visited.add(currNode);

		for(Node child : currNode.adjacents) {
			nextToVisit.add(child);
		}
	}

	return false;
}

/*********************************************************************************/

/* HashTable - Collisions 
   2 types of solution:
   - Open Addressing:
        1. Linear Probing -> Continue to next null (increment 1)
        2. Quadratic Probing -> Square the step number
        3. Double Hasing - Hash value twice
   - Separate Chaining
         1. Use array or linked list to store values

*/

/*********************************************************************************/

/* Find sum in array */
public static boolean hasSum(int[] arr, int sum) {
	HashSet<Integer> hset = new HashSet<Integer>();

	for(int i=0; i<arr.size(); i++) {
		if(hset.contains(arr[i])) {
			return true;
		}

		hset.add(sum - arr[i]);
	}

	return false;
}

/*********************************************************************************/

/* Pascal Triangle */
public printPascal(int n) {
	for(int i=0; i<n; i++) {
		for(int j=0; j<=i; i++) {
			System.out.print(calculatePascal(i, j) + " ");
		}
		System.out.println();
	}
}    

public int calculatePascal(int i, int j) {
	if(j == 0) return 1;

	if(i == j) return 1;

	return (calculatePascal(i - 1, j - 1) + calculatePascal(i - 1, j));

}  

/*********************************************************************************/

/* Coin Change */
public static int coinChange(int[] coins, int amount) {
	int[] combinations = new int[amount + 1];
	combinations[0] = 1;

	for(int coin : coins) {
		for(int i=coin; i<coins.length; i++) {
			combinations[i] += combinations[i - coin];
		}
	}

	return combinations[amount];
}

// Design
https://www.youtube.com/watch?v=K7o5OlRLtvU
https://www.youtube.com/watch?v=vg5onp8TU6Q