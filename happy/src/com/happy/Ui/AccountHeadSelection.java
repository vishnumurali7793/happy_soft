package com.happy.Ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.happy.action.TransactionAction;
import com.happy.entities.AccountHeadBean;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AccountHeadSelection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private TransactionAction trAction;
	private ArrayList<AccountHeadBean> accountList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountHeadSelection frame = new AccountHeadSelection();
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
	public AccountHeadSelection() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Select Customer/Supplier", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(27, 24, 670, 348);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(333, 22, 3, 3);
		panel.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 42, 607, 270);
		panel.add(scrollPane_1);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table.getSelectedRow();
					int column = 0;
					int headId = (int) table.getValueAt(row, column);
					trAction.getHeadByIdforSales(headId);
					dispose();
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Head code", "Account head name", "Phone" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(123);
		table.getColumnModel().getColumn(2).setPreferredWidth(325);
		table.getColumnModel().getColumn(3).setPreferredWidth(157);
		scrollPane_1.setViewportView(table);
		getAccountHeads();
	}

	public void getAccountHeads() {
		trAction = new TransactionAction();
		accountList = trAction.getAccountHeads();

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int id;
		String headCode, accountName, phone;
		for (AccountHeadBean headBean : accountList) {
			id = headBean.getHeadId();
			headCode = headBean.getHeadCode();
			accountName = headBean.getHeadName();
			phone = headBean.getHeadPhone();
			tableModel.addRow(new Object[] { id, headCode, accountName, phone });
		}
	}
}
