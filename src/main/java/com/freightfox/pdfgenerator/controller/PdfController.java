package com.freightfox.pdfgenerator.controller;

import java.io.File;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.freightfox.pdfgenerator.utils.PdfUtil;

@RestController
@RequestMapping("/api/pdf")
@Validated
public class PdfController {

    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    private PdfUtil pdfUtil;

    @PostMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generatePdf(@RequestBody PdfRequest request) {
        logger.info("Generating PDF for: {}", request);
        
        if (request.getTemplate() == null || request.getTemplate().isEmpty()) {
            String errorMessage = "Template cannot be null or empty.";
            logger.warn(errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            request.setTemplate("invoice.html");
            String pdfPath = pdfUtil.createPdfFromTemplate(request);

            File file = new File(pdfPath);
            if (!file.exists()) {
                logger.error("PDF file not found at path: {}", pdfPath);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("PDF file not found at " + pdfPath);
            }

            byte[] pdfBytes = Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
            headers.setContentType(MediaType.APPLICATION_PDF);  

            return ResponseEntity.ok()
                                 .headers(headers)
                                 .contentLength(pdfBytes.length)
                                 .body(pdfBytes);
        } catch (Exception e) {
            logger.error("Error generating or reading PDF: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .contentType(MediaType.TEXT_PLAIN)                                   .body("Error: " + e.getMessage());
        }
    }
}
