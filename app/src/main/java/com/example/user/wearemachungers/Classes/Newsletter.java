package com.example.user.wearemachungers.Classes;

public class Newsletter {
    private String id;
    private String namaPDF;
    private String url;
    private String email;
    private String lastEdit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaPDF() {
        return namaPDF;
    }

    public void setNamaPDF(String namaPDF) {
        this.namaPDF = namaPDF;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }
}
