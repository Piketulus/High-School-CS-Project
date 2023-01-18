
public class IngredientsLL {
		INode head = null;
	    INode tail = null;
	    INode current = head;
	    public void add(String a){
	        INode newNode = new INode();
	        newNode.i = a;
	        add(newNode);
	    }
	    public void add(INode newNode){
	        if (head == null && tail == null){
	            head = newNode;
	            tail = newNode;
	        } else {
	        	tail.next = newNode;
	        	tail = newNode;
	        }
	    }
	    public int getlength() {
	    	INode cur = head;
	    	int count = 0;
	    	while (cur != null) {
	    		count++;
	    		cur = cur.next;
	    	}
	    	return count;
	    }
	}
