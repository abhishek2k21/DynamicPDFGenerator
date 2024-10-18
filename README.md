DynamicPDFGenerator
DynamicPDFGenerator is a Spring Boot-based application designed for the dynamic generation of PDFs from client-provided data. It utilizes Thymeleaf templates to create custom PDF documents such as invoices or reports in real-time, with the PDFs being returned via REST API responses.

Features
RESTful API for generating PDFs
Custom exception handling for smoother error responses
Thymeleaf-based PDF creation for flexibility and dynamic content generation
Accepts dynamic input data for generating custom PDFs
Returns the generated PDF directly in the API response
Technologies Used
Spring Boot
Thymeleaf
Java 21
SLF4J for logging
Maven for dependency management
Project Structure
bash
Copy code
DynamicPDFGenerator/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── dynamicpdfgenerator/
│   │   │               ├── controller/      # REST controllers
│   │   │               ├── service/         # Business logic
│   │   │               ├── util/            # Utility classes
│   │   │               └── DynamicPdfGeneratorApplication.java  # Main app
│   │   └── resources/
│   │       ├── templates/     # Thymeleaf templates
│   │       ├── application.properties  # Configurations
│   └── test/                 # Unit and integration tests
│
├── pom.xml                   # Maven dependencies
└── README.md                 # Project documentation
API Endpoints
Generate PDF
URL: /api/pdf/generate
Method: POST
Request Body: JSON data containing the fields required for PDF generation
Response: PDF file returned in the response body
Example Request
json
Copy code
{
  "field1": "value1",
  "field2": "value2"
}
Example Response
The response will be a binary PDF file.

How to Run
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/DynamicPDFGenerator.git
Navigate into the project directory:

bash
Copy code
cd DynamicPDFGenerator
Build the project using Maven:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
The API will be available at:

bash
Copy code
http://localhost:8080/api/pdf/generate
API Documentation
For detailed API documentation, visit the Swagger UI:

bash
Copy code
http://localhost:8080/swagger-ui/index.html
