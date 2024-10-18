package com.freightfox.pdfgenerator.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.freightfox.pdfgenerator.service.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody PdfRequest request) {
        try {
            // Validate input before generating PDF
            if (request == null || request.getSomeRequiredField() == null) {
                throw new PdfGenerationException("Invalid input: Request or required field is missing.");
            }

            // Generate the PDF as a stream
            ByteArrayInputStream pdfStream = pdfService.generatePdf(request);

            // Set headers for the PDF response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=invoice.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);

            // Return the response with PDF content and headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(pdfStream));

        } catch (PdfGenerationException e) {
            // Custom exception for known issues
            logger.error("Error generating PDF for request: {}", request, e);
            throw e;
        } catch (Exception e) {
            // Log the error with detailed information for debugging
            logger.error("Unexpected error generating PDF for request: {}", request, e);
            throw new PdfGenerationException("An unexpected error occurred while generating the PDF.");
        }
    }

    // Custom exception handler for PDF generation errors
    @ExceptionHandler(PdfGenerationException.class)
    public ResponseEntity<String> handlePdfGenerationException(PdfGenerationException ex) {
        logger.error("PDF Generation Exception: {}", ex.getMessage());
        // Return a custom error message instead of the Whitelabel Error Page
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Error: " + ex.getMessage());
    }

    // Optionally, add a simple GET endpoint to check if the service is up
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("PDF Generator is running");
    }
}

// Custom Exception Class for PDF generation errors
class PdfGenerationException extends RuntimeException {
    public PdfGenerationException(String message) {
        super(message);
    }
}
