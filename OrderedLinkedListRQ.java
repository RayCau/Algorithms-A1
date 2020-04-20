import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */
public class OrderedLinkedListRQ implements Runqueue {

	Proc head;
	int linkedLength;
	/**
	 * Constructs empty linked list
	 */
	public OrderedLinkedListRQ() {
		head = null;
		linkedLength = 0;

	}  // end of OrderedLinkedList()

	@Override
	public void enqueue(String procLabel, int vt) {
		// Implement me
		Proc newProc = new Proc(procLabel, vt, null);
		//if head is null, then set head
		if(head == null) {
			head = new Proc(procLabel, vt, null);	
		}
		//If new proc is less than head, set it as the new head and amend other links.
		else if(vt < head.getVt()) {
			Proc temp = head;
			head = newProc;
			newProc.setNext(temp);
		}
		else {
			Proc currProc = head;
			for(int i = 0; i<linkedLength; i++) {
				if(currProc.getNext() == null) {
					currProc.setNext(newProc);
				}
				else if(newProc.getVt() < currProc.getNext().getVt()) {
					Proc temp = currProc.getNext();
					currProc.setNext(newProc);
					newProc.setNext(temp);
					break;
				}
				currProc = currProc.getNext();
			}
			//currProc.setNext(newProc);
		}
		linkedLength++;
		//sort();
	} // end of enqueue()


	@Override
	public String dequeue() {
		// Implement me 

		if(head == null) {
			return "You cannot deqeueue an empty list, mate.";
		}
		Proc currProc = head;
		Proc smallestProc = head;

		while(currProc != null && currProc.getNext() != null){
			if(smallestProc.getVt() > currProc.getVt()){
				smallestProc = currProc;
			}
			if(currProc.getNext().getNext() == null) {
				if(smallestProc.getVt() > currProc.getNext().getVt()) {
					smallestProc = currProc.getNext(); 
				}
			}
			currProc = currProc.getNext();
		}

		if(smallestProc.getVt() == head.getVt()) {
			head = head.getNext();
			//Changing the length of the linked list to represent the removal of one node.
			if(linkedLength > 0) {
				linkedLength--;
			}
			return smallestProc.getProcLabel();
		}
		
		currProc = head;
		for(int i = 0; i <linkedLength; i++) {
			if(currProc.getNext().getVt() == smallestProc.getVt()) {
				currProc.setNext(currProc.getNext().getNext());
				//Changing the length of the linked list to represent the removal of one node.
				if(linkedLength > 0) {
					linkedLength--;
				}
				return smallestProc.getProcLabel();
			}
			currProc = currProc.getNext();
		}
		//Changing the length of the linked list to represent the removal of one node.
		if(linkedLength > 0) {
			linkedLength--;
		}
		return smallestProc.getProcLabel();


	} // end of dequeue()

	@Override
	public boolean findProcess(String procLabel) {
		// Implement me
		Proc currProc = head;
		for(int i = 0; i<linkedLength - 1; i++) {
			if(currProc.getProcLabel().equals(procLabel)) {
				return true;
			}else {
				currProc = currProc.getNext();
			}
		}
		return false; 
	} // end of findProcess()


	@Override
	public boolean removeProcess(String procLabel) {
		// Implement me
		if(findProcess(procLabel) == true) {
			Proc currProc = head;
			if(currProc.getProcLabel().equals(procLabel)) {
				if(head.getNext() != null){
					head = head.getNext();
					return true;
				}else {
					head = null;
					return true;
				}
			}else {
				for(int i = 0; i<linkedLength; i++) {
					if(currProc.getNext().getProcLabel().equals(procLabel)) {
						currProc.setNext(currProc.getNext().getNext());
						return true;
					}else {
						currProc = currProc.getNext();
					}
				}
			}
		}
		return false; // placeholder, modify this
	} // End of removeProcess()


	@Override
	public int precedingProcessTime(String procLabel) {
		// Implement me
		Proc currProc = head;
		int total = 0;

		if(findProcess(procLabel)) {
			for(int i = 0; i<linkedLength; i++) {
				if(currProc.getProcLabel().equals(procLabel)) {
					return total;
				}else {
					total += currProc.getVt();
				}
				currProc = currProc.getNext();
			}
			return total;
		}

		return -1; // placeholder, modify this
	} // end of precedingProcessTime()


	@Override
	public int succeedingProcessTime(String procLabel) {
		// Implement me
		Proc currProc = head;
		int total = 0;
		boolean s = false;
		
		if(findProcess(procLabel)) {
			for(int i = 0; i<linkedLength; i++) {
				if(s == true) {
					total += currProc.getVt();
				}
				if(currProc.getProcLabel().equals(procLabel)) {
					s = true;
				}
				currProc = currProc.getNext();
			}
			return total;
		}
		return -1;
	} // end of precedingProcessTime()


	@Override
	public void printAllProcesses(PrintWriter os) {
		//Implement me
		printList(os);
		
		os.println();

	} // end of printAllProcess()

	private void printList(PrintWriter os) {
		Proc currProc = head;
		for(int i = 0; i<linkedLength; i++) {
			if(currProc != null && i < linkedLength) {
				//System.out.print(currProc.getProcLabel() + " -> ");
				os.append(currProc.getProcLabel() + " ");
				currProc = currProc.getNext();
			}
		}
	}
} // end of class OrderedLinkedListRQ















