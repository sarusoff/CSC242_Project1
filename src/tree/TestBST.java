package tree;

public class TestBST {

	public static void main(String[] args) {
		
		System.out.println("\nStarting tests\n");
		
		BST<Integer> tree = new BST<Integer>();
		
		// testing insert()
		System.out.println("Inserting integers into the tree");
		tree.insert(5);
		tree.insert(3);
		tree.insert(7);
		tree.insert(10);
		tree.insert(8);
		tree.insert(15);
		tree.insert(16);
		tree.insert(17);
		
		// testing printInOrder
		System.out.println("New Tree: ");
		tree.printInOrder();
		
		// testing lookup()
		System.out.println("\nTesting lookup()");
		System.out.println("Is 5 in the tree? " + tree.lookup(5));
		System.out.println("Is 4 in the tree? " + tree.lookup(4));
		System.out.println("Is 17 in the tree? " + tree.lookup(17));

		// testing delete()
		System.out.println("\nTesting delete():\n");
		System.out.println("Deleting 17, which is a leaf");
		tree.delete(17);
		System.out.println("New Tree: ");
		tree.printInOrder();
		
		System.out.println("\nDeleting 15, which has one child");
		tree.delete(15);
		System.out.println("New Tree: ");
		tree.printInOrder();
		
		System.out.println("\nDeleting 10, which has two children");
		tree.delete(10);
		System.out.println("New Tree: ");
		tree.printInOrder();
		
		
		System.out.println("\nEnding tests\n");
	}

}
