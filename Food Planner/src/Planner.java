import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Planner {

	private JFrame frame;
	
	public void setV(boolean b) {
		frame.setVisible(b);
	}
	
	Stack q = new Stack();
	public void getStack(Stack a) {
		q = a;
	}
	
	DoubleLL food = new DoubleLL();
	public void getItems() {
		try {
			String name;
			String rating;
			String category; 
			boolean done;
			Scanner c = new Scanner(new FileReader ("src/FoodItems.txt"));
			while (c.hasNext()) {
				IngredientsLL a = new IngredientsLL();
				done = false;
				name = c.nextLine();
				rating = c.nextLine();
				category = c.nextLine();
				while (done == false) {
					String temp = c.nextLine();
					if (!temp.equals("Stop") && !temp.equals("")) {
						a.add(temp);
					} else if (temp.equals("Stop")) {
						done = true;
					}
				}
				int r = Integer.parseInt(rating);
				food.add(name, category, r, a);
			}
			c.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Planner() {
		getItems();
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Weekly Planner");
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		String[] foods = new String[food.count()];
		DoubleNode cur = food.tail;
		for (int i = 0; i < food.count(); i++) {
			foods[i] = cur.name;
			cur = cur.previous;
		}
		
		JComboBox c1 = new JComboBox(foods);
		c1.setBounds(148, 42, 152, 27);
		
		JComboBox c2 = new JComboBox(foods);
		c2.setBounds(148, 85, 152, 27);
		
		JComboBox c3 = new JComboBox(foods);
		c3.setBounds(148, 128, 152, 27);
		
		JComboBox c4 = new JComboBox(foods);
		c4.setBounds(148, 171, 152, 27);
		
		JComboBox c5 = new JComboBox(foods);
		c5.setBounds(148, 214, 152, 27);
		
		JComboBox c6 = new JComboBox(foods);
		c6.setBounds(148, 257, 152, 27);
		
		JComboBox c7 = new JComboBox(foods);
		c7.setBounds(148, 300, 152, 27);
		
		JComboBox[] items = {c1, c2, c3, c4, c5, c6, c7};
		
		try {
			Scanner c = new Scanner(new FileReader ("src/Planner.txt"));
			if (c.hasNext()) {
				c1.setSelectedItem(c.nextLine());
				c2.setSelectedItem(c.nextLine());
				c3.setSelectedItem(c.nextLine());
				c4.setSelectedItem(c.nextLine());
				c5.setSelectedItem(c.nextLine());
				c6.setSelectedItem(c.nextLine());
				c7.setSelectedItem(c.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JButton btnSave = new JButton("Save");
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c1.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		btnView.setBounds(323, 41, 88, 29);
		frame.getContentPane().add(btnView);
		
		JButton button = new JButton("View");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c2.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button.setBounds(323, 84, 88, 29);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("View");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c3.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button_1.setBounds(323, 127, 88, 29);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("View");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c4.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button_2.setBounds(323, 170, 88, 29);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("View");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c5.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button_3.setBounds(323, 213, 88, 29);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("View");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c6.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button_4.setBounds(323, 256, 88, 29);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("View");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String curName = c7.getSelectedItem().toString();
				DoubleNode current = new DoubleNode();
				current = food.head;
				boolean found = false;
				while (current != null && found == false) {
					if (current.name.equals(curName)) {
						btnSave.doClick();
						frame.dispose();
						ItemMenu a = new ItemMenu();
						a.setV(true);
						a.getStack(q);
						a.buttons(current.name, current.rating, current.category, current.ingredients, 1);
						found = true;
					} else {
						current = current.next;
					}
				}
			}
		});
		button_5.setBounds(323, 299, 88, 29);
		frame.getContentPane().add(button_5);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnView.setEnabled(!btnView.isEnabled());
				c1.setEnabled(!c1.isEnabled());
			}
		});
		checkBox.setBounds(21, 42, 28, 23);
		frame.getContentPane().add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(!button.isEnabled());
				c2.setEnabled(!c2.isEnabled());
			}
		});
		checkBox_1.setBounds(21, 85, 28, 23);
		frame.getContentPane().add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setEnabled(!button_1.isEnabled());
				c3.setEnabled(!c3.isEnabled());
			}
		});
		checkBox_2.setBounds(21, 128, 28, 23);
		frame.getContentPane().add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_2.setEnabled(!button_2.isEnabled());
				c4.setEnabled(!c4.isEnabled());
			}
		});
		checkBox_3.setBounds(21, 171, 28, 23);
		frame.getContentPane().add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setEnabled(!button_3.isEnabled());
				c5.setEnabled(!c5.isEnabled());
			}
		});
		checkBox_4.setBounds(21, 214, 28, 23);
		frame.getContentPane().add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_4.setEnabled(!button_4.isEnabled());
				c6.setEnabled(!c6.isEnabled());
			}
		});
		checkBox_5.setBounds(21, 257, 28, 23);
		frame.getContentPane().add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_5.setEnabled(!button_5.isEnabled());
				c7.setEnabled(!c7.isEnabled());
			}
		});
		checkBox_6.setBounds(21, 300, 28, 23);
		frame.getContentPane().add(checkBox_6);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintWriter pw = new PrintWriter(new FileWriter("src/Planner.txt"));
					pw.println(c1.getSelectedItem().toString());
					pw.println(c2.getSelectedItem().toString());
					pw.println(c3.getSelectedItem().toString());
					pw.println(c4.getSelectedItem().toString());
					pw.println(c5.getSelectedItem().toString());
					pw.println(c6.getSelectedItem().toString());
					pw.println(c7.getSelectedItem().toString());
					pw.close();
					PrintWriter p = new PrintWriter(new FileWriter("src/PlannerCheck.txt"));
					if (checkBox.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_1.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_2.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_3.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_4.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_5.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					if (checkBox_6.isSelected()) {
						p.println("1");
					} else {
						p.println("0");
					}
					p.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(305, 422, 117, 29);
		frame.getContentPane().add(btnSave);
		btnSave.setVisible(false);
		
		JButton btnReset = new JButton("Reset Days");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.setEnabled(true);
				c2.setEnabled(true);
				c3.setEnabled(true);
				c4.setEnabled(true);
				c5.setEnabled(true);
				c6.setEnabled(true);
				c7.setEnabled(true);
				btnView.setEnabled(true);
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				button_4.setEnabled(true);
				button_5.setEnabled(true);
				checkBox.setSelected(false);
				checkBox_1.setSelected(false);
				checkBox_2.setSelected(false);
				checkBox_3.setSelected(false);
				checkBox_4.setSelected(false);
				checkBox_5.setSelected(false);
				checkBox_6.setSelected(false);
				try {
					PrintWriter pw = new PrintWriter(new FileWriter("src/Planner.txt"));
					pw.println("");
					pw.close();
					PrintWriter p = new PrintWriter(new FileWriter("src/PlannerCheck.txt"));
					p.println("");
					p.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				btnSave.doClick();
			}
		});
		btnReset.setBounds(183, 422, 117, 29);
		frame.getContentPane().add(btnReset);
		
		try {
			Scanner c = new Scanner(new FileReader ("src/PlannerCheck.txt"));
			if (c.hasNext()) {
				if (c.nextLine().equals("1")) {
					checkBox.setSelected(true);
					btnView.setEnabled(false);
					c1.setEnabled(false);
				} else {
					checkBox.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_1.setSelected(true);
					button.setEnabled(false);
					c2.setEnabled(false);
				} else {
					checkBox_1.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_2.setSelected(true);
					button_1.setEnabled(false);
					c3.setEnabled(false);
				} else {
					checkBox_2.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_3.setSelected(true);
					button_2.setEnabled(false);
					c4.setEnabled(false);
				} else {
					checkBox_3.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_4.setSelected(true);
					button_3.setEnabled(false);
					c5.setEnabled(false);
				} else {
					checkBox_4.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_5.setSelected(true);
					button_4.setEnabled(false);
					c6.setEnabled(false);
				} else {
					checkBox_5.setSelected(false);
				}
				if (c.nextLine().equals("1")) {
					checkBox_6.setSelected(true);
					button_5.setEnabled(false);
					c7.setEnabled(false);
				} else {
					checkBox_6.setSelected(false);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave.doClick();
				frame.dispose();
				MainWindow a = new MainWindow();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnBack.setBounds(305, 422, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnRandomize = new JButton("Randomize");
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] nums = new int[7];
				nums[0] = (int)(Math.random() * food.count());
				for (int i = 1; i < 7; i++) {
					boolean done = false;
					while (done == false) {
						int n = (int)(Math.random() * food.count());
						boolean dupe = false;
						for (int j = 0; j < i; j++) {
							if (nums[j] == n) {
								dupe = true;
							}
						}
						if (!dupe) {
							nums[i] = n;
							done = true;
						}
					}
				}
				c1.setSelectedIndex(nums[0]);
				c2.setSelectedIndex(nums[1]);
				c3.setSelectedIndex(nums[2]);
				c4.setSelectedIndex(nums[3]);
				c5.setSelectedIndex(nums[4]);
				c6.setSelectedIndex(nums[5]);
				c7.setSelectedIndex(nums[6]);
			}
		});
		btnRandomize.setBounds(73, 351, 117, 29);
		frame.getContentPane().add(btnRandomize);
		
		JButton btnIngredientsList = new JButton("Ingredients List");
		btnIngredientsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingList a = new ShoppingList();
				IngredientsLL a0 = null, a1 = null, a2 = null, a3 = null, a4 = null, a5 = null, a6 = null;
				for (int i = 0; i < items.length; i++) {
					String curName = items[i].getSelectedItem().toString();
					DoubleNode current = new DoubleNode();
					current = food.head;
					boolean done = false;
					while (current != null && done == false) {
						if (current.name.equals(curName)) {
							if (i == 0) {
								a0 = current.ingredients;
							} else if (i == 1) {
								a1 = current.ingredients;
							} else if (i == 2) {
								a2 = current.ingredients;
							} else if (i == 3) {
								a3 = current.ingredients;
							} else if (i == 4) {
								a4 = current.ingredients;
							} else if (i == 5) {
								a5 = current.ingredients;
							} else if (i == 6) {
								a6 = current.ingredients;
							}
							done = true;
						} else {
							current = current.next;
						}
					}
				
				}
				a.setText(a0, a1, a2, a3, a4, a5, a6);
				btnSave.doClick();
				frame.dispose();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnIngredientsList.setBounds(217, 351, 152, 29);
		frame.getContentPane().add(btnIngredientsList);
		
		for (int i = 1; i < 8; i++) {
			JLabel label = new JLabel("Day " + i + ":");
			int a = i * 43;
			label.setBounds(50, a, 61, 16);
			frame.getContentPane().add(label);
		}
		
		frame.getContentPane().add(c1);
		frame.getContentPane().add(c2);
		frame.getContentPane().add(c3);
		frame.getContentPane().add(c4);
		frame.getContentPane().add(c5);
		frame.getContentPane().add(c6);
		frame.getContentPane().add(c7);

	}
}
