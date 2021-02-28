/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoapp;

/**
 *
 * @author Admin
 */
public class Ellipse {
    private double bkLon, bkBe;

    public Ellipse(double bkLon, double bkBe) {
        this.bkLon = bkLon;
        this.bkBe = bkBe;
    }

    /**
     * @return the bkLon
     */
    public double getBkLon() {
        return bkLon;
    }
    
    /**
     * @param bkLon the bkLon to set
     */
    public void setBkLon(double bkLon) {
        this.bkLon = bkLon;
    }

    /**
     * @return the bkBe
     */
    public double getBkBe() {
        return bkBe;
    }

    /**
     * @param bkBe the bkBe to set
     */
    public void setBkBe(double bkBe) {
        this.bkBe = bkBe;
    }
    public double tinhDT(){
        return Math.PI * this.bkBe * this.bkLon;
    }
    public double tinhCV(){
        return 2 * Math.PI * Math.sqrt((Math.pow(this.bkBe, 2) +
                Math.pow(this.bkLon,2)) / 2);
    }

    @Override
    public String toString() {
        return String.format("hình ellipse: (%.1f, %.1f)"
                + "\ndien tich: %.1f "
                + "\nchu vi: %.1f",
                this.getBkLon(),this.getBkBe(), this.tinhDT(), this.tinhCV()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
