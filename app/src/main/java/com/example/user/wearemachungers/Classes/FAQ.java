package com.example.user.wearemachungers.Classes;

public class FAQ {
    private String pertanyaan;
    private String jawaban;
    private String last_edit;

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaanFAQ) {
        this.pertanyaan = pertanyaanFAQ;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawabanFAQ) {
        this.jawaban = jawabanFAQ;
    }

    public String getLast_edit() {
        return last_edit;
    }

    public void setLast_edit(String last_edit) {
        this.last_edit = last_edit;
    }
}
