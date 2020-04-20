public class Stack {
	
	private Proc procs[];
	private int top = -1;
	private int size;
	private int noOfElements = 0;
	
	public void Stack(int size) {
		procs = new Proc[size];
		this.size = size;
	}
	
	public void push(Proc proc) {
		if(noOfElements < size) {
			procs[++top] = proc;
		}
	}
	
	public Proc pop() {
		if(noOfElements > 0) {
			return procs[top--];
		}
		return null;
	}
	
}
