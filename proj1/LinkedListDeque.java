public class LinkedListDeque <Template> {
	public class Node <Template> {
		public Template value;
		public Node next;
		public Node prev;

		public Node(Template input, Node nextNode){
			value = input;
			next = nextNode;
			prev = nextNode;
		}
	}

	private int size;
	private Node <Template> sentinel;

	public LinkedListDeque() {
		size = 0;
		sentinel = new Node <Template>(null, null);
	}

	public LinkedListDeque(Template input){
		size = 1;
		sentinel = new Node <Template>(null, null);
		sentinel.next = new Node <Template>(input, sentinel);
		sentinel.prev = sentinel.next;
		sentinel.next.prev = sentinel;
	}

	public boolean isEmpty(){
		if(size == 0) return true;
		else return false;
	}

	public void addFirst(Template item){
		Node <Template> originalFirst = sentinel.next;
		Node <Template> newFirst = new Node <Template>(item, originalFirst);
		if(size > 0) 
		{
			originalFirst.prev = newFirst;
		}
		else
		{
			sentinel.prev = newFirst;
			newFirst.next = sentinel;
		}
		sentinel.next = newFirst;
		newFirst.prev = sentinel;
		size += 1;
	}

	public void addLast(Template item){
		Node <Template> originalLast = sentinel.prev;
		Node <Template> newLast = new Node <Template>(item, sentinel);
		sentinel.prev = newLast;
		if(size > 0)
		{
			originalLast.next = newLast;
			newLast.prev = originalLast;
		}
		else
		{
			sentinel.next = newLast;
			newLast.prev = sentinel;
		}
		size += 1;
	}

	public int size(){
		return size;
	}

	public void printDeque(){
		Template item;
		Node <Template> nodeInList = sentinel.next;
		for(int i = 1; i < size + 1; i++){
			item = nodeInList.value;
			System.out.print(item.toString() + " ");
			nodeInList = nodeInList.next;
		}
		System.out.println("");
	}

	public Template removeFirst(){
		if(size < 1) return null;
		size -= 1;
		Node <Template> nodeFirst = sentinel.next;
		Template item = nodeFirst.value;
		sentinel.next = nodeFirst.next;
		nodeFirst.next.prev = sentinel;
		nodeFirst = null;
		return item;
	}

	public Template removeLast(){
		if(size < 1) return null;
		size -= 1;
		Node <Template> nodeLast = sentinel.prev;
		Template item = nodeLast.value;
		sentinel.prev = nodeLast.prev;
		nodeLast.prev.next = sentinel;
		nodeLast = null;
		return item;
	}

	public Template get(int index){
		if(index > size || index < 0) return null;
		Template item;
		Node <Template> nodeInList = sentinel.next;
		for(int i = 0; i < index +1; i++){
			if(i != index) nodeInList = nodeInList.next;
		}
		item = nodeInList.value;
		return item;
	}

	private Template getRecursive(Node <Template> node, int index){
		if(index == 0) return node.value;
		Node <Template> nextNode = node.next;
		return getRecursive(nextNode, index - 1);
	}

	public Template getRecursive(int index){
		if(index > size - 1 || index < 0) return null;
		Template item;
		Node <Template> nodeFirst = sentinel.next;
		item = getRecursive(nodeFirst, index);
		return item;
	}
}