import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

	/**
	 * Constructs empty queue
	 */
	private int leaves = 0;
	private Proc root;
	public BinarySearchTreeRQ() {
		// Implement Me
		root = null;
	}  // end of BinarySearchTreeRQ()


	@Override
	public void enqueue(String procLabel, int vt) {
		// Implement me
		add(root, procLabel, vt);
	} // end of enqueue()


	@Override
	public String dequeue() {
		// Implement me
		// DOES NOT ACTUALLY DELETE YET!!!!! JUST SIMPLY FINDS PROC THAT NEEDS TO BE DELETED!!!
		Proc currProc = root;
		String deletedProc = "";
		if(root == null) {
			return "You can't dequeue nothing.";
		}
		if(root.getLeftProc() == null) {
			deletedProc = root.getProcLabel();
			root = root.getRightProc();
			return deletedProc;
		} else{
			while(currProc.getLeftProc() != null) {
				currProc = currProc.getLeftProc();
			}
			deletedProc = currProc.getProcLabel();

			Proc currProcParent = currProc.getParentProc();
			Proc currProcRight = currProc.getRightProc();

			currProc.getParentProc().setLeftProc(null);

			if(currProc.getRightProc() != null) {
				currProcParent.setLeftProc(currProcRight);
			}
			return deletedProc;
		}
	}


	@Override
	public boolean findProcess(String procLabel) {
		// Implement me
		Proc currProc = root; boolean checkedLeftProc = false; 
		while (currProc != null)
		{
			if (!checkedLeftProc)
			{ 
				while (currProc.getLeftProc() != null)
				{ 
					currProc = currProc.getLeftProc(); 
				} 
			}
			//
			if(currProc.getProcLabel().equals(procLabel)) {
				return true;
			}
			//
			checkedLeftProc = true; 

			if (currProc.getRightProc() != null)
			{ 
				checkedLeftProc = false; 
				currProc = currProc.getRightProc(); 
			}
			else if (currProc.getParentProc() != null){ 
				while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
					currProc = currProc.getParentProc(); 
				}
				if (currProc.getParentProc() == null) {
					break; 
				}
				currProc = currProc.getParentProc(); 
			}
			else {
				break; 
			}
		}
		return false; // placeholder, modify this
	} // end of findProcess()
	// end of findProcess()


	@Override
	public boolean removeProcess(String procLabel) {
		if(findProcess(procLabel)) {
			Proc procTBD = getProcViaLabel(procLabel);

			if(procTBD.getLeftProc() == null && procTBD.getRightProc() == null) {
				if(procTBD.getParentProc() != null 
						&& procTBD.getParentProc().getLeftProc().getProcLabel().equals(procLabel)){
					procTBD.getParentProc().setLeftProc(null);
					procTBD = null;
					return true;
				}else {
					procTBD.getParentProc().setRightProc(null);
					procTBD = null;
					return true;
				}

			}else if(procTBD.getLeftProc() != null && procTBD.getRightProc() == null) {
				if(procTBD.getParentProc() != null) {
					procTBD.getParentProc().setLeftProc(procTBD.getLeftProc());
					procTBD = null;
					return true;
				}else if(procTBD.getParentProc() == null) {
					root = procTBD.getLeftProc();
					root.setParentProc(null);
					return true;
				}
			}else if(procTBD.getLeftProc() == null && procTBD.getRightProc() != null) {
				procTBD = procTBD.getRightProc();
			}else if(procTBD.getLeftProc() != null && procTBD.getRightProc() != null) {

			}
			return true;
		}
		return false; // placeholder, modify this
	} // end of removeProcess()


	@Override
	public int precedingProcessTime(String procLabel) {
		int procVt = getProcVt(procLabel);
		int total = 0;
		if(procVt != -1) {
			//preceding should be combined vt of all vt's lower than procLabel.
			Proc currProc = root; boolean checkedLeftProc = false; 
			while (currProc != null)
			{
				if (!checkedLeftProc)
				{ 
					while (currProc.getLeftProc() != null)
					{ 
						currProc = currProc.getLeftProc(); 
					} 
				}

				if(currProc.getVt() < procVt) {
					total += currProc.getVt();
				}

				checkedLeftProc = true; 

				if (currProc.getRightProc() != null)
				{ 
					checkedLeftProc = false; 
					currProc = currProc.getRightProc(); 
				}
				else if (currProc.getParentProc() != null){ 
					while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
						currProc = currProc.getParentProc(); 
					}
					if (currProc.getParentProc() == null) {
						break; 
					}
					currProc = currProc.getParentProc(); 
				}
				else {
					break; 
				}
			}
			return total;
		}
		return -1; // placeholder, modify this
	} // end of precedingProcessTime()


	@Override
	public int succeedingProcessTime(String procLabel) {
		int procVt = getProcVt(procLabel);
		int total = 0;
		if(procVt != -1) {
			//preceding should be combined vt of all vt's lower than procLabel.
			Proc currProc = root; boolean checkedLeftProc = false; 
			while (currProc != null)
			{
				if (!checkedLeftProc)
				{ 
					while (currProc.getLeftProc() != null)
					{ 
						currProc = currProc.getLeftProc(); 
					} 
				}

				if(currProc.getVt() > procVt) {
					total += currProc.getVt();
				}

				checkedLeftProc = true; 

				if (currProc.getRightProc() != null)
				{ 
					checkedLeftProc = false; 
					currProc = currProc.getRightProc(); 
				}
				else if (currProc.getParentProc() != null){ 
					while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
						currProc = currProc.getParentProc(); 
					}
					if (currProc.getParentProc() == null) {
						break; 
					}
					currProc = currProc.getParentProc(); 
				}
				else {
					break; 
				}
			}
			return total;
		}
		return -1; // placeholder, modify this
	} // end of precedingProcessTime()


	@Override
	public void printAllProcesses(PrintWriter os) {
		// Implement me
		Proc currProc = root; boolean checkedLeftProc = false; 
		while (currProc != null)
		{
			if (!checkedLeftProc)
			{ 
				while (currProc.getLeftProc() != null)
				{ 
					currProc = currProc.getLeftProc(); 
				} 
			}
			os.append(currProc.getProcLabel() + " "); 

			checkedLeftProc = true; 

			if (currProc.getRightProc() != null)
			{ 
				checkedLeftProc = false; 
				currProc = currProc.getRightProc(); 
			}
			else if (currProc.getParentProc() != null){ 
				while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
					currProc = currProc.getParentProc(); 
				}
				if (currProc.getParentProc() == null) {
					break; 
				}
				currProc = currProc.getParentProc(); 
			}
			else {
				break; 
			}
		}
		os.println();
	}

	//Private methods
	private void add(Proc current, String procLabel, int vt) {
		//Add new node to the left if value is less than current. Add new node to the right
		//if value is greater than current.
		if(root == null) {
			root = new Proc(procLabel, vt);
			return;
		}
		if(vt < current.getVt()) {
			if(current.getLeftProc() == null) {
				current.setLeftProc(new Proc(procLabel, vt));
				current.getLeftProc().setParentProc(current);
				return;
			}
			add(current.getLeftProc(), procLabel, vt);
		}else {
			if(current.getRightProc() == null) {
				current.setRightProc(new Proc(procLabel, vt));
				current.getRightProc().setParentProc(current);
				return;
			}	
			add(current.getRightProc(), procLabel, vt);
		}
	}

	//finds the vt of the proclabel
	private int getProcVt(String procLabel) {
		Proc currProc = root; boolean checkedLeftProc = false; 
		while (currProc != null)
		{
			if (!checkedLeftProc)
			{ 
				while (currProc.getLeftProc() != null)
				{ 
					currProc = currProc.getLeftProc(); 
				} 
			}
			//
			if(currProc.getProcLabel().equals(procLabel)) {
				return currProc.getVt();
			}
			//
			checkedLeftProc = true; 

			if (currProc.getRightProc() != null)
			{ 
				checkedLeftProc = false; 
				currProc = currProc.getRightProc(); 
			}
			else if (currProc.getParentProc() != null){ 
				while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
					currProc = currProc.getParentProc(); 
				}
				if (currProc.getParentProc() == null) {
					break; 
				}
				currProc = currProc.getParentProc(); 
			}
			else {
				break; 
			}
		}
		return -1;
	}

	private Proc getProcViaLabel(String procLabel) {
		Proc currProc = root; boolean checkedLeftProc = false; 
		while (currProc != null)
		{
			if (!checkedLeftProc)
			{ 
				while (currProc.getLeftProc() != null)
				{ 
					currProc = currProc.getLeftProc(); 
				} 
			}
			//
			if(currProc.getProcLabel().equals(procLabel)) {
				return currProc;
			}
			//
			checkedLeftProc = true; 

			if (currProc.getRightProc() != null)
			{ 
				checkedLeftProc = false; 
				currProc = currProc.getRightProc(); 
			}
			else if (currProc.getParentProc() != null){ 
				while (currProc.getParentProc() != null	&& currProc == currProc.getParentProc().getRightProc()) {
					currProc = currProc.getParentProc(); 
				}
				if (currProc.getParentProc() == null) {
					break; 
				}
				currProc = currProc.getParentProc(); 
			}
			else {
				break; 
			}
		}
		return currProc;
	}
	private Proc remove(Proc current) {
		Proc currProc = current;
		if(current.getLeftProc() == null) {
			System.out.println(currProc.getProcLabel());
			return currProc;
		}
		else {
			currProc = currProc.getLeftProc();
			remove(currProc);
		}
		return currProc;
	}
} // end of class BinarySearchTreeRQ
