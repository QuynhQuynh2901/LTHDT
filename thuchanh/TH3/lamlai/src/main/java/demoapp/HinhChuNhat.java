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
public class HinhChuNhat {
    private double chieuDai, chieuRong;

    public HinhChuNhat(double chieuDai, double chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }
    public double tinhDTHCN(){
        return this.chieuDai * this.chieuRong;
    }
    public double tinhCVHCN(){
        return (1 * 1.0)/2 * (this.chieuDai + this.chieuRong);
    }

    @Override
    public String toString() {
        return String.format("Hinh chu Nhat: %s, %s" + 
                "\nDien tich: %.1f" +
                "\nChu vi: %.1f",
                this.chieuDai, this.chieuRong, this.tinhDTHCN(),
                this.tinhCVHCN()); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * @return the chieuDai
     */
    public double getChieuDai() {
        return chieuDai;
    }

    /**
     * @param chieuDai the chieuDai to set
     */
    public void setChieuDai(double chieuDai) {
        this.chieuDai = chieuDai;
    }

    /**
     * @return the chieuRong
     */
    public double getChieuRong() {
        return chieuRong;
    }

    /**
     * @param chieuRong the chieuRong to set
     */
    public void setChieuRong(double chieuRong) {
        this.chieuRong = chieuRong;
    }
    
}
