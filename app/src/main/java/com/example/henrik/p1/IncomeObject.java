package com.example.henrik.p1;

/**
 * Created by Henrik on 2016-09-16.
 */
public class IncomeObject {

    private String titel;
    private String category;
    private double amount;
    private int year;
    private int month;
    private int day;


    public IncomeObject(double amount, String category, String titel, int month, int year, int day) {
        this.amount = amount;
        this.category = category;
        this.titel = titel;
        this.month = month;
        this.year = year;
        this.day = day;
    }
    public IncomeObject(){

    }

    public String getTitel() {
        return titel;
    }

    public String getCategory() {
        return category;
    }


    public double getAmount() {
        return amount;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getFormatedDate(){
        return year+"-"+month+"-"+day;
    }
}
