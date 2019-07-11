package com.happy.Ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.happy.action.TransactionAction;
import com.happy.entities.DayBookBean;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class DayBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private DayBookBean dayBookBean;
	private TransactionAction transactionAction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayBook frame = new DayBook();
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
	public DayBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 798, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Day book", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(22, 12, 753, 310);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEntryDate = new JLabel("Entry date");
		lblEntryDate.setBounds(31, 33, 90, 15);
		panel.add(lblEntryDate);

		JDateChooser entryDate = new JDateChooser();
		entryDate.setBounds(120, 29, 133, 19);
		panel.add(entryDate);

		JLabel lblIncomeexpense = new JLabel("Income/Expense");
		lblIncomeexpense.setBounds(406, 33, 125, 15);
		panel.add(lblIncomeexpense);

		JComboBox comboType = new JComboBox();
		comboType.setModel(new DefaultComboBoxModel(new String[] { "", "Income", "Expense" }));
		comboType.setBounds(539, 28, 171, 24);
		panel.add(comboType);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(32, 86, 66, 15);
		panel.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setBounds(120, 84, 133, 19);
		panel.add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblRemarks = new JLabel("Remarks");
		lblRemarks.setBounds(31, 166, 66, 15);
		panel.add(lblRemarks);

		JTextArea areaRemarks = new JTextArea();
		areaRemarks.setBounds(120, 130, 590, 102);
		panel.add(areaRemarks);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (entryDate.getDate() != null || comboType.getSelectedItem().equals("")
						|| txtAmount.getText().equalsIgnoreCase("") || txtAmount.getText().equalsIgnoreCase("0")
						|| areaRemarks.getText().equalsIgnoreCase("")) {

					dayBookBean = new DayBookBean();
					dayBookBean.setEntryDate(entryDate.getDate());
					dayBookBean.setAmount(Double.parseDouble(txtAmount.getText()));
					if (comboType.getSelectedItem().equals("Income")) {
						dayBookBean.setTransactionType("I");
					} else if (comboType.getSelectedItem().equals("Expense")) {
						dayBookBean.setTransactionType("E");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid type selected");
					}
					dayBookBean.setRemarks(areaRemarks.getText());
					transactionAction = new TransactionAction();
					String status = transactionAction.saveDayBookEntry(dayBookBean);
					
					if(status!=null && status.equals("success")) {
						JOptionPane.showMessageDialog(null, "Entry saved");
					}
				}
			}
		});
		btnSave.setBounds(462, 259, 114, 25);
		panel.add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(606, 259, 114, 25);
		panel.add(btnExit);
	}
}
