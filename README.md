# DynamicPDFGenerator
DynamicPDFGenerator is a Spring Boot application designed to generate custom PDFs dynamically using client-provided data. It leverages Thymeleaf templates to create documents such as invoices or reports in real-time, with the PDFs returned directly through a REST API response. This tool is ideal for systems that require flexible, on-demand PDF creation.

# Project Structure
```
DynamicPDFGenerator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── freightfox/
│   │   │           └── pdfgenerator/
│   │   │               ├── config/
│   │   │               │   ├── AppConfig.java
│   │   │               │   ├── SwaggerConfig.java
│   │   │               │   └── WebConfig.java
│   │   │               ├── controller/
│   │   │               │   └── PdfController.java
│   │   │               ├── exception/
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── model/
│   │   │               │   └── PdfRequest.java
│   │   │               ├── service/
│   │   │               │   └── PdfService.java
│   │   │               └── utils/
│   │   │                   ├── PdfUtil.java
│   │   │                   └── DynamicPdfGeneratorApplication.java
│   ├── resources/
│   │   ├── static/
│   │   ├── templates/
│   │   │   ├── error/
│   │   │   └── invoice.html
│   │   └── application.properties
│
├── test/
│   ├── java/
│   │   └── com/
│   │       └── freightfox/
│   │           └── pdfgenerator/
│   │               └── PdfServiceTest.java
│   └── resources/
│
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Features
- **REST API for PDF Generation**: Easily generate PDFs by sending data through a RESTful POST request.
- **Dynamic Content Generation**: Custom PDFs created using Thymeleaf templates, making it flexible for various document types like invoices, receipts, etc.
- **Exception Handling**: Graceful error handling with custom exceptions.
- **Direct PDF Response**: The generated PDF is returned directly in the response body, ready for immediate download.
- **Logging**: Integrated with SLF4J for effective logging and debugging.

## Technology Stack
- **Spring Boot** – Backend framework for developing the REST API.
- **Thymeleaf** – Template engine for dynamic PDF generation.
- **Swagger** – API documentation.
- **Java 21** – Latest features of Java for enhanced performance and productivity.
- **SLF4J** – Logging framework.
- **Maven** – Dependency management and build tool.

# Key Folders
/src/main/java/com/freightfox/pdfgenerator/ – Contains the core Java files, including controllers, services, configurations, and utility classes.

- **controller/** – Contains the PdfController.java that handles API requests.
- **service/*** – Contains business logic for generating PDFs (PdfService.java).
- **model/** – Data model for incoming PDF generation requests (PdfRequest.java).
- **config/** – Configuration files for application setup, Swagger, and web settings.
- **exception/** – Global exception handling for REST APIs.
- **utils/** – Utility classes such as PdfUtil.java that help with PDF operations.
- **/src/main/resources/** – Contains application resources.
- **templates/** – Thymeleaf templates for generating dynamic PDFs (e.g., invoice.html).
- **application.properties** – Configuration file for application properties.
- **/test/** – Test folder with unit tests.


## API Endpoints

### Generate PDF

- **URL**: `/api/pdf/generate`
- **Method**: `POST`
- **Request Body**: JSON data containing the fields required for PDF generation
- **Response**: Binary PDF file returned in the response body

**Example Request**:
```json
{
  "seller": "Seller Name",
  "sellerGstin": "GSTIN123",
  "sellerAddress": "Seller Address",
  "buyer": "Buyer Name",
  "buyerGstin": "GSTIN456",
  "buyerAddress": "Buyer Address",
  "items": [],
  "template": "invoice.html"
}
```

**Example Response**:  
The response will be a binary PDF file, like `Buyer_Name_1.pdf`.

## How to Run the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/DynamicPDFGenerator.git
   ```
2. **Navigate into the Project Directory**:
   ```bash
   cd DynamicPDFGenerator
   ```
3. **Build the Project using Maven**:
   ```bash
   mvn clean install
   ```
4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The API will be available at: [http://localhost:8080/api/pdf/generate](http://localhost:8080/api/pdf/generate)

## Using Postman to Test the API

- **Method**: `POST`
- **URL**: [http://localhost:8080/api/pdf/generate](http://localhost:8080/api/pdf/generate)

### Headers:
- **Content-Type**: `application/json`

### Body (Raw JSON):
```json
{
  "seller": "Seller Name",
  "sellerGstin": "GSTIN123",
  "sellerAddress": "Seller Address",
  "buyer": "Buyer Name",
  "buyerGstin": "GSTIN456",
  "buyerAddress": "Buyer Address",
  "items": [],
  "template": "invoice.html"
}
```

### Response: 
You will receive a binary PDF file directly in the response.

**Sample PDF Output**:  
`Buyer_Name_1.pdf`

---

