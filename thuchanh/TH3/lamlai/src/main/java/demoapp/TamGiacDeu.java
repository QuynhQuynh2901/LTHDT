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
public class TamGiacDeu extends TamGiac {
    public TamGiacDeu(double abc) throws Exception{
        super(abc,abc,abc);
    }
    @Override
    public String toString() {
        return String.format("Tam Giac deu: %s" 
                + "\nDienTich: %.1f"
                + "\nChu vi; %.1f",
                this.getA(), this.tinhDTTG(),this.tinhCVTG()); //To change body of generated methods, choose Tools | Templates.
    }
}
