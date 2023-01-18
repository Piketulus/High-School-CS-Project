import java.awt.*;
import java.util.Scanner;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ItemMenu {

	private JFrame frame;
	private JTextField nameField;
	
	public void setV(boolean b) {
		frame.setVisible(b);
	}
	
	Stack q = new Stack();
	public void getStack(Stack a) {
		q = a;
	}
	
	public ItemMenu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
	}
	
	String curname;
	
	public void buttons (String n, int r, String c, IngredientsLL i, int x) {
		JLabel name = new JLabel("Name: ");
		name.setBounds(110, 6, 312, 27);
		frame.getContentPane().add(name);
		
		nameField = new JTextField(n);
		nameField.setBounds(150, 6, 170, 26);
		nameField.setEditable(false);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel rating = new JLabel("Rating: ");
		rating.setBounds(40, 63, 96, 16);
		frame.getContentPane().add(rating);
		
		JComboBox ratingBox = new JComboBox();
		ratingBox.addItem("1");
		ratingBox.addItem("2");
		ratingBox.addItem("3");
		ratingBox.addItem("4");
		ratingBox.addItem("5");
		ratingBox.setSelectedIndex(r-1);
		ratingBox.setEnabled(false);
		ratingBox.setBounds(100, 60, 63, 29);
		frame.getContentPane().add(ratingBox);
		
		JLabel category = new JLabel("Category: ");
		category.setBounds(200, 63, 173, 16);
		frame.getContentPane().add(category);
		
		JComboBox categoryBox = new JComboBox();
		boolean inList = false;
		try {
			Scanner s = new Scanner(new FileReader ("src/Categories.txt"));
			s.nextLine();
			while (s.hasNext()) {
				String temp = s.nextLine();
				if (!temp.equals("All")) {
					categoryBox.addItem(temp);
				}
				if (temp.equals(c)) {
					inList = true;
				}
			}
			s.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if (inList) {
			categoryBox.setSelectedItem(c);
		} else {
			categoryBox.addItem(c);
			categoryBox.setSelectedItem(c);
		}
		categoryBox.setEnabled(false);
		categoryBox.setBounds(270, 60, 149, 29);
		frame.getContentPane().add(categoryBox);
		JLabel image = new JLabel();
		image.setBounds(198, 103, 225, 146);
		ImageIcon p = new ImageIcon("src/Images/" + n + ".jpg");
		Image tempi = p.getImage();
		Image tempi2 = tempi.getScaledInstance(225, 146, java.awt.Image.SCALE_SMOOTH);
		p = new ImageIcon(tempi2);
		image.setIcon(p);
		frame.getContentPane().add(image);
		
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setBounds(40, 87, 86, 16);
		frame.getContentPane().add(lblIngredients);
		
		JTextArea ingredients = new JTextArea();
		INode temp = i.head;
		while (temp != null) {
			ingredients.append(temp.i + "\n");
			temp = temp.next;
		}
		ingredients.setBounds(40, 105, 146, 400);
		ingredients.setEditable(false);
		frame.getContentPane().add(ingredients);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x == 0) {	
					frame.dispose();
					MainWindow a = new MainWindow();
					a.setV(true);
					a.getStack(q);
				} else {
					frame.dispose();
					Planner a = new Planner();
					a.setV(true);
					a.getStack(q);
				}
			}
		});
		btnBack.setBounds(327, 543, 117, 29);
		frame.getContentPane().add(btnBack);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File temp = new File("src/tempImages");
					File[] img = temp.listFiles();
					if (img.length <= 2) {
						PrintWriter pw = new PrintWriter(new FileWriter("src/FoodItems.txt", true));	
						pw.println(nameField.getText());
						pw.println(ratingBox.getSelectedItem());
						pw.println(categoryBox.getSelectedItem());
						pw.println(ingredients.getText());
						pw.println("Stop");
						pw.close();
						File curimg = new File("src/Images/" + curname + ".jpg");
						curimg.renameTo(new File ("src/Images/" + nameField.getText() + ".jpg"));
						frame.dispose();
						MainWindow a = new MainWindow();
						a.setV(true);
						a.getStack(q);
					}
					if (img.length == 2) {
						img[1].renameTo(new File("src/Images/" + nameField.getText() + ".jpg"));
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
			}
		});
		btnSave.setBounds(327, 543, 117, 29);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(113, 543, 117, 29);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel = new JLabel("Image will be shown after saving");
		lblNewLabel.setBounds(225, 342, 218, 47);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JButton btnEditImage = new JButton("Edit Image");
		btnEditImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "Add or remove 1 image from the folder that will pop up.", "Images", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (n == JOptionPane.OK_OPTION) {
					String curName = nameField.getText();
					File imgs = new File("src/Images");
					File[] list = imgs.listFiles();
					int loc = -1;
					for (int i = 0; i < list.length; i++) {
						if (list[i].getName().substring(0, list[i].getName().length() - 4).equals(curName)) {
							loc = i;
						}
					}
					if (loc != -1) {
						list[loc].renameTo(new File("src/tempImages/" + nameField.getText() + ".jpg"));
					}
					try {
						Desktop.getDesktop().open(new File("src/tempImages"));
						lblNewLabel.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEditImage.setBounds(260, 320, 117, 29);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curname = nameField.getText();
				btnEdit.setBounds(327, 543, 117, 29);
				frame.getContentPane().add(btnSave);
				frame.getContentPane().remove(btnBack);
				frame.getContentPane().remove(btnEdit);
				frame.getContentPane().remove(btnDelete);
				frame.getContentPane().add(btnEditImage);
				nameField.setEditable(true);
				ratingBox.setEnabled(true);
				categoryBox.setEnabled(true);
				ingredients.setEditable(true);
				try {
					File fi = new File("src/FoodItems.txt");
					File t = new File("src/temp.txt");
					BufferedReader br = new BufferedReader(new FileReader("src/FoodItems.txt"));
					PrintWriter pw = new PrintWriter(new FileWriter(t));
					String line = null;
					boolean done = false;
					while (done == false) {
						line = br.readLine();
						if (line.trim().equals(nameField.getText())) {
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
					e1.printStackTrace();
				}
			}
		});
		btnEdit.setBounds(220, 543, 117, 29);
		frame.getContentPane().add(btnEdit);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File fi = new File("src/FoodItems.txt");
					File t = new File("src/temp.txt");
					BufferedReader br = new BufferedReader(new FileReader("src/FoodItems.txt"));
					PrintWriter pw = new PrintWriter(new FileWriter(t));
					String line = null;
					boolean done = false;
					while (done == false) {
						line = br.readLine();
						if (line.trim().equals(nameField.getText())) {
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
					frame.dispose();
					MainWindow a = new MainWindow();
					a.setV(true);
					a.getStack(q);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
}
