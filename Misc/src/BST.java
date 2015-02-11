import java.util.*;

/**
 * This is the class with the methods for binary search trees.  I have answered
 * the questions that go along with this project below:
 * 
 * 1.  The worst case for finding a datum on a binary search tree is O(n), where
 * the depth of the tree is n and the datum to be searched for is last
 * 2.  The best case for finding a datum on a binary search tree is O(log(n))
 * 3.  The average case for finding a datum on a binary search tree is also O(log(n)).
 * 4.  The order of growth to insert an element on the tree is also directly related to
 * depth of the tree, so the average case order of growth is O(log(n))
 * 5.  The order of growth for printing the tree is O(n), since every element in the
 * tree must be printed
 * 6.  (EC)  The order of growth to delete an element is related to the order of growth
 * for searching, since it takes constant time to remove the element from the tree.  Thus,
 * the average case is O(log(n)).
 * 
 * Note:  This class does use the Randp class, which is submitted along with this assignment
 * @author winst13
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>> 
{
	public static void main(String args[])
	{
		//Creates a tree with 10000 nodes
		int size = 10000;
		Randp r = new Randp(size);
		BST<Integer> big = new BST<Integer>(r.nextInt());
		for (int i = 1; i < size; i++)
		{
			big.insert(r.nextInt());
		}
		
		//tests printTree() function
		big.printTree();
		
		//tests depth() function
		System.out.println("Depth is:"+ big.root.depth());
		
		// test delete() function
		Random randomgen = new Random();
		int todelete = randomgen.nextInt(size)+1;
		big.delete(todelete);
		System.out.println("Deleted:"+todelete+" Tree becomes:");
		big.printTree();
		System.out.println("After delete depth is:"+ big.root.depth());
	}
	
	private BSTnode<T> root;
	
	/**
	 * This is the default constructor for a binary search tree
	 */
	public BST()
	{
		root = null;
	}
	
	/**
	 * This is a constructor for a binary search tree.  It takes
	 * an argument datum which it sets as the root.
	 * @param datum
	 */
	public BST(T datum)
	{
		root = new BSTnode<T>(datum);
	}
	
	/**
	 * This method returns the node of the BST
	 * @return
	 */
	public BSTnode<T> getTree()
	{
		return root;
	}
	
	/**
	 * This method takes an inputted node and returns whether
	 * or not it is a leaf of the BST
	 * @param inputNode
	 * @return
	 */
	public boolean isLeaf(BSTnode<T> inputNode)
	{
		if (root==null) 
		{
			return true;
		}
		else if (inputNode.isLeaf()) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * This method prints the tree using infix notation
	 */
	public void printTree()
	{		
		root.printTree();
	}
	
	/**
	 * This method converts the entire tree to a string, overriding
	 * the toString method in Object
	 */
	public String toString()
	{
		return root.toString();		
	}
	
	/**
	 * This finds the depth of the tree
	 * @return
	 */
	public int depth()
	{
		return root.depth();
	}
		
	/**
	 * This method searches for a certain datum starting from a 
	 * given node
	 * @param datum
	 * @param node
	 * @return
	 */
	public BSTnode<T> search(T datum, BSTnode<T> node)
	{
		if (node == null || node.getDatum() == datum)
		{
			return node;
		}
		else if(datum.compareTo(node.getDatum())<0)
		{
			return search(datum, node.getLeft());
		}
		else
		{
			return search(datum, node.getRight());
		}
	}
	
	/**
	 * This method inserts a new datum, such that the tree remains
	 * a binary search tree
	 * @param indatum
	 */
	public void insert(T indatum)
	{
		root.insert(indatum);
	}
	
	/**
	 * This method deletes a datum, also making sure that 
	 * the tree remains a BST
	 * @param datum
	 */
	public void delete(T datum)
	{
		root.delete(datum);
	}
}

/**
 * This class encodes for a node.  It is generic, which means its
 * type is specified in these <> brackets
 * @author winst13
 *
 * @param <T>
 */
class BSTnode<T extends Comparable<T>>
{
	private T datum;
	private BSTnode<T> left;
	private BSTnode<T> right;
	
	/**
	 * This constructor takes a datum and makes it the node
	 * It also sets the left and right nodes to null
	 * @param datum
	 */
	public BSTnode(T datum)
	{
		this.datum = datum;
		left = null;
		right = null;
	}
	
	/**
	 * This method returns the value of the root
	 * @return
	 */
	public T getDatum()
	{
		return datum;
	}
	
	/**
	 * This method returns the node that is on the left
	 * branch
	 * @return
	 */
	public BSTnode<T> getLeft()
	{
		return left;
	}
	
	/**
	 * This method returns the node that is on the right
	 * branch
	 * @return
	 */
	public BSTnode<T> getRight()
	{
		return right;
	}
	
