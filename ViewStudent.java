package studentManagementSystem1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ViewStudent extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 782, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(423, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Student Details");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(255, 27, 225, 52);
		desktopPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.show();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 96, 113, 32);
		desktopPane.add(btnNewButton);
		contentPane.setLayout(gl_contentPane);
		
		try {
		    String query = "select studentname, entrynumber, email, contactnumber, homecity from studentmanagementsystem";
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "ishaan");
		    Statement pst = con.createStatement();
		    ResultSet rs = pst.executeQuery(query);

		    GroupLayout layout = new GroupLayout(desktopPane);
		    desktopPane.setLayout(layout);
		    GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		    GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		    int y = 100;
		    while (rs.next()) {
		        JLabel nameLabel = new JLabel(rs.getString("studentname"));
		        JLabel entryLabel = new JLabel(rs.getString("entrynumber"));
		        JLabel emailLabel = new JLabel(rs.getString("email"));
		        JLabel contactLabel = new JLabel(rs.getString("contactnumber"));
		        JLabel homeLabel = new JLabel(rs.getString("homecity"));

		        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                .addComponent(nameLabel)
		                .addComponent(entryLabel)
		                .addComponent(emailLabel)
		                .addComponent(contactLabel)
		                .addComponent(homeLabel));
		        vGroup.addGap(5);
		        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(nameLabel)
		                .addComponent(entryLabel)
		                .addComponent(emailLabel)
		                .addComponent(contactLabel)
		                .addComponent(homeLabel))
		                .addGap(5);
		        nameLabel.setBounds(50, 300, 100, 30);
		        entryLabel.setBounds(140, 200, 100, 30);
		        emailLabel.setBounds(260, 300, 150, 30);
		        contactLabel.setBounds(420, 200, 100, 30);
		        homeLabel.setBounds(540, 440, 100, 30);
		        y += 40;
		    }

		    layout.setHorizontalGroup(
		            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addGroup(layout.createSequentialGroup()
		                            .addGap(20)
		                            .addGroup(hGroup)
		                            .addGap(20))
		    );
		    layout.setVerticalGroup(
		            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		                    .addGroup(vGroup)
		    );

		    rs.close();
		    pst.close();
		    con.close();
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, ex);
		}

		
	}
}
