package utilities;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gui.guiform.ContactGUIForm;
import gui.guiform.CustomerGUIForm;
import gui.guiform.InvoiceGUIForm;
import gui.guiform.ProductGUIForm;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceManager {

    public void generateInvoice(String filePath, InvoiceGUIForm invoiceGUIForm, int invoiceId, CustomerGUIForm customerGUIForm, ContactGUIForm contactGUIForm, List<ProductGUIForm> productsList) {
        
        Document document;
        Paragraph emptyLine;
        Font shopCustomerFont;
        Font titleFont;
        Paragraph title;
        PdfPTable shopCustomerTable;
        PdfPCell shopCell;
        PdfPCell customerCell;
        PdfPCell shopHeader;
        PdfPCell spacerHeader;
        PdfPCell customerHeader;
        Paragraph separator;
        PdfPTable purchaseTable;
        PdfPCell productHeader;
        PdfPCell priceHeader;
        PdfPCell productCell;
        PdfPCell priceCell;

        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            titleFont = new Font(Font.FontFamily.UNDEFINED, 14, Font.BOLD);
            shopCustomerFont = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
            
            emptyLine = new Paragraph("\n");
            separator = new Paragraph("________________________________________________________________________");
            
            title = new Paragraph("Invoice n: " + invoiceId + " " + LocalDate.now(), titleFont);
            title.setAlignment(Rectangle.ALIGN_CENTER);

            shopCustomerTable = new PdfPTable(3);
            shopCustomerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            shopCell = new PdfPCell(new Phrase("Shop", shopCustomerFont));
            customerCell = new PdfPCell(new Phrase("Customer", shopCustomerFont));

            shopCell.setBorder(Rectangle.NO_BORDER);
            customerCell.setBorder(Rectangle.NO_BORDER);

            shopHeader = new PdfPCell(shopCell);
            spacerHeader = new PdfPCell(new Phrase(""));
            customerHeader = new PdfPCell(customerCell);
            
            purchaseTable = new PdfPTable(3);
            purchaseTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            
            productHeader = new PdfPCell(new Phrase("Product", shopCustomerFont));
            productHeader.setBorder(Rectangle.NO_BORDER);
            productHeader.setPaddingBottom(20);
            
            priceHeader = new PdfPCell(new Phrase("Price", shopCustomerFont));
            priceHeader.setBorder(Rectangle.NO_BORDER);
            priceHeader.setPaddingBottom(20);
            
            shopHeader.setBorder(Rectangle.NO_BORDER);
            spacerHeader.setBorder(Rectangle.NO_BORDER);
            customerHeader.setBorder(Rectangle.NO_BORDER);

            String shopDetails = Utilities.getShopName();
            String customerDetails = customerGUIForm.getName() + " " + customerGUIForm.getLastname() + "\n" + contactGUIForm.getAddress()
                    + ", " + contactGUIForm.getZipCode() + "\n" + contactGUIForm.getCity() + ", " + contactGUIForm.getCountry();

            shopCustomerTable.addCell(shopHeader);
            shopCustomerTable.addCell(spacerHeader);
            shopCustomerTable.addCell(customerHeader);

            shopCustomerTable.setHeaderRows(1);

            shopCustomerTable.addCell(shopDetails);
            shopCustomerTable.addCell("");
            shopCustomerTable.addCell(customerDetails);
            
            purchaseTable.addCell(productHeader);
            purchaseTable.addCell(new Phrase(""));
            purchaseTable.addCell(priceHeader);
            
            double subtotalNumber = 0;
            
            for(ProductGUIForm productGUIForm : productsList){
                productCell = new PdfPCell(new Phrase("-" + productGUIForm.getProductName()));
                productCell.setBorder(Rectangle.NO_BORDER);
                productCell.setPaddingBottom(15);
                
                priceCell = new PdfPCell(new Phrase(productGUIForm.getSellingPrice() + " €"));
                priceCell.setBorder(Rectangle.NO_BORDER);
                priceCell.setPaddingBottom(15);
                
                purchaseTable.addCell(productCell);
                purchaseTable.addCell("");
                purchaseTable.addCell(priceCell);
                
                subtotalNumber += Utilities.getIntegerValueFromDecimalNumber(productGUIForm.getSellingPrice());
            }
            
            purchaseTable.addCell("Subtotal:");
            purchaseTable.addCell("");
            purchaseTable.addCell(Double.toString(subtotalNumber / 100) + " €");
            
            purchaseTable.addCell("Tax:");
            purchaseTable.addCell("");
            purchaseTable.addCell("20%");
            
            purchaseTable.addCell("Total:");
            purchaseTable.addCell("");
            purchaseTable.addCell(Utilities.roundTo2ndDecimal(invoiceGUIForm.getTotalAmount()) + " €");
            
            purchaseTable.addCell("Discount:");
            purchaseTable.addCell("");
            purchaseTable.addCell(invoiceGUIForm.getDiscount() + "€");
            
            purchaseTable.addCell("Total payment:");
            purchaseTable.addCell("");
            purchaseTable.addCell(new Phrase(invoiceGUIForm.getTotalPayment() + " €", new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD)));

            document.add(title);
            document.add(emptyLine);
            
            document.add(shopCustomerTable);
            document.add(emptyLine);
            
            document.add(separator);
            document.add(emptyLine);
            
            document.add(purchaseTable);
            
            document.add(separator);
            document.add(emptyLine);
            
            document.add(new Paragraph("Total paid: " + invoiceGUIForm.getTotalPayment() + " €"));
            document.add(new Paragraph("Payment method: " + invoiceGUIForm.getPaymentMethod()));
            document.add(new Paragraph("Notes:"));
            document.add(new Paragraph(invoiceGUIForm.getNote()));
            
            document.close();

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(InvoiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Document getPDFDocument(String filePath) throws FileNotFoundException, DocumentException {
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        return document;
    }
}
