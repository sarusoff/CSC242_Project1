package tree;

public interface BSTInterface<T extends Comparable<T>> {
	public void insert(T x);
	public void delete(T x);
	public boolean lookup(T x);
	public void printPreOrder();
	public void printInOrder();
	public void printPostOrder();
}
