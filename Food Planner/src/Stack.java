import java.io.*;

public class Stack extends DoubleLL {
	
	DoubleNode head = null;
    DoubleNode tail = null;
    DoubleNode current = head;
    
    public void add(String n){
        DoubleNode newNode = new DoubleNode();
        newNode.name = n;
        push(newNode);
    }
    
    public void push(DoubleNode newNode){
        if (head == null && tail == null){
            head = newNode;
            tail = newNode;
        } else {
        	tail.next = newNode;
        	newNode.previous = tail;
            tail = newNode;
        } 
    }
    
    public void pop(){
        if (head == tail){
        	delete(tail.name);
            head = null;
            tail = null;
        } else {
        	delete(tail.name);
        	tail = tail.previous;
            tail.next = null;
        } 
    }
    
    public void delete(String name) {
    	try {
			File fi = new File("src/FoodItems.txt");
			File t = new File("src/temp.txt");
			BufferedReader br = new BufferedReader(new FileReader("src/FoodItems.txt"));
			PrintWriter pw = new PrintWriter(new FileWriter(t));
			String line = null;
			boolean done = false;
			while (done == false) {
				line = br.readLine();
				if (line.trim().equals(name)) {
					while (!br.readLine().equals("Stop")) {
					}
					done = true;
				} else {
					pw.println(line);
					pw.flush();
				}
			}
			while ((line = br.readLine()) != null) {
				pw.println(line);
				pw.flush();
			}
			pw.close();
			br.close();
			t.renameTo(fi);
		} catch (IOException e1) {
		}
    	
    }
}
