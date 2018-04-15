package com.research.gradesheet.model;

public class Header {

    private int id;
    private String header;

    public Header(int id, String header){

        this.id = id;
        this.header = header;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
