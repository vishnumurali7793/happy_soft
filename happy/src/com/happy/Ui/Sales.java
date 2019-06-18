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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.happy.action.TransactionAction;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private List<ProductBean> list = new ArrayList<>();
	private JTable table;
	JLabel lblNetAmt;

	private String custName;
	private String custAddress;
	private String custPhone;

	TransactionAction transactionAction = new TransactionAction();
	ProductBean productBean;

	private double subTotal;

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
		scrollPane.setBounds(12, 374, 1054, 145);
		panel.add(scrollPane);

		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table_1.getSelectedRow();
					double quantity = Double.parseDouble(table_1.getValueAt(row, 3).toString());
					double price = Double.parseDouble(table_1.getValueAt(row, 2).toString());
					double itemTotal = transactionAction.calculateTotal(quantity, price);
					if (itemTotal != 0) {
						table_1.setValueAt(itemTotal, row, 4);
						setSubTotal(itemTotal);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid quantity");
					}
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
		panel_1.setBounds(12, 12, 1054, 153);
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
					AccountHeadSelection ahs = new AccountHeadSelection();
					ahs.setVisible(true);
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

		lblNetAmt = new JLabel("");
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

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		panel_3.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_3.setBounds(12, 172, 1054, 190);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JScrollPane scrollPaneSelectFrom = new JScrollPane();
		scrollPaneSelectFrom.setBounds(12, 12, 1030, 166);
		panel_3.add(scrollPaneSelectFrom);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();
					int value = (int) table.getValueAt(row, column);
					productBean = new ProductBean();
					productBean.setProductId(value);
					productBean = transactionAction.getProductById(productBean);
					addproducts(productBean);
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item ID", "Item name", "Category", "Price", "Unit" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(337);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		scrollPaneSelectFrom.setViewportView(table);
		getProductList();
		dateBill.setDate(new Date());
		setBillNo();
	}

	public void addproducts(ProductBean productBean) {
		String name, unit, quantity;
		Double unitPrice, totalPrice;
		name = productBean.getProductName();
		unit = productBean.getSellingUnit();
		quantity = "";
		unitPrice = productBean.getSellingPrice();
		totalPrice = 0.0;
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.addRow(new Object[] { name, unit, unitPrice, quantity, totalPrice });
		new Sales();
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

	public void setSubTotal(double itemTotal) {
		subTotal += itemTotal;
		textSubTotal.setText(String.valueOf(subTotal));
		lblNetAmt.setText(String.valueOf(subTotal));
	}

	public void refresh() {
		textCustomerName.setText(custName);
		textPhone.setText(custPhone);
		textAddress.setText(custAddress);

	}

	public void setBillNo() {
		int billNo = transactionAction.getSalesCount();
		if (billNo < 1 || billNo == 0) {
			txtBillNo.setText("S/" + 1);
		} else {
			txtBillNo.setText("S/" + (billNo + 1));
		}
	}

	public String getCustName() {
		return custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
}
