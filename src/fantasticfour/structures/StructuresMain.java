package fantasticfour.structures;
public class StructuresMain {

	public static void main(String[] args) {
		SimpleLinkedList<Integer> Arr = new SimpleLinkedList<Integer>();
		for(int i = 0; i < 10; i++)
			Arr.add(i);
		show(Arr);
		Arr.remove(4);
		show(Arr);
		Arr.remove(4);
		show(Arr);
		
	}
	public static void show(SimpleArray<?> ar) {
		for(int i = 0; i < ar.size(); i++) {
			System.out.print(ar.get(i) + " ");
		}
		System.out.println("\tWith size = " + ar.size() + " cap = " + ar.capacity);
	}
	public static void show(SimpleLinkedList<?> ar) {
		for(int i = 0; i < ar.size(); i++) {
			System.out.print(ar.get(i) + " ");
		}
		System.out.println("\tsize = " + ar.size());
	}
}
