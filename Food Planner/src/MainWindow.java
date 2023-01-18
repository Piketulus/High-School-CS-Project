import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.*;

public class MainWindow {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setV(boolean b) {
		frame.setVisible(b);
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
	
	Stack q = new Stack();
	public void getStack(Stack a) {
		q = a;
	}
	
	public void addCategoryAll() {
		Scanner c;
		try {
			c = new Scanner(new FileReader ("src/Categories.txt"));
			if (!c.hasNext()) {
				try {
					PrintWriter pw = new PrintWriter(new FileWriter("src/Categories.txt", true));
					pw.println("All");
					pw.close();
				} catch (IOException e) {
				}
			}
		} catch (FileNotFoundException e1) {
		}
	}
	
	public MainWindow() {
		addCategoryAll();
		getItems();
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Food Logger");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JButton btnWeeklyPlanner = new JButton("Weekly Planner");
		btnWeeklyPlanner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Planner a = new Planner();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnWeeklyPlanner.setBounds(240, 315, 145, 29);
		frame.getContentPane().add(btnWeeklyPlanner);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3, 5, 5));
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setLocation(80, 95);
		scrollPane.setSize(650, 191);
		
		
		JLabel lblCategories = new JLabel("Categories:");
		lblCategories.setBounds(54, 29, 99, 16);
		frame.getContentPane().add(lblCategories);
		
