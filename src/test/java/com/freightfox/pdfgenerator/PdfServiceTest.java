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
    private PdfUtil pdfUtil; 

    @InjectMocks
    private PdfService pdfService; 

    @BeforeEach
    public void setUp() {
	MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGeneratePdf_NewFile() {
	PdfRequest request = new PdfRequest();
	request.setTemplate("invoice.html"); 

	when(pdfUtil.createPdfFromTemplate(request)).thenReturn(new ByteArrayInputStream(new byte[0]));

	ByteArrayInputStream pdfStream = pdfService.generatePdf(request);

	assertNotNull(pdfStream, "PDF stream should not be null");

	
    }
}
