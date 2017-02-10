package tree;
/*
 Daniel Saltz
Binary Search Tree
 
 */

public class TreeNode<T extends Comparable<T>> {
	public T data;
	public TreeNode<T> leftChild;
	public TreeNode<T> rightChild;
	public TreeNode<T> parent;
	
	public TreeNode(T data) {
		this.data = data;
	}
	
	public TreeNode(T info, TreeNode<T> parent){ // receives data and parent node
		data = info;
		this.parent = parent;	
	}
	
	public void insert(TreeNode<T> current, T data) {
		
		int compare = data.compareTo(current.data);
		if (compare < 0) {
			if (current.leftChild == null) {
				current.leftChild = new TreeNode<T>(data,current);				
			} else {
				insert(current.leftChild, data);
			}
		} else {
			if (current.rightChild == null) {
				current.rightChild = new TreeNode<T>(data,current);				
			} else {
				insert(current.rightChild,data);
			}
		}
	}
	
	public void printPreOrder(TreeNode<T> current) {
		if (current != null) {
			System.out.println(current.data);
			printPreOrder(current.leftChild);
			printPreOrder(current.rightChild);
		}
	}
	
	public void printInOrder(TreeNode<T> current) {
		if (current != null) {
			printPreOrder(current.leftChild);
			System.out.println(current.data);
			printPreOrder(current.rightChild);
		}
	}
	
	public void printPostOrder(TreeNode<T> current) {
		if (current != null) {
			printPreOrder(current.leftChild);
			printPreOrder(current.rightChild);
			System.out.println(current.data);
		}
	}

	
	public boolean lookup(TreeNode<T> root, T x) {
		int compare = root.data.compareTo(x);
		if (compare > 0) {
			if (root.leftChild == null) {
				return false;
			} else {
				return lookup(root.leftChild,x);
			}
		} else if (compare < 0) {
			if (root.rightChild == null) {
				return false;
			} else {
				return lookup(root.rightChild,x);
			}
		}
		return true; // numbers must be equal, the data exists in the tree
	}

	public void delete(TreeNode<T> root, T x) {
		int compare = x.compareTo(root.data);
		
		if (compare  < 0) {
			delete(root.leftChild,x);
		} else if (compare > 0) {
			delete(root.rightChild,x);
		} else { // found the element to delete						
			if (root.leftChild == null && root.rightChild == null) { // leaf
				if (root.parent.leftChild == root) {
					root.parent.leftChild = null;
				} else {
					root.parent.rightChild = null;
				}
			} else if (root.leftChild == null) {
				if (root.parent.leftChild == root) {
					root.parent.leftChild = root.rightChild;
				} else {
					root.parent.rightChild = root.rightChild;
				}
				root.rightChild.parent = root.parent;
			} else if (root.rightChild == null) {
				if (root.parent.leftChild == root) {
					root.parent.leftChild = root.leftChild;
				} else {
					root.parent.rightChild = root.leftChild;
				}
				root.leftChild.parent = root.parent;
			} else { // 2 children								
				TreeNode<T> temp = root.rightChild;
				while (temp.leftChild != null) { // finding the left most child of the right child
					temp = temp.leftChild;
				}				
				root.data = temp.data;
				delete(temp,temp.data);				
			}
		}
	}
	
}
