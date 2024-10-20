package com.freightfox.pdfgenerator.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.freightfox.pdfgenerator.model.PdfRequest;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@Component
public class PdfUtil {

    private static final Logger logger = LoggerFactory.getLogger(PdfUtil.class);

    @Autowired
    private TemplateEngine templateEngine;

    
    public String createPdfFromTemplate(PdfRequest request) throws Exception {
        String baseFileName = request.getBuyer().replaceAll("\\s+", "_"); 
        String extension = ".pdf";
        String directoryPath = "pdfs"; 
        String filePath = Paths.get(directoryPath, baseFileName + extension).toString();

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                logger.info("Created directory: {}", directory.getAbsolutePath());
            } else {
                logger.error("Failed to create directory: {}", directory.getAbsolutePath());
                throw new RuntimeException("Could not create directory for PDFs");
            }
        }

        File file = new File(filePath);
        int counter = 1;

        while (file.exists()) {
            filePath = Paths.get(directoryPath, baseFileName + "_" + counter + extension).toString();
            file = new File(filePath);
            counter++;
        }

        Context context = new Context();
        context.setVariable("seller", request.getSeller());
        context.setVariable("sellerGstin", request.getSellerGstin());
        context.setVariable("sellerAddress", request.getSellerAddress());
        context.setVariable("buyer", request.getBuyer());
        context.setVariable("buyerGstin", request.getBuyerGstin());
        context.setVariable("buyerAddress", request.getBuyerAddress());
        context.setVariable("items", request.getItems());

        String htmlContent = templateEngine.process(request.getTemplate(), context);

        logger.debug("Generated HTML Content: \n{}", htmlContent);

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent, fos, converterProperties);
        } catch (Exception e) {
            logger.error("Error generating PDF for {}: {}", request.getBuyer(), e.getMessage());
            throw new RuntimeException("Error generating PDF for " + request.getBuyer(), e);
        }

        logger.info("PDF generated successfully: {}", filePath);
        return filePath; 
    }
}
