package com.example.dream;

public class RequestList_Item {
    private String btype;
    private String title;
    private String bkind;
    private String hospital;
    private String duedate;

    public RequestList_Item(String btype, String title, String bkind, String hospital, String duedate) {
        this.btype = btype;
        this.title = title;
        this.bkind = bkind;
        this.hospital = hospital;
        this.duedate = duedate;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBkind() {
        return bkind;
    }

    public void setBkind(String bkind) {
        this.bkind = bkind;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}
