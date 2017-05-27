public class ArrayDeque <Template> {
	private Template[] queueArray;
	private int size;

	private int nextFirst;
	private int nextLast;

	public ArrayDeque() {
		size = 0;
		queueArray = (Template[]) new Object[8];
		nextFirst = 0;
		nextLast = 1;
	}

	public boolean isEmpty(){
		if(size == 0) return true;
		else return false;
	}

	public int size(){
		return size;
	}

	public void addFirst(Template item){
		queueArray[nextFirst] = item;
		nextFirst = (nextFirst == 0) ? queueArray.length - 1 : nextFirst - 1;
		size += 1;
		if(size > queueArray.length>>2) extendQueueArray();
	}

	public void addLast(Template item){
		queueArray[nextLast] = item;
		nextLast = (nextLast == queueArray.length - 1) ? 0 : nextLast + 1;
		size += 1;
		if(size > queueArray.length>>2) extendQueueArray();
	}

	public void removeFirst(){
		int position = (nextFirst == queueArray.length - 1) ? 0 : nextFirst + 1;
		queueArray[position] = null;
		nextFirst = position;
		size -= 1;
		if(size < queueArray.length>>3 && queueArray.length > 8) shrinkQueueArray();
	}

	public void removeLast(){
		int position = (nextLast == 0) ? queueArray.length - 1 : nextLast - 1;
		queueArray[position] = null;
		nextLast = position;
		size -= 1;
		if(size < queueArray.length>>3 && queueArray.length > 8) shrinkQueueArray();
	}

	private void extendQueueArray(){
		int originalSize = queueArray.length;
		Template[] newQueueArray = (Template[]) new Object[originalSize * 2];
		int copySize = (nextLast > nextFirst) ? nextLast - nextFirst : originalSize - nextFirst;
		System.arraycopy(queueArray, nextFirst, newQueueArray, 0, copySize);
		if(copySize - 1 < size) 
			System.arraycopy(queueArray, 0, newQueueArray, copySize, size - (copySize - 1));
		queueArray = newQueueArray;
		newQueueArray = null;
		nextFirst = 0;
		nextLast = size + 1;
		System.out.println("nextFirst after extension= "+ nextFirst);
		System.out.println("nextLast after extension= "+ nextLast);
	}

	private void shrinkQueueArray(){
		int originalSize = queueArray.length;
		Template[] newQueueArray = (Template[]) new Object[originalSize >> 1];
		int copySize = (nextLast > nextFirst) ? nextLast - nextFirst : originalSize - nextFirst;
		System.arraycopy(queueArray, nextFirst, newQueueArray, 0, copySize);
		if(copySize - 1 < size) 
			System.arraycopy(queueArray, 0, newQueueArray, copySize, size - (copySize - 1));
		queueArray = newQueueArray;
		newQueueArray = null;
		nextFirst = 0;
		nextLast = size + 1;
		System.out.println("nextFirst after shrink= "+ nextFirst);
		System.out.println("nextLast after shrink= "+ nextLast);
	}

	public void printDeque(){
		Template item;
		for(int i = 0; i < size; i++){
			int position = nextFirst + 1 + i;
			position = (position > queueArray.length - 1) ? position - queueArray.length : position;
			item = queueArray[position];
			System.out.print(item.toString() + " ");
		}
		System.out.println("");
	}

	public Template get(int index){
		if(index > size || index < 0) return null;
		int position = nextFirst + 1 + index;
		position = (position > queueArray.length - 1) ? position - queueArray.length : position;
		return queueArray[position];
	}
}