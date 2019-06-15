package com.happy.Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import com.happy.dao.LoginDao;
import com.happy.entities.LoginBean;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPassword;
	LoginBean login = new LoginBean();
	LoginDao loginDao = new LoginDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(45, 51, 354, 164);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(177, 5, 0, 0);
		panel.add(label);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(48, 42, 78, 15);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 69, 66, 15);
		panel.add(lblPassword);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(132, 40, 156, 19);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(132, 67, 156, 19);
		panel.add(passwordFieldPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginBean log;
				
				if(textFieldUserName.getText().equals("")||passwordFieldPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Username/Password Incorrect");
				}else {
					login.setUserName(textFieldUserName.getText());
					login.setPassWord(passwordFieldPassword.getText());
					
					log = loginDao.authenticateUser(login);
					
					if(log!=null) {
						dispose();
						mainWindow mw = new mainWindow();
						mw.setExtendedState(JFrame.MAXIMIZED_BOTH);
						mw.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Username/Password Incorrect");
						textFieldUserName.setText("");
						passwordFieldPassword.setText("");
					}
				}
			}
		});
		btnLogin.setBounds(216, 114, 72, 25);
		panel.add(btnLogin);
	}
}
