package com.freightfox.pdfgenerator.model;

public class PdfRequest {

    private String name;
    private String template; // Adding template field as a String

    // Constructors
    public PdfRequest() {
    }

    public PdfRequest(String name, String template) {
	this.name = name;
	this.template = template;
    }

    // Getter and setter for 'name'
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    // Getter and setter for 'template'
    public String getTemplate() {
	return template;
    }

    public void setTemplate(String template) {
	this.template = template;
    }

    public Object getSomeRequiredField() {
	// TODO Auto-generated method stub
	return null;
    }

   
}
