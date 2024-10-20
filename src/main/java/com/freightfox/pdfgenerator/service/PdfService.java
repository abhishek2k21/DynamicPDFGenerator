package com.freightfox.pdfgenerator.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.freightfox.pdfgenerator.utils.PdfUtil;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Service
public class PdfService {

    @Autowired
    private PdfUtil pdfUtil;

    public ByteArrayInputStream generatePdf(PdfRequest request) throws Exception {
	String pdfFileName = request.getSeller() + "_" + request.getBuyer() + ".pdf";
	Path pdfDirectory = Paths.get("pdfs");
	String filePath = pdfDirectory.resolve(pdfFileName).toString();

	if (!Files.exists(pdfDirectory)) {
	    Files.createDirectories(pdfDirectory);
	}

	File file = new File(filePath);
	if (file.exists()) {
	    return new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
	}

	PdfWriter writer = new PdfWriter(filePath);
	PdfDocument pdfDoc = new PdfDocument(writer);
	Document document = new Document(pdfDoc);

	float[] sellerBuyerColumnWidths = { 1, 1 };
	Table sellerBuyerTable = new Table(sellerBuyerColumnWidths);
	sellerBuyerTable.addCell(new Paragraph("Seller:\n" + request.getSeller() + "\n" + request.getSellerAddress()
		+ "\nGSTIN: " + request.getSellerGstin()));
	sellerBuyerTable.addCell(new Paragraph("Buyer:\n" + request.getBuyer() + "\n" + request.getBuyerAddress()
		+ "\nGSTIN: " + request.getBuyerGstin()));
	document.add(sellerBuyerTable);

	float[] columnWidths = { 2, 1, 1, 1 };
	Table itemTable = new Table(columnWidths);
	addTableHeader(itemTable);

	for (PdfRequest.Item item : request.getItems()) {
	    addRow(itemTable, item);
	}

	document.add(itemTable);

	document.close();

	return new ByteArrayInputStream(Files.readAllBytes(Paths.get(filePath)));
    }

    private void addTableHeader(Table table) {
	Stream.of("Item", "Quantity", "Rate", "Amount").forEach(columnTitle -> {
	    Cell header = new Cell();
	    header.add(new Paragraph(columnTitle));
	    header.setBold();
	    table.addHeaderCell(header);
	});
    }

    private void addRow(Table table, PdfRequest.Item item) {
	table.addCell(new Cell().add(new Paragraph(item.getName()))); 
	table.addCell(new Cell().add(new Paragraph(item.getQuantity()))); 
	table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getRate())))); 
	table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getAmount())))); 
    }

}
