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

   
    public ByteArrayInputStream generatePdf(PdfRequest request) {
	if (request == null) {
	    throw new IllegalArgumentException("PdfRequest cannot be null");
	}

	if (request.getTemplate() == null || request.getTemplate().isEmpty()) {
	    throw new IllegalArgumentException("Template cannot be null or empty");
	}

	return pdfUtil.createPdfFromTemplate(request);
    }
}