	/**
	 * This method returns whether or not the node is a 
	 * leaf.  It does this by checking the right and left
	 * nodes
	 * @return
	 */
	public boolean isLeaf()
	{
		if (left == null && right == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method sets the datum to the input value
	 * @param indatum
	 */
	public void setDatum(T indatum)
	{
		datum = indatum;
	}
	
	/**
	 * This method creates a new node to the left
	 * with the inputted datum as its datum
	 * @param datum
	 */
	public void setLeft(T datum)
	{
		left = new BSTnode<T>(datum);
	}
	
	/**
	 * This method creates a new node to the right
	 * with the inputted datum as its datum
	 * @param datum
	 */
	public void setRight(T datum)
	{
		right = new BSTnode<T>(datum);
	}
	
	/**
	 * This method sets the inputted node
	 * as the node to the left
	 * @param innode
	 */
	public void setLeft(BSTnode<T> innode)
	{
		left = innode;
	}
	
	/**
	 * This method sets the inputted node
	 * as the node to the right
	 * @param innode
	 */
	public void setRight(BSTnode<T> innode)
	{
		right = innode;
	}

	/**
	 * This method prints the tree, starting from this node
	 */
	public void printTree()
	{
		
		StringBuffer resultBuffer = new StringBuffer();
		printTree(this, resultBuffer );
		System.out.println(resultBuffer.toString());
	}
	
	/**
	 * This method prints the tree starting at inputnode
	 * @param inputNode
	 * @param resultBuffer
	 */
	private void printTree(BSTnode<T> inputNode, StringBuffer resultBuffer) 
	{
		if (inputNode == null)
		{
			return;
		}
		printTree(inputNode.getLeft(), resultBuffer);
		resultBuffer.append(inputNode.toString());
		resultBuffer.append(" + ");
		printTree(inputNode.getRight(), resultBuffer);		    
	}		
	
	/**
	 * This method returns the node as a string
	 */
	public String toString()
	{
		return datum.toString();
	}
	
	/**
	 * This method inserts a given datum into the tree
	 * by converting it into a node and inserting that node
	 * @param indatum
	 */
	public void insert(T indatum)
	{
		insert(indatum, this);
	}
	
	/**
	 * This method inserts a node into the tree
	 * @param indatum
	 * @param node
	 * @return
	 */
	private BSTnode<T> insert(T indatum, BSTnode<T> node)
	{
		if (node == null) 
		{
			return new BSTnode<T>(indatum);
		}
		else if (indatum.compareTo(node.getDatum())<0)
		{
			BSTnode<T> inserted = insert(indatum, node.getLeft());
			node.setLeft(inserted);
			return node;
		}
		else if (indatum.compareTo(node.getDatum())>0)
		{
			BSTnode<T> inserted = insert(indatum, node.getRight());
			node.setRight(inserted);
			return node;
		}
		return node;
	}	
	
	/**
	 * This method returns the depth of the tree
	 * @return
	 */
	public int depth()
	{
		return depth(this);
	}

	/**
	 * The method also returns the depth of the tree,
	 * but takes the starting node as an argument
	 * @param innode
	 * @return
	 */
	private int depth(BSTnode<T> innode)
	{
		if(innode == null) 
		{
			return 0;
		}
		else
		{
			return 1 + Math.max( depth(innode.left), depth(innode.right));
		}
	}
	
	/**
	 * This method returns the minimum of a tree.  This is extremely 
	 * useful in the deletion method
	 * @return
	 */
	public BSTnode<T> getMin()
	{
		if (left!=null)
		{
			return left.getMin();
		}
		else 
		{
			return this;
		}
	}
	
	/**
	 * This method deletes a datum from the tree
	 * @param indatum
	 */
	public void delete(T indatum)
	{
		delete(indatum, this);
	}
	
	/**
	 * This method deletes a node from the tree.  There are three cases
	 * 1:  Deleted node is leaf
	 * 2:  Deleted node has 1 child
	 * 3:  Deleted node has 2 children
	 * @param indatum
	 * @param node
	 * @return
	 */
	private BSTnode<T> delete(T indatum, BSTnode<T> node)
	{
		if (node == null)  
		{
			return null;
		}
		//case 1
		else if (indatum.compareTo(node.getDatum())<0)
		{
			BSTnode<T> deleted = delete(indatum, node.getLeft());
			node.setLeft(deleted);
		}
		else if (indatum.compareTo(node.getDatum())>0)
		{
			BSTnode<T> deleted = delete(indatum, node.getRight());
			node.setRight(deleted);
		}
		else
		{
			//case 2
			if (node.getLeft() == null) 
			{
				return node.getRight();
			}
			else if (node.getRight() == null) 
			{
				return node.getLeft();
			}
			//case 3
			else
			{
				// get the min (left most node) from the right tree
				T leftonright = node.getRight().getMin().getDatum();
				node.setDatum(leftonright);
				BSTnode<T> deleted = delete(leftonright, node.getRight());
				node.setRight(deleted);
			}
		}
		return node;
	}
}
