package com.freightfox.pdfgenerator.utils;

import java.io.ByteArrayInputStream;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.itextpdf.html2pdf.HtmlConverter;

@Component
public class PdfUtil {

    public ByteArrayInputStream createPdfFromTemplate(PdfRequest request) {
	if (request == null) {
	    throw new IllegalArgumentException("PdfRequest cannot be null");
	}

	try {
	    String htmlContent = getHtmlTemplate(request);
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8)),
		    outputStream);
	    return new ByteArrayInputStream(outputStream.toByteArray());
	} catch (Exception e) {
	    throw new RuntimeException("Error while generating PDF: " + e.getMessage(), e);
	}
    }

    private String getHtmlTemplate(PdfRequest request) {
	String name = request.getName() != null ? request.getName() : "Guest"; 
	return "<html><body><h1>Invoice</h1><p>This is a sample PDF for: " + name + "</p></body></html>";
    }
}
