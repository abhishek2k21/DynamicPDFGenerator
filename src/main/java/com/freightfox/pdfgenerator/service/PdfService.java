package com.freightfox.pdfgenerator.service;

import java.io.ByteArrayInputStream;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.freightfox.pdfgenerator.utils.PdfUtil;

@Service
public class PdfService {

    @Autowired
    private PdfUtil pdfUtil;

    /**
     * Generates a PDF from the provided request.
     *
     * @param request the PdfRequest containing data for the PDF generation
     * @return ByteArrayInputStream representing the generated PDF
     * @throws IllegalArgumentException if the request is null or invalid
     */
    public ByteArrayInputStream generatePdf(PdfRequest request) {
	// Validate the request object
	if (request == null) {
	    throw new IllegalArgumentException("PdfRequest cannot be null");
	}

	// Check if the template field in the request is a valid String
	if (request.getTemplate() == null || request.getTemplate().isEmpty()) {
	    throw new IllegalArgumentException("Template cannot be null or empty");
	}

	// Assuming PdfUtil will handle further PDF creation logic
	return pdfUtil.createPdfFromTemplate(request);
    }
}
