package com.afterpay.util.fraud;

import java.io.Serializable;

public class PanDate implements Serializable {

    private static final long serialVersionUID = 2833042802503352171L;

    private String pan;
    private String date;

    public PanDate(String pan, String date) {
        this.pan = pan;
        this.date = date;
    }

    public String getPan() {
        return pan;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!PanDate.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final PanDate other = (PanDate) obj;

        return (this.pan.equals(other.getPan()) && this.date.equals(other.getDate()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.pan.hashCode();
        hash = 53 * hash + this.date.hashCode();
        return hash;
    }
}
