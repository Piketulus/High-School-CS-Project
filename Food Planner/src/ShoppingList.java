import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShoppingList {

	private JFrame frame;
	
	public void setV (boolean b) {
		frame.setVisible(b);
	}
	
	Stack q = new Stack();
	public void getStack(Stack a) {
		q = a;
	}

	public void setText(IngredientsLL a, IngredientsLL b, IngredientsLL c, IngredientsLL d, IngredientsLL e, IngredientsLL f, IngredientsLL g) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(30, 295, 390, 250);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(30, 295);
		scrollPane.setSize(390, 250);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea2 = new JTextArea();
		textArea2.setBounds(30, 30, 390, 250);
		textArea2.setEditable(false);
		
		JScrollPane scrollPane2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setLocation(30, 30);
		scrollPane2.setSize(390, 250);
		frame.getContentPane().add(scrollPane2);
		
		textArea2.setText("Total:" + "\n\n");
		int length = a.getlength() + b.getlength() + c.getlength() + d.getlength() + e.getlength() + f.getlength() + g.getlength();
		String [] all = new String[length];
		INode temp = new INode();
		int count = 0;
		
		IngredientsLL [] things = {a, b, c, d, e, f, g};
		
		for (int k = 0; k < 7; k++) {
			temp = things[k].head;
			while (temp != null) {
				if (!temp.i.equals("")) {
					all[count] = temp.i;
					count++;
				}
				temp = temp.next;
			}
		}
		
		for (int q = 0; q < length; q++) {
			int counter = 0;
			if (!all[q].equals(null) && !all[q].equals("")) {
				for (int r = q+1; r < length; r++) {
					if (!all[r].equals(null) && !all[r].equals("")) {
						if (all[r].toString().equals(all[q].toString())) {
							all[r] = "";
							counter++;
						}
					}
				}
				textArea2.append(all[q] + " x" + (counter+1) + "\n");
			}
		}
		
		INode cur = new INode();
		
		textArea.setText("\n");
		
		for (int l = 0; l < 7; l++) {
			textArea.append("Day " + (l+1) + ":" + "\n\n");
			cur = things[l].head;
			while (cur != null) {
				if (!cur.i.equals("")) {
					textArea.append(cur.i + "\n");
				}
				cur = cur.next;
			}
			textArea.append("\n\n");
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Planner a = new Planner();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnBack.setBounds(170, 545, 117, 29);
		frame.getContentPane().add(btnBack);
		
		textArea2.setCaretPosition(0);
		textArea.setCaretPosition(0);
		
	}
	
	
}
