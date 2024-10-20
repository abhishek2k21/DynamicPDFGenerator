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
swagger
Java 21
SLF4J for logging
Maven for dependency management


Project Structure


![image](https://github.com/user-attachments/assets/2dba8a41-7362-4369-ad0b-892a42cd0ee0)



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



Postman Post method:
http://localhost:8080/api/pdf/generate-pdf


input 
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


Output :[Buyer_Name_1.pdf](https://github.com/user-attachments/files/17449818/Buyer_Name_1.pdf)



