package com.research.gradesheet.model;

public class Response {

    private String RESULT;
    private String URL;
    private String doc;

    public Response(String RESULT, String URL, String doc) {
        this.RESULT = RESULT;
        this.URL = URL;
        this.doc = doc;
    }

    public String getRESULT() {
        return RESULT;
    }

    public String getURL() {
        return URL;
    }

    public String getDoc() {
        return doc;
    }
}
