package tree;

public class BST<T extends Comparable<T>> implements BSTInterface<T>{

	private TreeNode<T> root;
	
	@Override
	public void insert(T x) {		
		if (root == null) {
			root = new TreeNode<T>(x);
		} else if (!lookup(x)){
			root.insert(root,x);			
		}
	}

	@Override
	public void delete(T x) {
		if (root != null && lookup(x)) {
			root.delete(root,x);
		}
	}

	@Override
	public boolean lookup(T x) {
		if (root == null) {
			System.out.println("Root does not exist.");
			return false;
		} else {
			return root.lookup(root,x);
		}
	}

	@Override
	public void printPreOrder() {
		if (root == null) {
			System.out.print("Root does not exsist");
		} else {
			root.printPreOrder(root);
		}
	}

	@Override
	public void printInOrder() {
		if (root == null) {
			System.out.print("Root does not exsist");
		} else {
			root.printInOrder(root);
		}		
	}

	@Override
	public void printPostOrder() {
		if (root == null) {
			System.out.print("Root does not exsist");
		} else {
			root.printPostOrder(root);
		}		
	}

	
}
