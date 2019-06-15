package com.happy.Ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.happy.action.TransactionAction;
import com.happy.dao.TransactionDao;
import com.happy.entities.ProductBean;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	TransactionAction transaction = new TransactionAction();
	TransactionDao transactionDao;
	ProductBean productBean;
	private ArrayList<ProductBean> list;

	private Integer productId;
	private String productName;
	private String productType;
	private String sellingUnit;
	private Double sellingPrice;
	private JTable saleTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionWindow frame = new SelectionWindow();
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
	public SelectionWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 923, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 30, 899, 178);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Integer rowCount = table.getSelectedRow();
						Integer columnCount = table.getSelectedColumn();
						Integer productId = Integer.parseInt(table.getValueAt(rowCount, columnCount).toString());
						productBean = new ProductBean();
						productBean.setProductId(productId);
						productBean = transaction.getProductById(productBean);
						list = new ArrayList<>();
						list.add(productBean);
						setProductsToTable(list);
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						dispose();
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		});
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(new String[][] {},
				new String[] { "Item ID", "Item name", "Category", "Selling price", "Selling unit" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(289);
		table.getColumnModel().getColumn(2).setPreferredWidth(146);
		table.getColumnModel().getColumn(3).setPreferredWidth(99);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add items to sale bill", TitledBorder.CENTER, TitledBorder.TOP, null,
				Color.RED));
		panel.setBounds(12, 220, 899, 286);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 44, 865, 193);
		panel.add(scrollPane_1);

		saleTable = new JTable();
		saleTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Item ID", "Item name", "Category", "Selling price", "Selling unit" }));
		scrollPane_1.setViewportView(saleTable);

		JButton btnAddToSale = new JButton("Add to sale bill");
		btnAddToSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list = new ArrayList<>();
				DefaultTableModel model = (DefaultTableModel) saleTable.getModel();
				for (int i = 0; i < model.getRowCount(); i++) {
					productBean.setProductId(Integer.parseInt(model.getValueAt(i, 0).toString()));
					productBean.setProductName(model.getValueAt(i, 1).toString());
					productBean.setProductType(model.getValueAt(i, 2).toString());
					productBean.setSellingPrice(Double.parseDouble(model.getValueAt(i, 3).toString()));
					productBean.setSellingUnit(model.getValueAt(i, 4).toString());
					list.add(productBean);
				}
				Sales sales = new Sales(list);
				sales.setVisible(true);
			}
		});
		btnAddToSale.setBounds(301, 249, 135, 25);
		panel.add(btnAddToSale);

		JButton btnResetItems = new JButton("Reset items");
		btnResetItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) saleTable.getModel();
				model.setRowCount(0);
			}
		});
		btnResetItems.setBounds(461, 249, 114, 25);
		panel.add(btnResetItems);

	}

	public void getProducts() {

		List<ProductBean> productList = transaction.getProductList();

		if (productList != null) {
			for (ProductBean bean : productList) {
				productId = bean.getProductId();
				productName = bean.getProductName();
				productType = bean.getProductType();
				sellingUnit = bean.getSellingUnit();
				sellingPrice = bean.getSellingPrice();

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] { productId, productName, productType, sellingPrice, sellingUnit });
			}
		}

	}

	public void setProductsToTable(ArrayList<ProductBean> list) {

		for (ProductBean bean : list) {
			productId = bean.getProductId();
			productName = bean.getProductName();
			productType = bean.getProductType();
			sellingUnit = bean.getSellingUnit();
			sellingPrice = bean.getSellingPrice();

			DefaultTableModel model = (DefaultTableModel) saleTable.getModel();
			model.addRow(new Object[] { productId, productName, productType, sellingPrice, sellingUnit });
		}

	}
}
