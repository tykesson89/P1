package com.example.henrik.p1;

/**
 * Created by Henrik on 2016-09-16.
 */
public class ExpenseObject {
    private String titel;
    private String category;
    private int year;
    private int month;
    private int day;
    private double price;

    public ExpenseObject(double price, String category, String titel, int month, int year, int day) {
        this.price = price;
        this.category = category;
        this.titel = titel;
        this.month = month;
        this.year = year;
        this.day = day;
    }
    public ExpenseObject(){

    }

    public String getTitel() {
        return titel;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "ExpenseObject{" +
                "titel='" + titel + '\'' +
                ", category='" + category + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", price=" + price +
                '}';
    }
}
