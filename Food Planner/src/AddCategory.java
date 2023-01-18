import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddCategory {

	private JFrame frame;
	private JTextField textField;

	public void setV(boolean b) {
		frame.setVisible(b);
	}
	
	Stack q = new Stack();
	public void getStack(Stack a) {
		q = a;
	}
	
	public AddCategory() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JLabel lblCategoryName = new JLabel("Category Name:");
		lblCategoryName.setBounds(93, 40, 100, 16);
		frame.getContentPane().add(lblCategoryName);
		
		textField = new JTextField();
		textField.setBounds(214, 35, 165, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnDone = new JButton("Add");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner c = new Scanner(new FileReader ("src/Categories.txt"));
					boolean fail = false;
					while (c.hasNext() &&  fail == false) {
						if (c.nextLine().toLowerCase().equals(textField.getText().toLowerCase())) {
							fail = true;
							JOptionPane.showMessageDialog(frame, "Category already added!");
						} else if (!c.hasNext() && fail == false) {
							PrintWriter pw = new PrintWriter(new FileWriter("src/Categories.txt", true));
							pw.println(textField.getText());
							pw.close();
						}
					}
					c.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.dispose();
				MainWindow a = new MainWindow();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnDone.setBounds(313, 84, 117, 29);
		frame.getContentPane().add(btnDone);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainWindow a = new MainWindow();
				a.setV(true);
				a.getStack(q);
			}
		});
		cancel.setBounds(313, 231, 117, 29);
		frame.getContentPane().add(cancel);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(93, 145, 61, 16);
		frame.getContentPane().add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		try {
			Scanner c = new Scanner(new FileReader ("src/Categories.txt"));
			c.nextLine();
			while (c.hasNext()) {
				comboBox.addItem(c.nextLine());
			}
			c.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		comboBox.setBounds(214, 141, 165, 27);
		frame.getContentPane().add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File fi = new File("src/Categories.txt");
					File t = new File("src/temp.txt");
					BufferedReader br = new BufferedReader(new FileReader("src/Categories.txt"));
					PrintWriter pw = new PrintWriter(new FileWriter(t));
					String line = null;
					boolean done = false;
					while (done == false) {
						line = br.readLine();
						if (line.trim().equals(comboBox.getSelectedItem())) {
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
				frame.dispose();
				MainWindow a = new MainWindow();
				a.setV(true);
				a.getStack(q);
			}
		});
		btnDelete.setBounds(313, 190, 117, 29);
		frame.getContentPane().add(btnDelete);
	}
}
