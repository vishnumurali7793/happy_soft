package com.happy.Ui;

import java.awt.EventQueue;
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

public class SelectionWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	TransactionAction transaction = new TransactionAction();
	TransactionDao transactionDao;

	private Integer productId;
	private String productName;
	private String productType;
	private String sellingUnit;
	private Double sellingPrice;

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
		setBounds(100, 100, 923, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();
					System.out.println(table.getValueAt(row, column));
				}
			}
		});
		scrollPane.setBounds(12, 82, 899, 309);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setModel(new DefaultTableModel(new String[][] {},
				new String[] { "Item ID", "Item name", "Category", "Selling price", "Selling unit" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(289);
		table.getColumnModel().getColumn(2).setPreferredWidth(146);
		table.getColumnModel().getColumn(3).setPreferredWidth(99);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		scrollPane.setViewportView(table);

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
}
