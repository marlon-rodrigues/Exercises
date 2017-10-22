/* QUESTION 4.1 - Given a directed graph, design an algorithm to find out whether there is 
   a route between two nodes. */
enum State { Unvisited, Visited, Visiting; }

boolean search(Graph g, Node start, Node end) {
	if(start == end) return true;

	// Operates as a Queue
	LinkedList<Node> q = new LinkedList<Node>();

	for(Node u : g.getNodes()) {
		u.state = State.Unvisited;
	}

	start.state = State.Visiting;
	q.add(start);
	Node u;

	while(!q.isEmpty()) {
		u = q.removeFirst();
		if(u != null) {
			for (Node v : u.getAdjacent()) {
				if(v.state == State.Unvisited) {
					if(v == end) {
						return true;
					} else {
						v.state = State.Visiting;
						q.add(v);
					}
				}
			}

			u.state = State.Visited;
		}
	}

	return false;
}

/* QUESTION 4.2 - Given a sorted (increasing order) array with unique integer elements, write an
   algorithm to create a binary search tree with minimal height. */
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

/* QUESTION 4.3 - Given a binary tree, design an algorithm which creates a linked list of all the nodes
   at each depth (if you have a tree with depth D, you'll have D linked lists) */
ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
	ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();

	// "Visit" the root
	LinkedList<TreeNode> current = new LinkedList<TreeNode>();
	if(root != null) {
		current.add(root);
	}

	while(current.size() > 0) {
		result.add(current); // Add previous level
		LinkedList<TreeNode> parents = current; // Go to next level
		current = new LinkedList<TreeNode>(); // Reset current - so event if parents have left and right, 
		                                      // current will only be added once to results
		for(TreeNode parent : parents) {
			// Visit the children
			if(parent.left != null) {
				current.add(parent.left);
			}
			if(parent.right != null) {
				current.add(parent.right);
			}
		}
	}

	return result;
}

/* QUESTION 4.5 - Implement a function to check if a binary tree is a binary search tree. (left <= current < right) */
boolean checkBST(TreeNode n) {
	return checkBST(n, null, null);
}

boolean checkBST(TreeNode n, Integer min, Integer max) {
	if(n == null) {
		return true;
	}

	if((min != null && n.data <= min) || (max != null && n.data > max)) {
		return false;
	}

	if(!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max)) {
		return false;
	}

	return true;
}

/* QUESTION 4.6 - Write an algorithm to find the next node (in-order sucessor) of a given node in a binary search tree */
TreeNode inOrderSucc(TreeNode n) {
	if(n == null) return null;

	// Found right children, return leftmost node of right subtree 
	if(n.right != null) {
		return leftMostChild(n.right);
	} else {
		TreeNode q = n;
		TreeNode x = q.parent;

		// Go up until we're on left instead of right
		while(x != null && x.left != q) {
			q = x;
			x = x.parent;
		}

		return x;
	}
}

TreeNode leftMostChild(TreeNode n) {
	if(n == null) return null;

	while(n.left != null) {
		n = n.left;
	}

	return n;
}

/* QUESTION 4.10 - T1 and T2 are two binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2
   is a subtree of T1. */
booelan containsTree(TreeNode t1, TreeNode t2) {
	if(t2 == null) return true; // The empty tree is always a subtree
	return subTree(t1, t2);
}

boolean subTree(TreeNode r1, TreeNode r2) {
	if(r1 == null) {
		return false; // big tree empty & subtree still not found
	} else if (r1.data == r2.data && matchTree(r1, r2)) {
		return true;
	}

	return subTree(r1.left, r2) || subTree(r1.right, r2); // OR table (only returns false if both false)
}

boolean matchTree(TreeNode r1, TreeNode r2) {
	if(r1 == null && r2 == null) {
		return true;
	} else if(r1 == null || r2 == null) {
		return false;
	} else if (r1.data != r2.data) {
		return false;
	} else {
		return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
	}
}

/* QUESTION 4.12 - You are given a binary tree in which each node contains an integer value (which migh be positve or negative).
   Design an algorithm to count the number of paths that sum to a given value. The path does not need to start or end at the root
   or a leaf, but it must go downwards. */
int countPathsWithSum(TreeNode root, int targetSum) {
	if(root == null) return 0;

	// Count paths with sum starting from the root
	int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

	// Try the nodes on the left and right
	int pathsOnLeft = countPathsWithSum(root.left, targetSum);
	int pathsOnRight = countPathsWithSum(root.right, targetSum);

	return pathsFromRoot + pathsOnLeft + pathsOnRight;
}

int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
	if(node == null) return 0;

	currentSum += node.data;

	int totalPaths = 0;
	if(currentSum == targetSum) {
		totalPaths++;
	}

	totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
	totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);

	return totalPaths;
}