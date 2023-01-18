import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class AddFood {

	private JFrame frame;
	private JTextField textField;
	public Stack s = new Stack();

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
	
	public AddFood() {
		getItems();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(49, 27, 61, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setBounds(49, 120, 75, 16);
		frame.getContentPane().add(lblIngredients);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setBounds(289, 27, 61, 16);
		frame.getContentPane().add(lblRating);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(289, 120, 61, 16);
		frame.getContentPane().add(lblCategory);
		
		textField = new JTextField();
		textField.setBounds(49, 48, 164, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(289, 49, 155, 26);
		comboBox.addItem("1");
		comboBox.addItem("2");
		comboBox.addItem("3");
		comboBox.addItem("4");
		comboBox.addItem("5");
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(289, 141, 155, 26);
		try {
			Scanner c = new Scanner(new FileReader ("src/Categories.txt"));
			c.nextLine();
			while (c.hasNext()) {
				String temp = c.nextLine();
				if (!temp.equals("All")) {
					comboBox_1.addItem(temp);
				}
			}
			c.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		frame.getContentPane().add(comboBox_1);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setBounds(49, 142, 164, 400);
		frame.getContentPane().add(txtArea);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					DoubleNode cur = food.head;
					boolean dupe = false;
					while (cur != null && dupe == false) {
						if (cur.name.toLowerCase().trim().equals(textField.getText().toLowerCase().trim())) {
							dupe = true;
						} else {
							cur = cur.next;
						}
					}
					if (!dupe) {
						try {
							File temp = new File("src/tempImages");
							File[] img = temp.listFiles();
							if (img.length <= 2) {
								PrintWriter pw = new PrintWriter(new FileWriter("src/FoodItems.txt", true));	
								pw.println(textField.getText().trim());
								pw.println(comboBox.getSelectedItem());
								pw.println(comboBox_1.getSelectedItem());
								pw.println(txtArea.getText());
								pw.println("Stop");
								pw.close();
								q.add(textField.getText().trim());
								frame.dispose();
								MainWindow a = new MainWindow();
								a.setV(true);
								a.getStack(q);
							}
							if (img.length == 2) {
								img[1].renameTo(new File("src/Images/" + textField.getText() + ".jpg"));
							} else if (img.length > 2) {
								JOptionPane.showMessageDialog(frame, "Too many images");
								try {
									Desktop.getDesktop().open(new File("src/tempImages"));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Food name already exists");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "You must at least add a name");
				}
			}
		});
		btnDone.setBounds(327, 543, 117, 29);
		frame.getContentPane().add(btnDone);
		
		JButton btnAddVategory = new JButton("Add Category");
		btnAddVategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddCategory a = new AddCategory();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnAddVategory.setBounds(327, 509, 117, 29);
		frame.getContentPane().add(btnAddVategory);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow a = new MainWindow();
				a.setV(true);
				a.getStack(q);
			}
		});
		cancel.setBounds(214, 543, 117, 29);
		frame.getContentPane().add(cancel);
		
		JLabel lblNewLabel = new JLabel("Image will be shown after saving");
		lblNewLabel.setBounds(225, 292, 218, 47);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnAddImage = new JButton("Add Image");
		btnAddImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "Add or remove 1 image from the folder that will pop up.", "Images", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (n == JOptionPane.OK_OPTION) {
					try {
						Desktop.getDesktop().open(new File("src/tempImages"));
						lblNewLabel.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAddImage.setBounds(275, 251, 117, 29);
		frame.getContentPane().add(btnAddImage);
		
	}
}
