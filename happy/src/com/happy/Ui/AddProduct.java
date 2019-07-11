package com.happy.Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.happy.dao.MasterDao;
import com.happy.entities.ProductBean;
import com.happy.entities.StockBean;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField productId;
	private JTextField productName;
	private JTextField price;

	MasterDao master = new MasterDao();
	ProductBean productMaster = new ProductBean();
	private JTextField txtOpeningStock;
	private StockBean stock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
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
	public AddProduct() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 913, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel unit = new JPanel();
		unit.setBorder(new TitledBorder(null, "Add Product", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		unit.setBounds(24, 12, 865, 474);
		contentPane.add(unit);
		unit.setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(367, 400, 82, 25);
		unit.add(btnExit);

		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(52, 65, 82, 15);
		unit.add(lblProductId);

		productId = new JTextField();
		productId.setBounds(267, 63, 124, 19);
		if (master.getProductId() != null) {
			productId.setText(String.valueOf(master.getProductId() + 1));
		} else {
			productId.setText("1");
		}
		productId.setEditable(false);
		unit.add(productId);
		productId.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(52, 122, 66, 15);
		unit.add(lblName);

		productName = new JTextField();
		productName.setBounds(267, 120, 293, 19);
		unit.add(productName);
		productName.setColumns(10);

		JLabel lblRawMaterialfinishedGood = new JLabel("Raw material/Finished good");
		lblRawMaterialfinishedGood.setBounds(52, 177, 224, 15);
		unit.add(lblRawMaterialfinishedGood);

		JComboBox productCategory = new JComboBox();
		productCategory
				.setModel(new DefaultComboBoxModel(new String[] { "-select one-", "Raw Material", "Finished Good" }));
		productCategory.setBounds(267, 172, 138, 24);
		unit.add(productCategory);

		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(52, 228, 66, 15);
		unit.add(lblUnit);

		JComboBox sellingUnit = new JComboBox();
		sellingUnit.setModel(new DefaultComboBoxModel(new String[] { "-select one-", "KG", "G", "Nos", "Packets" }));
		sellingUnit.setBounds(267, 223, 138, 24);
		unit.add(sellingUnit);

		JLabel lblPriceunit = new JLabel("Price/Unit");
		lblPriceunit.setBounds(52, 281, 98, 15);
		unit.add(lblPriceunit);

		price = new JTextField();
		price.setBounds(267, 279, 124, 19);
		unit.add(price);
		price.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (productName.getText().equals("") || productCategory.getSelectedItem().equals("-select one-")
						|| sellingUnit.getSelectedItem().equals("-select one-") || price.getText().equals("")
						|| price.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "All fields are mandatory! please fill all.");
				} else {
					productMaster.setProductId(Integer.parseInt(productId.getText()));
					productMaster.setProductName(productName.getText());
					productMaster.setProductType(productCategory.getSelectedItem().toString());
					productMaster.setSellingUnit(sellingUnit.getSelectedItem().toString());
					productMaster.setSellingPrice(Double.parseDouble(price.getText()));

					if (txtOpeningStock.getText().equals("") || txtOpeningStock.getText().equals("0")) {
						try {
							productMaster.setOpeningStock(Double.parseDouble("0.0"));
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							productMaster.setOpeningStock(Double.parseDouble(txtOpeningStock.getText()));
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Only numbers allowed");
							e1.printStackTrace();
							return;
						}
					}

					char[] nameArray = productName.getText().toUpperCase().toCharArray();
					int length = nameArray.length;

					productMaster
							.setProductCode("ITE" + productId.getText() + nameArray[0] + nameArray[1] + nameArray[2]
									+ "-" + nameArray[length - 3] + nameArray[length - 2] + nameArray[length - 1]);

					stock = new StockBean();
					stock.setProductId(new ProductBean());
					stock.getProductId().setProductId(productMaster.getProductId());

					if (txtOpeningStock.getText().equals("") || txtOpeningStock.getText().equals("0")) {
						try {
							stock.setStock(Double.parseDouble("0.0"));
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							stock.setStock(Double.parseDouble(txtOpeningStock.getText()));
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null, "Only numbers allowed");
							e1.printStackTrace();
							return;
						}
					}

					try {
						master.addProduct(productMaster);
						master.updateOpeningStock(stock);
						JOptionPane.showMessageDialog(null, "Product saved");
						productId.setText("");
						productName.setText("");
						sellingUnit.setSelectedIndex(0);
						productCategory.setSelectedIndex(0);
						price.setText("");
						txtOpeningStock.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			}
		});
		btnSave.setBounds(267, 400, 82, 25);
		unit.add(btnSave);

		JLabel lblOpeningStock = new JLabel("Opening stock");
		lblOpeningStock.setForeground(Color.RED);
		lblOpeningStock.setBounds(52, 336, 115, 15);
		unit.add(lblOpeningStock);

		txtOpeningStock = new JTextField();
		txtOpeningStock.setBounds(267, 334, 124, 19);
		unit.add(txtOpeningStock);
		txtOpeningStock.setColumns(10);
	}
}
