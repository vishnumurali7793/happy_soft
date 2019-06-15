package com.happy.Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.happy.entities.ProductBean;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Sales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JTextField txtBillNo;
	private JTextField textCustomerName;
	private JTextField textAddress;
	private JTextField textPhone;
	private JTextField textSubTotal;
	private JTextField textDiscount;
	private ArrayList<ProductBean> list = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales frame = new Sales();
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
	public Sales() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1102, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 1078, 677);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 206, 1054, 313);
		panel.add(scrollPane);

		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					SelectionWindow window = new SelectionWindow();
					window.getProducts();
					window.setVisible(true);
					dispose();

				}
			}
		});
		table_1.setShowGrid(false);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item name", "Selling unit", "Unit price", "Quantity", "Total" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(297);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(117);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(116);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane.setViewportView(table_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sales", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 12, 1054, 179);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblBillNo = new JLabel("Bill No.");
		lblBillNo.setBounds(12, 42, 66, 15);
		panel_1.add(lblBillNo);

		txtBillNo = new JTextField();
		txtBillNo.setBounds(83, 40, 124, 19);
		panel_1.add(txtBillNo);
		txtBillNo.setColumns(10);

		JLabel lblBillDate = new JLabel("Bill date");
		lblBillDate.setBounds(12, 113, 66, 15);
		panel_1.add(lblBillDate);

		JDateChooser dateBill = new JDateChooser();
		dateBill.setBounds(83, 109, 124, 19);
		panel_1.add(dateBill);

		JLabel lblCustomerName = new JLabel("Customer name");
		lblCustomerName.setBounds(325, 42, 124, 15);
		panel_1.add(lblCustomerName);

		textCustomerName = new JTextField();
		textCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					SelectionWindow window = new SelectionWindow();
					window.getProducts();
					window.setVisible(true);
					dispose();

				}
			}
		});
		textCustomerName.setBounds(460, 40, 217, 19);
		panel_1.add(textCustomerName);
		textCustomerName.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(325, 113, 66, 15);
		panel_1.add(lblAddress);

		textAddress = new JTextField();
		textAddress.setBounds(409, 109, 268, 19);
		panel_1.add(textAddress);
		textAddress.setColumns(10);

		JLabel lblCustomerPhone = new JLabel("Customer phone");
		lblCustomerPhone.setBounds(739, 42, 124, 15);
		panel_1.add(lblCustomerPhone);

		textPhone = new JTextField();
		textPhone.setBounds(881, 40, 161, 19);
		panel_1.add(textPhone);
		textPhone.setColumns(10);

		JRadioButton rdbtnEnableDiscount = new JRadioButton("Enable Discount");
		rdbtnEnableDiscount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textDiscount.setEditable(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textDiscount.setEditable(false);
				}
			}
		});
		rdbtnEnableDiscount.setBounds(739, 107, 144, 23);
		panel_1.add(rdbtnEnableDiscount);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 0, 0)));
		panel_2.setBounds(12, 531, 1054, 91);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTotal = new JLabel("TOTAL : ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotal.setBounds(22, 36, 99, 15);
		panel_2.add(lblTotal);

		JLabel lblNetAmt = new JLabel("");
		lblNetAmt.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNetAmt.setBounds(117, 36, 126, 15);
		panel_2.add(lblNetAmt);

		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(831, 20, 52, 15);
		panel_2.add(lblTotal_1);

		textSubTotal = new JTextField();
		textSubTotal.setBounds(908, 18, 124, 19);
		panel_2.add(textSubTotal);
		textSubTotal.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(831, 51, 66, 15);
		panel_2.add(lblDiscount);

		textDiscount = new JTextField();
		textDiscount.setBounds(908, 49, 124, 19);
		textDiscount.setEditable(false);
		panel_2.add(textDiscount);
		textDiscount.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(736, 634, 114, 25);
		panel.add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(890, 634, 114, 25);
		panel.add(btnExit);

	}

	
	public void addproducts(ProductBean productBean) {

		list.add(productBean);

		String name, unit, quantity;
		Double unitPrice, totalPrice;

		for (ProductBean product : list) {
			name = product.getProductName();
			unit = product.getSellingUnit();
			quantity = "";
			unitPrice = product.getSellingPrice();
			totalPrice = 0.0;

			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(new Object[] { name, unit, unitPrice, quantity, totalPrice });
			new Sales();
		}

	}

	public ArrayList<ProductBean> getList() {
		return list;
	}

	public void setList(ArrayList<ProductBean> list) {
		this.list = list;
	}
	
	public Sales(ArrayList<ProductBean> list) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1102, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 1078, 677);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 206, 1054, 313);
		panel.add(scrollPane);

		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					SelectionWindow window = new SelectionWindow();
					window.getProducts();
					window.setVisible(true);
					dispose();

				}
			}
		});
		table_1.setShowGrid(false);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item name", "Selling unit", "Unit price", "Quantity", "Total" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(297);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(117);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(116);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPane.setViewportView(table_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Sales", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 12, 1054, 179);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblBillNo = new JLabel("Bill No.");
		lblBillNo.setBounds(12, 42, 66, 15);
		panel_1.add(lblBillNo);

		txtBillNo = new JTextField();
		txtBillNo.setBounds(83, 40, 124, 19);
		panel_1.add(txtBillNo);
		txtBillNo.setColumns(10);

		JLabel lblBillDate = new JLabel("Bill date");
		lblBillDate.setBounds(12, 113, 66, 15);
		panel_1.add(lblBillDate);

		JDateChooser dateBill = new JDateChooser();
		dateBill.setBounds(83, 109, 124, 19);
		panel_1.add(dateBill);

		JLabel lblCustomerName = new JLabel("Customer name");
		lblCustomerName.setBounds(325, 42, 124, 15);
		panel_1.add(lblCustomerName);

		textCustomerName = new JTextField();
		textCustomerName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					SelectionWindow window = new SelectionWindow();
					window.getProducts();
					window.setVisible(true);

				}
			}
		});
		textCustomerName.setBounds(460, 40, 217, 19);
		panel_1.add(textCustomerName);
		textCustomerName.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(325, 113, 66, 15);
		panel_1.add(lblAddress);

		textAddress = new JTextField();
		textAddress.setBounds(409, 109, 268, 19);
		panel_1.add(textAddress);
		textAddress.setColumns(10);

		JLabel lblCustomerPhone = new JLabel("Customer phone");
		lblCustomerPhone.setBounds(739, 42, 124, 15);
		panel_1.add(lblCustomerPhone);

		textPhone = new JTextField();
		textPhone.setBounds(881, 40, 161, 19);
		panel_1.add(textPhone);
		textPhone.setColumns(10);

		JRadioButton rdbtnEnableDiscount = new JRadioButton("Enable Discount");
		rdbtnEnableDiscount.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textDiscount.setEditable(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textDiscount.setEditable(false);
				}
			}
		});
		rdbtnEnableDiscount.setBounds(739, 107, 144, 23);
		panel_1.add(rdbtnEnableDiscount);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 0, 0)));
		panel_2.setBounds(12, 531, 1054, 91);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTotal = new JLabel("TOTAL : ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTotal.setBounds(22, 36, 99, 15);
		panel_2.add(lblTotal);

		JLabel lblNetAmt = new JLabel("");
		lblNetAmt.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNetAmt.setBounds(117, 36, 126, 15);
		panel_2.add(lblNetAmt);

		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(831, 20, 52, 15);
		panel_2.add(lblTotal_1);

		textSubTotal = new JTextField();
		textSubTotal.setBounds(908, 18, 124, 19);
		panel_2.add(textSubTotal);
		textSubTotal.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(831, 51, 66, 15);
		panel_2.add(lblDiscount);

		textDiscount = new JTextField();
		textDiscount.setBounds(908, 49, 124, 19);
		textDiscount.setEditable(false);
		panel_2.add(textDiscount);
		textDiscount.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(736, 634, 114, 25);
		panel.add(btnSave);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(890, 634, 114, 25);
		panel.add(btnExit);

		for (ProductBean product : list) {
			String name = product.getProductName();
			String unit = product.getSellingUnit();
			String quantity = "";
			Double unitPrice = product.getSellingPrice();
			Double totalPrice = 0.0;

			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(new Object[] { name, unit, unitPrice, quantity, totalPrice });
		}
	}
}
