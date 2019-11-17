package com.example.wordsbook;

import android.database.sqlite.SQLiteDatabase;

public class Words {
    private String word ;
    private String meanings ;
    private String exS ;

    public Words(String word){
        this.word= word;
    }
    public Words(String word,String meanings){
        this.word= word;
        this.meanings=meanings;
    }
    public Words(String word,String meanings,String exS){
        this.word= word;
        this.meanings=meanings;
        this.exS=exS;
    }

    public String getExS() {
        return exS;
    }

    public String getMeanings() {
        return meanings;
    }

    public String getWord() {
        return word;
    }

    public void setExS(String exS) {
        this.exS=exS;
    }

    public void setMeanings(String meanings) {
        this.meanings = meanings;
    }

    public void setWord(String word) {
        this.word = word;
    }


}


