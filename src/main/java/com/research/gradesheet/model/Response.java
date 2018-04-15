package com.research.gradesheet.model;

public class Response {

    private String RESULT;
    private String URL;

    public Response(String RESULT, String URL) {
        this.RESULT = RESULT;
        this.URL = URL;
    }

    public String getRESULT() {
        return RESULT;
    }

    public String getURL() {
        return URL;
    }

    public void setRESULT(String RESULT) {
        this.RESULT = RESULT;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
