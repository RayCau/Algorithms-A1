
public class testmain {

	public static void main(String[] args) {
		OrderedLinkedListRQ list = new OrderedLinkedListRQ();
		list.enqueue("No.1", 1);
		list.enqueue("No.2", 2);
		list.enqueue("No.3", 3);
		list.enqueue("No.4", 4);
		list.enqueue("No.5", 5);//////////
		list.enqueue("No.6", 6);
		list.enqueue("No.7", 7);//13
		
		System.out.println(list.succeedingProcessTime("No.5"));
	}

}
