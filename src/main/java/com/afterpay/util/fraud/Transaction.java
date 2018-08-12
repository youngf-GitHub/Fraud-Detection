package com.afterpay.util.fraud;

import java.util.Date;

public class Transaction {

    private String hPan = "";
    private Date dateTime = new Date();
    private double price = 0.0;

    public Transaction(String line) throws Exception {

        super();

        String[] list = line.split(",");

        if (list == null || list.length < 3) {
            throw new Exception ("Invaide transaction");
        }

        hPan = list[0].trim();
        dateTime = FraudDetector.yyyy_MM_ddTHHmmss.parse(list[1].trim());
        price = Double.parseDouble(list[2].trim());
    }

    public String getHPan() {
        return hPan;
    }

    public String getDate() {
        return FraudDetector.yyyy_MM_dd.format(dateTime);
    }

    public double getPrice() {
        return price;
    }
}
