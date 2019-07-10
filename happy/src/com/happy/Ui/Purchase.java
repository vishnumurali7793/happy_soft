package com.happy.Ui;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.happy.action.TransactionAction;
import com.happy.entities.AccountHeadBean;
import com.happy.entities.ProductBean;
import com.happy.entities.PurchaseBean;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Purchase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBillNo;
	private JTextField txtSupplierCode;
	private JTextField txtSupplierName;
	private JTable table;
	private JTable table_1;
	private JTextField txtSubTotal;
	private JTextField txtDiscount;
	private JLabel lblGrantTotalValue;

	private List<ProductBean> list = new ArrayList<>();
	private TransactionAction transactionAction = new TransactionAction();
	private ProductBean productBean;
	private String supplierCode;
	private String supplierName;
	private Double subTotal = 0.0;
	private PurchaseBean purchaseBean;
	private AccountHeadBean accountHeadBean;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase frame = new Purchase();
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
	public Purchase() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 954, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Purchase", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 919, 183);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBillNo = new JLabel("Bill No.");
		lblBillNo.setBounds(24, 27, 66, 15);
		panel.add(lblBillNo);

		txtBillNo = new JTextField();
		txtBillNo.setBounds(87, 23, 124, 19);
		panel.add(txtBillNo);
		txtBillNo.setColumns(10);

		JLabel lblBillDate = new JLabel("Bill Date");
		lblBillDate.setBounds(24, 79, 66, 15);
		panel.add(lblBillDate);

		JDateChooser dateBillDate = new JDateChooser();
		dateBillDate.setBounds(87, 75, 124, 19);
		panel.add(dateBillDate);

		JLabel lblPurchaseDate = new JLabel("Purchase date");
		lblPurchaseDate.setBounds(24, 133, 108, 15);
		panel.add(lblPurchaseDate);

		JDateChooser datePurchaseDate = new JDateChooser();
		datePurchaseDate.setBounds(133, 129, 124, 19);
		panel.add(datePurchaseDate);

		JLabel lblSupplierCode = new JLabel("Supplier code");
		lblSupplierCode.setBounds(333, 27, 94, 15);
		panel.add(lblSupplierCode);

		txtSupplierCode = new JTextField();
		txtSupplierCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					AccountHeadSelection ahs = new AccountHeadSelection();
					ahs.setVisible(true);
					dispose();
				}
			}
		});
		txtSupplierCode.setBounds(445, 23, 124, 19);
		panel.add(txtSupplierCode);
		txtSupplierCode.setColumns(10);

		JLabel lblSupplierName = new JLabel("Supplier name");
		lblSupplierName.setBounds(333, 79, 108, 15);
		panel.add(lblSupplierName);

		txtSupplierName = new JTextField();
		txtSupplierName.setBounds(445, 77, 124, 19);
		panel.add(txtSupplierName);
		txtSupplierName.setColumns(10);

		JLabel lblPurchaseType = new JLabel("Purchase type");
		lblPurchaseType.setBounds(333, 133, 108, 15);
		panel.add(lblPurchaseType);

		JComboBox comboPurchType = new JComboBox();
		comboPurchType.setModel(new DefaultComboBoxModel(new String[] { "", "Cash", "Credit" }));
		comboPurchType.setBounds(445, 128, 124, 24);
		panel.add(comboPurchType);

		JRadioButton radioDiscount = new JRadioButton("Enable discount");
		radioDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioDiscount.isSelected()) {
					txtDiscount.setEditable(true);
				}
				if (!radioDiscount.isSelected()) {
					txtDiscount.setEditable(false);
				}
			}
		});
		radioDiscount.setBounds(675, 23, 144, 23);
		panel.add(radioDiscount);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(153, 204, 255)));
		panel_1.setBounds(12, 207, 919, 172);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 895, 148);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table.getSelectedRow();
					int value = (int) table.getValueAt(row, 0);
					productBean = new ProductBean();
					productBean.setProductId(value);
					productBean = transactionAction.getProductById(productBean);
					addproducts(productBean);
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item code", "Item name", "Category", "Price", "Unit" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(275);
		table.getColumnModel().getColumn(2).setPreferredWidth(137);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_2.setBounds(12, 391, 919, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 12, 895, 134);
		panel_2.add(scrollPane_1);

		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table_1.getSelectedRow();
					double quantity = Double.parseDouble(table_1.getValueAt(row, 4).toString());
					double price = Double.parseDouble(table_1.getValueAt(row, 3).toString());
					double itemTotal = transactionAction.calculateTotal(quantity, price);
					if (itemTotal != 0) {
						table_1.setValueAt(itemTotal, row, 5);
						setSubTotal(itemTotal);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid quantity");
					}
				}
			}
		});
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item code", "Item name", "Unit", "Price", "Quantity", "Total" }));
		table_1.getColumnModel().getColumn(1).setPreferredWidth(240);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(113);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(96);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(86);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(88);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(12, 561, 919, 131);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblSubTotal = new JLabel("Sub total");
		lblSubTotal.setBounds(670, 23, 66, 15);
		panel_3.add(lblSubTotal);

		txtSubTotal = new JTextField();
		txtSubTotal.setBounds(754, 21, 124, 19);
		panel_3.add(txtSubTotal);
		txtSubTotal.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount");
		lblDiscount.setBounds(670, 67, 66, 15);
		panel_3.add(lblDiscount);

		txtDiscount = new JTextField();
		txtDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					double discount = Double.parseDouble(txtDiscount.getText());
					double subTotal = Double.parseDouble(txtSubTotal.getText());
					lblGrantTotalValue.setText(String.valueOf(subTotal-discount));
				}
			}
		});
		txtDiscount.setBounds(754, 65, 124, 19);
		panel_3.add(txtDiscount);
		txtDiscount.setColumns(10);
		txtDiscount.setEditable(false);

		JLabel lblGrantTotal = new JLabel("Grant Total : ");
		lblGrantTotal.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGrantTotal.setBounds(27, 67, 157, 15);
		panel_3.add(lblGrantTotal);

		lblGrantTotalValue = new JLabel("");
		lblGrantTotalValue.setFont(new Font("Dialog", Font.BOLD, 20));
		lblGrantTotalValue.setBounds(196, 67, 190, 15);
		panel_3.add(lblGrantTotalValue);

		JButton btnSaveBill = new JButton("Save bill");
		btnSaveBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBillNo.getText() != null
						|| !txtBillNo.getText().isEmpty() && dateBillDate.getDate() != null
								&& txtSupplierCode.getText() != null
						|| !txtSupplierCode.getText().isEmpty() && txtSupplierName.getText() != null
						|| !txtSupplierName.getText().isEmpty() && comboPurchType.getSelectedIndex() != -1) {

					purchaseBean = new PurchaseBean();
					accountHeadBean = new AccountHeadBean();
					accountHeadBean = transactionAction.getHeadsByHeadCode(txtSupplierCode.getText().toString());
					purchaseBean.setBillNo(txtBillNo.getText().toString());
					purchaseBean.setBillDate(dateBillDate.getDate());
					purchaseBean.setPurchDate(datePurchaseDate.getDate());
					purchaseBean.setSupplierHeadId(new AccountHeadBean());
					purchaseBean.getSupplierHeadId().setHeadId(accountHeadBean.getHeadId());
					purchaseBean.setPaymentType(comboPurchType.getSelectedItem().toString());
					purchaseBean.setNetAmount(Double.parseDouble(lblGrantTotalValue.getText()));
					purchaseBean.setSubTotal(Double.parseDouble(txtSubTotal.getText()));
					if (radioDiscount.isSelected()) {
						purchaseBean.setDiscountEnabled("Y");
						purchaseBean.setDiscount(Double.parseDouble(txtDiscount.getText()));
					} else {
						purchaseBean.setDiscountEnabled("N");
					}
					transactionAction.savePurchase(purchaseBean);
					JOptionPane.showMessageDialog(null, "Bill saved!");
				} else {
					JOptionPane.showMessageDialog(null, "All fields are mandatory");
				}
			}
		});
		btnSaveBill.setBounds(594, 725, 114, 25);
		contentPane.add(btnSaveBill);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(745, 725, 114, 25);
		contentPane.add(btnExit);
		datePurchaseDate.setDate(new Date());
		getProductList();
	}

	public void getProductList() {
		Integer id;
		String name, unit, category;
		Double price;
		list = (ArrayList<ProductBean>) transactionAction.getProductList();
		for (ProductBean product : list) {
			id = product.getProductId();
			name = product.getProductName();
			unit = product.getSellingUnit();
			price = product.getSellingPrice();
			category = product.getProductType();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { id, name, category, price, unit });
		}
	}

	public void addproducts(ProductBean productBean) {
		String name, unit, quantity, code;
		Double unitPrice, totalPrice;
		code = productBean.getProductCode();
		name = productBean.getProductName();
		unit = productBean.getSellingUnit();
		quantity = "";
		unitPrice = productBean.getSellingPrice();
		totalPrice = 0.0;
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.addRow(new Object[] { code, name, unit, unitPrice, quantity, totalPrice });
		new Sales();
	}

	public void refresh() {
		txtSupplierCode.setText(supplierCode);
		txtSupplierName.setText(supplierName);
	}

	public void setSubTotal(double itemTotal) {
		subTotal += itemTotal;
		txtSubTotal.setText(String.valueOf(subTotal));
		lblGrantTotalValue.setText(String.valueOf(subTotal));
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
