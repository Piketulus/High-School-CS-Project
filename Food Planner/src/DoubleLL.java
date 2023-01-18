
public class DoubleLL {
	
	DoubleNode head = null;
    DoubleNode tail = null;
    DoubleNode current = head;
    
    public void add(String n, String c, int r, IngredientsLL i){
        DoubleNode newNode = new DoubleNode();
        newNode.name = n;
        newNode.category = c;
        newNode.rating = r;
        newNode.ingredients = i;
        add(newNode);
    }
    
    public void add(DoubleNode newNode){
        if (head == null && tail == null){
            head = newNode;
            tail = newNode;
        } else if (head.rating != 0 && head == tail){
            if (newNode.rating >= head.rating){
                tail = newNode;
                head.next = tail;
                tail.previous = head;
            } else if (newNode.rating < head.rating){
                head = newNode;
                head.next = tail;
                tail.previous = head;
            }
        } else if(head != tail && newNode.rating >= tail.rating){
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else if (head != tail && newNode.rating < tail.rating){
            DoubleNode cur = head;
            if(newNode.rating <= head.rating){
                newNode.next = head;
                head.previous = newNode;
                head = newNode;
            }else{  
            	boolean done = false;
            	while (cur != null && done == false) {
                    if(newNode.rating >= cur.rating && newNode.rating <= cur.next.rating){
                        newNode.next = cur.next;
                        cur.next.previous = newNode;
                        cur.next = newNode;
                        newNode.previous = cur;
                        done = true;
                    } else {
                        cur = cur.next;
                    }
                }
            }
        }
    }
    
    public void clear() {
    	head = null;
    	tail = null;
    }
    
    public int count() {
		DoubleNode temp = head;
		int c = 0;
		while (temp != null) {
			c++;
			temp = temp.next;
		}
		return c;
    }
    
}
