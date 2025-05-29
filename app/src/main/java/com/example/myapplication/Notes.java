package com.example.myapplication;


public class Notes {

    private long id;
    String tittel;
    String notat;
    String tag;

    String tagId;

    //konstrøkører
    public Notes() {
    }
    public Notes(long id, String tittel, String notat, String tag, String tagId) {
        this.id = id;
        this.tittel = tittel;
        this.tag = tag;
        this.tagId = tagId;
    }

    // getter og settere

    public long getId() {
        return id;
    }

    public String getTittel() {
        return tittel;
    }

    public String getNotat() {
        return notat;
    }

    public String getTag() {
        return tag;
    }

    public String getTagId() {
        return tagId;
    }

    public  void setId(long id) {
        this.id = id;
    }

    public  void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public  void setNotat(String notat) {
        this.notat = notat;
    }

    public  void setTag(String tag) {
        this.tag = tag;
    }

    public  void setTagId(String tagId) {
            this.tagId = tagId;
    }

    @Override
    public String toString() {
        return getTittel();
    }
}
