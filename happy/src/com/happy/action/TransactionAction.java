package com.happy.action;

import com.happy.Ui.Purchase;
import com.happy.Ui.Sales;
import com.happy.Ui.mainWindow;
import com.happy.dao.TransactionDao;
import com.happy.entities.AccountHeadBean;
import com.happy.entities.ProductBean;
import com.happy.entities.SalesBean;
import com.happy.entities.SalesProductMappingBean;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TransactionAction {

	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";
	private List<SalesProductMappingBean> salesProductsList;

	Sales sales;
	TransactionDao transactionDao = new TransactionDao();
	AccountHeadBean headBean = new AccountHeadBean();
	mainWindow mainWindow = new mainWindow();
	SalesBean salesBean = new SalesBean();
	Purchase purchase;

	private String billType; // to switch between sales and purchase

	public List<ProductBean> getProductList() {
		return transactionDao.getProductList();
	}

	public ProductBean getProductById(ProductBean bean) {
		return transactionDao.getProductById(bean);
	}

	public double calculateTotal(double quantity, double price) {
		if (quantity > 0 && price > 0) {
			return quantity * price;
		}
		return 0;
	}

	public ArrayList<AccountHeadBean> getAccountHeads() {
		return transactionDao.getAccountHeads();
	}

	public void getHeadByIdforSales(int headId) {
		if (headId > 0) {
			headBean = transactionDao.getHeadById(headId);
		} else {
			JOptionPane.showMessageDialog(null, "Some error occured");
		}

		if (headBean.getHeadType().equals("Customer")) {
			if (headBean != null) {
				sales = new Sales();
				sales.setVisible(true);
				sales.setCustName(headBean.getHeadName());
				sales.setCustAddress(headBean.getHeadAddress());
				sales.setCustPhone(headBean.getHeadPhone());
				sales.setCustHeadCode(headBean.getHeadCode());
				sales.refresh();
			}
		}
		if (headBean.getHeadType().equals("Supplier")) {
			if (headBean != null) {
				purchase = new Purchase();
				purchase.setVisible(true);
				purchase.setSupplierCode(headBean.getHeadCode());
				purchase.setSupplierName(headBean.getHeadName());
				purchase.refresh();
			}
		}

	}

	public int getSalesCount() {
		return transactionDao.getSalesCount();
	}

	public AccountHeadBean getHeadsByHeadCode(String accountBean) {
		return transactionDao.getHeadsByHeadCode(accountBean);
	}

	public String saveSalesBill(SalesBean salesBean) {
		int status = transactionDao.saveSalesBill(salesBean);
		if (status > 0) {
			return SUCCESS;
		} else if (status <= 0) {
			return FAILED;
		}
		return null;

	}

	public int getProductIdByCode(String itemCode) {
		return transactionDao.getProductIdByCode(itemCode);
	}

	public String saveSalesProductMapping(SalesProductMappingBean spMappingBean) {
		int status = transactionDao.saveSalesProductMapping(spMappingBean);
		if (status > 0) {
			return SUCCESS;
		} else if (status <= 0) {
			return FAILED;
		}
		return null;

	}

	public void generateSalesBill(SalesBean salesBean, SalesProductMappingBean mapBean) {
		salesProductsList = new ArrayList<>();
		headBean = transactionDao.getHeadDetails(salesBean.getAccountBean().getHeadId());
		salesProductsList = transactionDao.getSalesItemList(mapBean);

		try {
			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("bill.pdf"));
			PdfPTable pdfpTable;
			Chunk chunk;
			String temp;
			Paragraph paragraph;
			PdfPCell cell;
			PdfContentByte cb;

			document.open();
			document.addTitle("Sales");
			document.setMargins(5, 5, 5, 5);

			paragraph = new Paragraph();
			if (salesBean.getPaymentType().equals("cash")) {
				temp = "Cash";
			} else if (salesBean.getPaymentType().equals("credit")) {
				temp = "Credit";
			} else {
				temp = "";
			}
			paragraph.add(new Chunk(temp + " Bill", new Font(FontFamily.HELVETICA, 8, Font.BOLDITALIC)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			paragraph.add(new Chunk("Generated on: " + salesBean.getBillDate().toString(),
					new Font(FontFamily.HELVETICA, 8, Font.ITALIC)));
			paragraph.setAlignment(Element.ALIGN_RIGHT);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			paragraph = new Paragraph();
			temp = "Happy Food Products";
			chunk = new Chunk(temp.toString(), new Font(FontFamily.HELVETICA, 12, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingBefore(10);
			paragraph.add(chunk);
			document.add(paragraph);

			paragraph = new Paragraph();
			temp = "Near St. Aloisius college, Karyatukara";
			chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
			paragraph.add(chunk);
			paragraph.setSpacingBefore(1);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraph);

			paragraph = new Paragraph();
			chunk = new Chunk("Customer name: ", new Font(FontFamily.HELVETICA, 8));
			paragraph.add(chunk);
			paragraph.add(new Chunk(headBean.getHeadName(), new Font(FontFamily.HELVETICA, 8)));
			paragraph.setSpacingBefore(20);
			paragraph.setSpacingAfter(1);
			document.add(paragraph);

			paragraph = new Paragraph();
			chunk = new Chunk("Address: ", new Font(FontFamily.HELVETICA, 8));
			paragraph.add(chunk);
			paragraph.add(new Chunk(headBean.getHeadAddress(), new Font(FontFamily.HELVETICA, 8)));
			paragraph.setSpacingAfter(1);
			document.add(paragraph);

			paragraph = new Paragraph();
			chunk = new Chunk("Contact: ", new Font(FontFamily.HELVETICA, 8));
			paragraph.add(chunk);
			paragraph.add(new Chunk(headBean.getHeadPhone(), new Font(FontFamily.HELVETICA, 8)));
			paragraph.setSpacingAfter(1);
			document.add(paragraph);

			paragraph = new Paragraph();
			chunk = new Chunk("GSTIN: ", new Font(FontFamily.HELVETICA, 8));
			paragraph.add(chunk);
			if (headBean.getHeadGstin().equals("")) {
				paragraph.add(new Chunk("-N/A-", new Font(FontFamily.HELVETICA, 8)));
			} else if (headBean.getHeadGstin() != null && headBean.getHeadGstin().equalsIgnoreCase("")) {
				paragraph.add(new Chunk(headBean.getHeadGstin(), new Font(FontFamily.HELVETICA, 8)));
			}
			paragraph.setSpacingAfter(5);
			document.add(paragraph);

			pdfpTable = new PdfPTable(5);
			pdfpTable.setWidths(new int[] { 5, 65, 10, 10, 10 });
			pdfpTable.setWidthPercentage(100);
			cell = new PdfPCell(new Phrase("#", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Item name", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Price", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Quantity", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase("Total", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpTable.addCell(cell);

			int slNo = 1;
			for (SalesProductMappingBean bean : salesProductsList) {
				cell = new PdfPCell(new Phrase(String.valueOf(slNo), new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
				cell.setBorder(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Phrase(bean.getProductId().getProductName(),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
				cell.setBorder(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Phrase(bean.getProductId().getSellingPrice().toString(),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
				cell.setBorder(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(
						new Phrase(bean.getQuantity().toString(), new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
				cell.setBorder(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Phrase(bean.getProductTotalAmt().toString(),
						new Font(FontFamily.HELVETICA, 8, Font.NORMAL)));
				cell.setBorder(com.itextpdf.text.Rectangle.RIGHT | com.itextpdf.text.Rectangle.LEFT);
				pdfpTable.addCell(cell);

				slNo++;
			}

			cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setBorder(com.itextpdf.text.Rectangle.BOTTOM | com.itextpdf.text.Rectangle.LEFT);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setBorder(com.itextpdf.text.Rectangle.BOTTOM | com.itextpdf.text.Rectangle.LEFT);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setBorder(com.itextpdf.text.Rectangle.BOTTOM | com.itextpdf.text.Rectangle.LEFT);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setBorder(com.itextpdf.text.Rectangle.BOTTOM | com.itextpdf.text.Rectangle.LEFT);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
			cell.setBorder(com.itextpdf.text.Rectangle.BOTTOM | com.itextpdf.text.Rectangle.LEFT
					| com.itextpdf.text.Rectangle.RIGHT | com.itextpdf.text.Rectangle.BOTTOM);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Sub total", new Font(FontFamily.HELVETICA, 8)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			pdfpTable.addCell(cell);

			cell = new PdfPCell(
					new Paragraph(salesBean.getTotalBeforeDiscount().toString(), new Font(FontFamily.HELVETICA, 8)));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfpTable.addCell(cell);

			if (salesBean.getDiscountEnabled().equals("Y") && salesBean.getDiscount() != null) {

				cell = new PdfPCell(new Paragraph(" ", new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				cell.setColspan(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Paragraph(" ", new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Paragraph("Discount", new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				cell.setColspan(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(
						new Paragraph(salesBean.getDiscount().toString(), new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				pdfpTable.addCell(cell);
			} else {
				cell = new PdfPCell(new Paragraph(" ", new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				cell.setColspan(4);
				pdfpTable.addCell(cell);

				cell = new PdfPCell(new Paragraph(" ", new Font(FontFamily.HELVETICA, 8)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				pdfpTable.addCell(cell);
			}
			pdfpTable.setSpacingBefore(10);
			document.add(pdfpTable);

			document.close();
			// to open the pdf file automatically after generating
			if (Desktop.isDesktopSupported()) {
				try {
					File myFile = new File("bill.pdf");
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}
}
