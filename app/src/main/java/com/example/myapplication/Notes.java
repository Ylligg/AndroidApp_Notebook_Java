package com.example.myapplication;


public class Notes {

    private long id;
    String tittel;
    String notat;
    String tag;


    //konstrøkører

public Notes(long id, String tittel, String notat, String tag) {
    this.id = id;
    this.tittel = tittel;
    this.tag = tag;
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

// metode for å få navn i spinneren i avtale siden
@Override
public String toString() {
    return getTittel();
}
}
