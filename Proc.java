
public class Proc {

	private String procLabel;
	private int vt;
	
	//For LinkedList
	private Proc nextProc = null;	
	
	//For Binary Search Tree.
	private Proc leftProc = null;
	private Proc rightProc = null;
	private Proc parent = null;
	
	//Constructor for Ordered array
	public Proc(String procLabel, int vt) {
		this.procLabel = procLabel;
		this.vt = vt;
	}
	//Constructor for LinkedList
	public Proc(String procLabel, int vt, Proc nextProc) {
		this.procLabel = procLabel;
		this.vt = vt;
		this.nextProc = nextProc;
	}	
	
	public String getProcLabel() {
		return procLabel;
	}

	public void setProcLabel(String procLabel) {
		this.procLabel = procLabel;
	}

	public int getVt() {
		return vt;
	}

	public void setVt(int vt) {
		this.vt = vt;
	}
	
	//METHODS FOR LINKED LIST
	public Proc getNext() {
		return nextProc;
	}
	
	public void setNext(Proc nextProc) {
		this.nextProc = nextProc;
	}
	
	public boolean hasNext() {
		boolean hasNext = false;
		
		if(nextProc != null) {
			hasNext = true;
		}
		
		return hasNext;
	}
	//END METHODS FOR LINKED LIST
	
	//START METHODS FOR BINARY SEARCH TREE
	public Proc getLeftProc() {
		return leftProc;
	}

	public void setLeftProc(Proc leftProc) {
		this.leftProc = leftProc;
	}

	public Proc getRightProc() {
		return rightProc;
	}

	public void setRightProc(Proc rightProc) {
		this.rightProc = rightProc;
	}
	
	public Proc getParentProc() {
		return parent;
	}
	
	public void setParentProc(Proc proc) {
		this.parent = proc;
	}
	//END METHODS FOR BINARY SEARCH TREE

}
