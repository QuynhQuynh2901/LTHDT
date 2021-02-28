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
public class TamGiacCan extends TamGiac{
    public TamGiacCan(double ab, double c) throws Exception{
        super(ab, ab, c);
    }

    @Override
    public String toString() {
        return String.format("Tam Giac Can: %s, %s" 
                + "\nDienTich: %.1f"
                + "\nChu vi; %.1f",
                this.getA(), this.getC(), this.tinhDTTG(),this.tinhCVTG()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
