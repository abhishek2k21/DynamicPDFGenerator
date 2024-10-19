package com.freightfox.pdfgenerator.model;

public class PdfRequest {

    private String name;
    private String template;

    public PdfRequest() {
    }

    public PdfRequest(String name, String template) {
        this.name = name;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSomeRequiredField() {
        return name; 
    }
}
