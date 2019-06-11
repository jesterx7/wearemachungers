package com.example.user.wearemachungers.Classes;

public class FAQ {
    private String pertanyaan;
    private String jawaban;
    private String lastEdit;

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

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }
}