		JButton btnAddCategory = new JButton("Add/Delete Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddCategory a = new AddCategory();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnAddCategory.setBounds(44, 315, 163, 29);
		frame.getContentPane().add(btnAddCategory);
		
		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(559, 34, 61, 16);
		frame.getContentPane().add(lblSearch);
		
		JTextField searchField = new JTextField();
		
		searchField.setBounds(610, 29, 130, 26);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		DoubleNode cur = new DoubleNode();
		cur = food.tail;
		while (cur != null) {
			JButton b = new JButton ("");
			b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String curName;
					if (!b.getText().equals("")) {
						curName = b.getText().substring(0, b.getText().length() - 5);
						DoubleNode current = new DoubleNode();
						current = food.head;
						boolean found = false;
						while (current != null && found == false) {
							if (current.name.equals(curName)) {
								frame.dispose();
								ItemMenu a = new ItemMenu();
								a.setV(true);
								a.getStack(q);
								a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
								found = true;
							} else {
								current = current.next;
							}
						}
					}
				}
			});
			panel.add(b);
			cur = cur.previous;
		}
        
		frame.getContentPane().add(scrollPane);
		
		JLabel sorting = new JLabel("Sorting:");
		sorting.setBounds(240, 29, 99, 16);
		frame.getContentPane().add(sorting);
		
		JComboBox sortingBox = new JComboBox();
		sortingBox.addItem("Highest Rating");
		sortingBox.addItem("Lowest Rating");
		sortingBox.setBounds(230, 35, 172, 48);
		frame.getContentPane().add(sortingBox);
		
		JComboBox comboBox = new JComboBox();
		try {
			Scanner c = new Scanner(new FileReader ("src/Categories.txt"));
			while (c.hasNext()) {
				comboBox.addItem(c.nextLine());
			}
			c.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		comboBox.setBounds(44, 35, 172, 48);
		frame.getContentPane().add(comboBox);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFood a = new AddFood();
				a.setV(true);
				a.getStack(q);
				frame.dispose();
			}
		});
		btnAddItem.setBounds(634, 315, 117, 29);
		frame.getContentPane().add(btnAddItem);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				String curCategory = comboBox.getSelectedItem().toString();
				DoubleNode cur = new DoubleNode();
				if (sortingBox.getSelectedItem().equals("Highest Rating")) {
					cur = food.tail;
					if (curCategory.equals("All")) {
						while (cur != null) {
							JButton b = new JButton ("");
							b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
							b.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String curName;
									if (!b.getText().equals("")) {
										curName = b.getText().substring(0, b.getText().length() - 5);
										DoubleNode current = new DoubleNode();
										current = food.head;
										boolean found = false;
										while (current != null && found == false) {
											if (current.name.equals(curName)) {
												frame.dispose();
												ItemMenu a = new ItemMenu();
												a.setV(true);
												a.getStack(q);
												a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
												found = true;
											} else {
												current = current.next;
											}
										}
									}
								}
							});
							panel.add(b);
							cur = cur.previous;
						}
					} else {
						while (cur != null) {
							if (cur.category.equals(curCategory)) {
								JButton b = new JButton ("");
								b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
								b.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String curName;
										if (!b.getText().equals("")) {
											curName = b.getText().substring(0, b.getText().length() - 5);
											DoubleNode current = new DoubleNode();
											current = food.head;
											boolean found = false;
											while (current != null && found == false) {
												if (current.name.equals(curName)) {
													frame.dispose();
													ItemMenu a = new ItemMenu();
													a.setV(true);
													a.getStack(q);
													a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
													found = true;
												} else {
													current = current.next;
												}
											}
										}
									}
								});
								panel.add(b);
							}
							cur = cur.previous;
						}
					}
				} else {
					cur = food.head;
					if (curCategory.equals("All")) {
						while (cur != null) {
							JButton b = new JButton ("");
							b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
							b.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String curName;
									if (!b.getText().equals("")) {
										curName = b.getText().substring(0, b.getText().length() - 5);
										DoubleNode current = new DoubleNode();
										current = food.head;
										boolean found = false;
										while (current != null && found == false) {
											if (current.name.equals(curName)) {
												frame.dispose();
												ItemMenu a = new ItemMenu();
												a.setV(true);
												a.getStack(q);
												a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
												found = true;
											} else {
												current = current.next;
											}
										}
									}
								}
							});
							panel.add(b);
							cur = cur.next;
						}
					} else {
						while (cur != null) {
							if (cur.category.equals(curCategory)) {
								JButton b = new JButton ("");
								b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
								b.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String curName;
										if (!b.getText().equals("")) {
											curName = b.getText().substring(0, b.getText().length() - 5);
											DoubleNode current = new DoubleNode();
											current = food.head;
											boolean found = false;
											while (current != null && found == false) {
												if (current.name.equals(curName)) {
													frame.dispose();
													ItemMenu a = new ItemMenu();
													a.setV(true);
													a.getStack(q);
													a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
													found = true;
												} else {
													current = current.next;
												}
											}
										}
									}
								});
								panel.add(b);
							}
							cur = cur.next;
						}
					}
				}
				panel.revalidate();
				panel.repaint();
			}
		});
		
		sortingBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				String curCategory = comboBox.getSelectedItem().toString();
				if (sortingBox.getSelectedItem().toString().equals("Highest Rating")) {
					comboBox.setSelectedItem(curCategory);
				} else {
					comboBox.setSelectedItem(curCategory);
				}
				panel.revalidate();
				panel.repaint();
			}
		});
		
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				sortingBox.setEnabled(false);
				comboBox.setEnabled(false);
				DoubleNode cur = new DoubleNode();
				String curCategory = comboBox.getSelectedItem().toString();
				if (searchField.getText().equals("")) {
					sortingBox.setEnabled(true);
					comboBox.setEnabled(true);
					comboBox.setSelectedItem(curCategory);
				} else {
					cur = food.tail;
					CharSequence search = searchField.getText().toLowerCase();
					if (curCategory.equals("All")) {
						while (cur != null) {
							INode temp = cur.ingredients.head;
							boolean ingredient = false;
							while (temp != null) {
								if (temp.i.toLowerCase().contains(search)) {
									ingredient = true;
									break;
								}
								temp = temp.next;
							}
							if (cur.name.toLowerCase().contains(search) || ingredient == true) {
								JButton b = new JButton ("");
								b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
								b.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String curName;
										if (!b.getText().equals("")) {
											curName = b.getText().substring(0, b.getText().length() - 5);
											DoubleNode current = new DoubleNode();
											current = food.head;
											boolean found = false;
											while (current != null && found == false) {
												if (current.name.equals(curName)) {
													frame.dispose();
													ItemMenu a = new ItemMenu();
													a.setV(true);
													a.getStack(q);
													a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
													found = true;
												} else {
													current = current.next;
												}
											}
										}
									}
								});
								panel.add(b);
							}
							cur = cur.previous;
						}
					} else {
						while (cur != null) {
							INode temp = cur.ingredients.head;
							boolean ingredient = false;
							while (temp != null) {
								if (temp.i.toLowerCase().contains(search)) {
									ingredient = true;
									break;
								}
								temp = temp.next;
							}
							if ((cur.name.toLowerCase().contains(search) || ingredient == true) && cur.category.equals(curCategory)) {
								JButton b = new JButton ("");
								b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
								b.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String curName;
										if (!b.getText().equals("")) {
											curName = b.getText().substring(0, b.getText().length() - 5);
											DoubleNode current = new DoubleNode();
											current = food.head;
											boolean found = false;
											while (current != null && found == false) {
												if (current.name.equals(curName)) {
													frame.dispose();
													ItemMenu a = new ItemMenu();
													a.setV(true);
													a.getStack(q);
													a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
													found = true;
												} else {
													current = current.next;
												}
											}
										}
									}
								});
								panel.add(b);
							}
							cur = cur.previous;
						}
					}
				}
				panel.revalidate();
				panel.repaint();
			}
		});
		
		JButton btnUndoLastAddition = new JButton("Undo Last Addition");
		btnUndoLastAddition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				q.pop();
				food.clear();
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
				} catch (FileNotFoundException e1) {
				}
				panel.removeAll();
				DoubleNode cur = new DoubleNode();
				cur = food.tail;
				while (cur != null) {
					JButton b = new JButton ("");
					b.setText(cur.name + " " + "(" + cur.rating + "*" + ")");
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String curName;
							if (!b.getText().equals("")) {
								curName = b.getText().substring(0, b.getText().length() - 5);
								DoubleNode current = new DoubleNode();
								current = food.head;
								boolean found = false;
								while (current != null && found == false) {
									if (current.name.equals(curName)) {
										frame.dispose();
										ItemMenu a = new ItemMenu();
										a.setV(true);
										a.getStack(q);
										a.buttons(current.name, current.rating, current.category, current.ingredients, 0);
										found = true;
									} else {
										current = current.next;
									}
								}
							}
						}
					});
					panel.add(b);
					cur = cur.previous;
				}
				panel.revalidate();
				panel.repaint();
			}
		});
		btnUndoLastAddition.setBounds(434, 315, 158, 29);
		frame.getContentPane().add(btnUndoLastAddition);
	}
}
