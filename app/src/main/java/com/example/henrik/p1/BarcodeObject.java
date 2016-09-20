package com.example.henrik.p1;

import com.google.android.gms.vision.barcode.Barcode;

/**
 * Created by Henrik on 2016-09-20.
 */
public class BarcodeObject {
    private String barcode;
    private String titel;
    private String category;
    private double price;


    public BarcodeObject(String barcode, String titel, String category, double price) {
        this.barcode = barcode;
        this.titel = titel;
        this.category = category;
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
