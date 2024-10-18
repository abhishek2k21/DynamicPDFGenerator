package com.freightfox.pdfgenerator;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.freightfox.pdfgenerator.service.PdfService;
import com.freightfox.pdfgenerator.utils.PdfUtil;

public class PdfServiceTest {

    @Mock
    private PdfUtil pdfUtil; // Mock the PdfUtil dependency

    @InjectMocks
    private PdfService pdfService; // Inject the PdfService with the mock PdfUtil

    @BeforeEach
    public void setUp() {
	// Initialize Mockito annotations before each test
	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGeneratePdf_NewFile() {
	// Arrange: Set up a valid PdfRequest
	PdfRequest request = new PdfRequest();
	request.setTemplate("invoice.html"); // Provide a valid template for testing

	// Mock the behavior of PdfUtil to return a valid ByteArrayInputStream
	when(pdfUtil.createPdfFromTemplate(request)).thenReturn(new ByteArrayInputStream(new byte[0]));

	// Act: Call the generatePdf method
	ByteArrayInputStream pdfStream = pdfService.generatePdf(request);

	// Assert: Verify the generated PDF stream is not null
	assertNotNull(pdfStream, "PDF stream should not be null");

	// Optionally, you can add more assertions to check for specific behaviors,
	// such as validating the size or content of the PDF stream if needed
    }
}
