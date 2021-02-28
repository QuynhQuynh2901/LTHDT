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
public class HinhVuong extends HinhChuNhat{
    public HinhVuong(double a)
    {
        super(a,a);
    }
    public double tinhCVHCN(double a){
        return 4 * a;
    }
    @Override
    public String toString() {
        return String.format("Hinh vuong: %s" + 
                "\nDien tich: %.1f" +
                "\nChu vi: %.1f",
                this.getChieuDai(), this.tinhDTHCN(), this.tinhCVHCN(this.getChieuDai())); //To change body of generated methods, choose Tools | Templates.
    }
    
}
