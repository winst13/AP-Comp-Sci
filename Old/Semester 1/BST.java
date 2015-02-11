import java.util.*;


public class BST<T extends Comparable<T>> 
{
	public static void main(String args[])
	{
		int size = 10000;
		Randp r = new Randp(size);
		BST<Integer> big = new BST<Integer>(r.nextInt());
		for (int i = 1; i < size; i++)
		{
			big.insert(r.nextInt());
		}
		big.printTree();
		System.out.println("Depth is:"+ big.root.depth());
		// test delete
		Random randomgen = new Random();
		int todelete = randomgen.nextInt(size)+1;
		big.delete(todelete);
		System.out.println("Deleted:"+todelete+" Tree becomes:");
		big.printTree();
		System.out.println("After delete depth is:"+ big.root.depth());
	}
	
	private BSTnode<T> root;
	public BST(){
		root = null;
	}
	public BST(T datum)
	{
		root = new BSTnode<T>(datum);
	}
	
	public BSTnode<T> getTree()
	{
		return root;
	}
	public boolean isLeaf(BSTnode<T> inputNode){
		//The assignment says "returns true if the node is a leaf", it does not say which node need to be examed.  So use a input node
		if (root==null) return true;
		else if (inputNode.isLeaf()) return true;
		else return false;
	}
	public void printTree()
	{		
		root.printTree();
	}
	

	public String toString()
	{
		return root.toString();		
	}
	
	public int depth()
	{
		return root.depth();
	}
		
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
	public void insert(T indatum){
		root.insert(indatum);
	}
	public void delete(T datum)
	{
		root.delete(datum);
	}


}

class BSTnode<T extends Comparable<T>>
{
	private T datum;
	private BSTnode<T> left;
	private BSTnode<T> right;
	
	public BSTnode(T datum)
	{
		this.datum = datum;
		left = null;
		right = null;
	}
	
	public T getDatum()
	{
		return datum;
	}
	
	public BSTnode<T> getLeft()
	{
		return left;
	}
	
	public BSTnode<T> getRight()
	{
		return right;
	}
	
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
	public void setDatum(T indatum)
	{
		datum = indatum;
	}
	public void setLeft(T datum)
	{
		left = new BSTnode<T>(datum);
	}
	
	public void setRight(T datum)
	{
		right = new BSTnode<T>(datum);
	}
	public void setLeft(BSTnode<T> innode){
		left = innode;
	}
	public void setRight(BSTnode<T> innode){
		right = innode;
	}

	public void printTree()
	{
		
		StringBuffer resultBuffer = new StringBuffer();
		printTree(this, resultBuffer );
		System.out.println(resultBuffer.toString());
	}
	private void printTree(BSTnode<T> inputNode, StringBuffer resultBuffer) {
		 if (inputNode == null)
		    {
		        return;
		    }
		 printTree(inputNode.getLeft(), resultBuffer);
		 resultBuffer.append(inputNode.toString());
		 resultBuffer.append(" + ");
		 printTree(inputNode.getRight(), resultBuffer);		    
}		

	
	public String toString()
	{
		return datum.toString();
	}
	
	public void insert(T indatum){
		insert(indatum, this);
	}
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
	public int depth()
	{
		return depth(this);
	}

	private int depth(BSTnode<T> innode)
	   {
	      if(innode == null) return 0;
	      else
	      return 1 + Math.max( depth(innode.left), depth(innode.right));
	   }
	public BSTnode<T> getMin(){
		if (left!=null)return left.getMin();
		else return this;
	}
	
	public void delete(T indatum){
		delete(indatum, this);
	}
	private BSTnode<T> delete(T indatum, BSTnode<T> node)
	   {
	      if (node == null)  return null;
	      else if (indatum.compareTo(node.getDatum())<0){
	    	  BSTnode<T> deleted = delete(indatum, node.getLeft());
	    	  node.setLeft(deleted);
	      }
	      else if (indatum.compareTo(node.getDatum())>0){
	    	  BSTnode<T> deleted = delete(indatum, node.getRight());
	    	  node.setRight(deleted);
	      }
	      else
	      {
	         if (node.getLeft() == null) return node.getRight();
	         else if (node.getRight() == null) return node.getLeft();
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
