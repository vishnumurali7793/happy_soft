package com.happy.Ui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.happy.action.MasterAction;
import com.happy.entities.AccountHeadBean;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountHead extends JFrame {

	private JPanel contentPane;
	private JTextField txtAccName;
	private JTextField txtAccPhone;
	private JTextField txtAccNo;
	private JTextField txtIfsc;
	private JTextField txtAddress;
	private JTextField txtPan;
	private JTextField txtGstin;
	
	AccountHeadBean head = new AccountHeadBean();
	MasterAction masterAction = new MasterAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountHead frame = new AccountHead();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountHead() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 876, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account Head", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(34, 30, 811, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 51, 51), 1, true));
		panel_1.setBounds(46, 44, 720, 197);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(41, 26, 66, 15);
		panel_1.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(41, 73, 66, 15);
		panel_1.add(lblPhone);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(41, 114, 121, 15);
		panel_1.add(lblAccountNumber);
		
		JLabel lblIfscCode = new JLabel("IFSC Code");
		lblIfscCode.setBounds(41, 156, 121, 15);
		panel_1.add(lblIfscCode);
		
		txtAccName = new JTextField();
		txtAccName.setBounds(246, 24, 158, 19);
		panel_1.add(txtAccName);
		txtAccName.setColumns(10);
		
		txtAccPhone = new JTextField();
		txtAccPhone.setBounds(246, 71, 158, 19);
		panel_1.add(txtAccPhone);
		txtAccPhone.setColumns(10);
		
		txtAccNo = new JTextField();
		txtAccNo.setBounds(246, 112, 158, 19);
		panel_1.add(txtAccNo);
		txtAccNo.setColumns(10);
		
		txtIfsc = new JTextField();
		txtIfsc.setBounds(246, 154, 158, 19);
		panel_1.add(txtIfsc);
		txtIfsc.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(446, 26, 66, 15);
		panel_1.add(lblType);
		
		JComboBox comboType = new JComboBox();
		comboType.setModel(new DefaultComboBoxModel(new String[] {"-select one-", "Customer", "Supplier"}));
		comboType.setBounds(515, 21, 139, 24);
		panel_1.add(comboType);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(95, 283, 66, 15);
		panel.add(lblAddress);
		
		JLabel lblPanNumber = new JLabel("PAN Number");
		lblPanNumber.setBounds(95, 330, 110, 15);
		panel.add(lblPanNumber);
		
		JLabel lblGstin = new JLabel("GSTIN");
		lblGstin.setBounds(95, 371, 66, 15);
		panel.add(lblGstin);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(295, 281, 290, 19);
		panel.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPan = new JTextField();
		txtPan.setBounds(295, 326, 290, 19);
		panel.add(txtPan);
		txtPan.setColumns(10);
		
		txtGstin = new JTextField();
		txtGstin.setBounds(295, 369, 290, 19);
		panel.add(txtGstin);
		txtGstin.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(712, 417, 87, 25);
		panel.add(btnExit);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status = null;

				try {
					if (txtAccName.getText().isEmpty() || txtAccNo.getText().isEmpty()
							|| txtAccPhone.getText().isEmpty() || txtIfsc.getText().isEmpty()
							|| comboType.getSelectedIndex() == -1
							|| comboType.getSelectedItem().equals("-select one-")) {
						JOptionPane.showMessageDialog(null, "All fields inside the red rectangle are mandatory!");
						
					} else {
						head.setHeadName(txtAccName.getText());
						head.setHeadAccountNo(txtAccNo.getText());
						head.setHeadPhone(txtAccPhone.getText());
						head.setHeadIfscCode(txtIfsc.getText());
						head.setHeadType(comboType.getSelectedItem().toString());
						head.setHeadAddress(txtAddress.getText());
						head.setHeadPanNo(txtPan.getText());
						head.setHeadGstin(txtGstin.getText());

						status = masterAction.saveAccountHead(head);
					}

					if (status.equals("success")) {
						JOptionPane.showMessageDialog(null, "Saved successfully!");
						txtAccName.setText("");
						txtAccPhone.setText("");
						txtAccNo.setText("");
						txtIfsc.setText("");
						comboType.setSelectedIndex(-1);
						txtAddress.setText("");
						txtPan.setText("");
						txtGstin.setText("");
					}
					if (status.equals("failed")) {
						JOptionPane.showMessageDialog(null, "Some error occured, please try again.");
						txtAccName.setText("");
						txtAccPhone.setText("");
						txtAccNo.setText("");
						txtIfsc.setText("");
						comboType.setSelectedIndex(-1);
						txtAddress.setText("");
						txtPan.setText("");
						txtGstin.setText("");
					}

				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(606, 417, 95, 25);
		panel.add(btnSave);
	}
}
