import java.io.PrintWriter;
import java.lang.String;


/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

	/**
	 * Constructs empty queue
	 */
	Proc queue[];
	int length;


	public OrderedArrayRQ() {
		queue = new Proc[5000];
	}  // end of OrderedArrayRQ()


	@Override
	public void enqueue(String procLabel, int vt) { //Add element to back of queue.
		// Implement me
		for(int i = 0 ; i < queue.length ; i++ ) {
			if(queue[i] == null) {
				queue[i] = new Proc(procLabel, vt);
				break;
			}
		}
		
		sort(queue);
		//Order the queue based on vt. smallest vt is at front of queue. 
//		while(!isSorted(queue)) {
//			sortArray(queue);
//		}
		

	} // end of enqueue()


	@Override
	public String dequeue() { //Delete with highest priority
		int smallestvt = 0;
		int indexOfSmallestVt = 0;
		String deletedLabel;

		/*
		 * Find the smallest vt and its corresponding index.
		 */
		for(int i = 0 ; i < queue.length ; i++) {
			if(i == 0 && queue[i] != null) {
				smallestvt = queue[i].getVt();
				continue;
			}
			else if (queue[0] == null) {
				return "Cannot dequeue an empty queue, mate!";
			}
			if(queue[i] != null && queue[i].getVt() < smallestvt) {
				smallestvt = queue[i].getVt();
				indexOfSmallestVt = i;
			}
		}
		//Delete the Proc at 'indexOfsmallestVt'.
		deletedLabel = queue[indexOfSmallestVt].getProcLabel();
		queue[indexOfSmallestVt] = null;
		//Shift all elements after deleted elements down 
		shiftQueue(indexOfSmallestVt);
		return deletedLabel;
	} // end of dequeue()


	@Override
	public boolean findProcess(String procLabel){
		boolean processFound = false;

		for(int i = 0 ; i < queue.length ; i++) {
			if(queue[i] != null && (queue[i].getProcLabel().equals(procLabel))) {
				return true;
			}
		}
		return processFound; 
	} // end of findProcess()


	@Override
	public boolean removeProcess(String procLabel) {
		// Implement me
		//Find and remove process, store index of deleted element
		int deletedIndex = 0;
		boolean procRemoved = false;
		for(int i = 0 ; i < queue.length; i++) {
			if(queue[i] != null && (queue[i].getProcLabel().equals(procLabel))) {
				queue[i] = null;
				deletedIndex = i;
				procRemoved = true;
				break;
			}
		}
		//Shift all elements after deleted element down.
		if(procRemoved == true) {
			for(int i = deletedIndex + 1 ; i < queue.length ; i++) {
				if(queue[i] != null ) {
					queue[i - 1] = queue[i];
				}
				
			}
		}  
		
		//Remove duplicated element at the end of the queue.
		if(procRemoved == true) {
			for(int i = 0 ; i < queue.length ; i++) {
				if(queue[i + 1] == null) {
					queue[i] = null;
					break;
				}
			}
			return true;
		}
		return false;
	} // end of removeProcess()


	@Override
	public int precedingProcessTime(String procLabel) {
		int indexOfProc = 0;
		int totalTime = 0;

		if(findProcess(procLabel)) {
			//Find the Proc that has the matching proc label
			for(int i = 0 ; i < queue.length ; i++) {
				if(queue[i] != null && (queue[i].getProcLabel().equals(procLabel))) {
					indexOfProc = i;
					break;
				}
			}

			/*
			 * Calculate the total vt before the
			 * previously found proc.
			 */
			for(int i = 0; i < indexOfProc ; i++) {
				totalTime += queue[i].getVt();
			}
		}
		else {
			return -1;
		}		
		return totalTime; 
	}// end of precedingProcessTime()


	@Override
	public int succeedingProcessTime(String procLabel) {
		int indexOfProc = 0;
		int totalTime = 0;

		//Find the Proc that has the matching proc label
		
		if(findProcess(procLabel)) {
			for(int i = 0 ; i < queue.length ; i++) {
				if(queue[i] != null && (queue[i].getProcLabel().equals(procLabel))) {
					indexOfProc = i;
					break;
				}
			}

			/*
			 * Calculate the total vt after the
			 * previously found proc.
			 */
			for(int i = indexOfProc + 1; i < queue.length  ; i++) {
				if(queue[i] != null) {
					totalTime += queue[i].getVt();
				}
			}
		}
		else
		{
			return -1;
		}
	
		return totalTime; 
	} // end of precedingProcessTime()


	@Override
	public void printAllProcesses(PrintWriter os) {
		//Implement me
		for(int i = 0 ; i < queue.length ; i++) {
			if(queue[i] != null) {
				os.append(queue[i].getProcLabel() + " " );
			}			
		}
		os.println();
	} // end of printAllProcesses()

	private void shiftQueue(int index) { //Move all elements down after index i to remove all gaps
		for(int i = index + 1 ; i < queue.length ; i++) {
			if(queue[i] != null ) {
				queue[i - 1] = queue[i];
			}			
		}
		
		//Remove duplicated element at the end of the queue.
		for(int i = 0 ; i < queue.length ; i++) {
			if(queue[i + 1] == null) {
				queue[i] = null;
				break;
			}
		}
	}
	
	public void sort(Proc array[]) {
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                // check if we need to swap
            	if(array[j] != null && array[j + 1] != null) {
            		if (array[j].getVt() > array[j+1].getVt()) {
                        Proc temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
            	}                
            }
        }
    }
} // end of class OrderedArrayRQ




























