
public class Test {

	public static void main(String[] args) {
		
		OrderedLinkedListRQ l = new OrderedLinkedListRQ();
		l.enqueue("1", 1);
		l.enqueue("2", 2);
		l.enqueue("3", 3);
		//System.out.println(l.findProcess("2"));
		//System.out.println(l.removeProcess("3"));
		System.out.println(l.precedingProcessTime("3"));
		System.out.println(l.succeedingProcessTime("1"));

	}

}
