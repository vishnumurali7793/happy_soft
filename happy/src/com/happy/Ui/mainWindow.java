package com.happy.Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.happy.entities.SalesBean;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class mainWindow extends JFrame {

	private JPanel contentPane;
	SalesBean salesBean;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow frame = new mainWindow();
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
	public mainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem addProduct = new JMenuItem("Add Product");
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProduct ap = new AddProduct();
				ap.setLocationRelativeTo(null);
				ap.setVisible(true);
			}
		});
		mnFile.add(addProduct);

		JMenuItem mntmAddAccountHeads = new JMenuItem("Add Account Heads");
		mntmAddAccountHeads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountHead ah = new AccountHead();
				ah.setVisible(true);
				ah.setLocationRelativeTo(null);
			}
		});
		mnFile.add(mntmAddAccountHeads);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Confirm",
						JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		mnFile.add(mntmExit);

		JMenu mnTransaction = new JMenu("Transaction");
		menuBar.add(mnTransaction);

		JMenuItem mntmSales = new JMenuItem("Sales");
		mntmSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sales sales = new Sales();
				sales.setVisible(true);
				sales.setLocationRelativeTo(null);
				salesBean = new SalesBean();
				salesBean.setBillType("sales");
			}
		});
		mnTransaction.add(mntmSales);

		JMenuItem mntmPurchase = new JMenuItem("Purchase");
		mntmPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Purchase purchase = new Purchase();
				purchase.setVisible(true);
				purchase.setLocationRelativeTo(null);

			}
		});
		mnTransaction.add(mntmPurchase);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.focus"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

}
