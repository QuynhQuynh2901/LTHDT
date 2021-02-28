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
public class TamGiac {
    private double a, b, c;
    public TamGiac(double a, double b, double c) throws Exception{
        if((a + b) > c && (a + c) > b && (b + c) > a){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        else throw new Exception("du lieu hk dung");
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(double a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(double b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public double getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(double c) {
        this.c = c;
    }
    public double tinhCVTG(){
        return this.a + this.b + this.c;
    }
    public double tinhDTTG(){
        double p = this.tinhCVTG() / 2;
        return p * ((p - this.a)*(p - this.b)*(p - this.c));
    }

    @Override
    public String toString() {
        return String.format("Tam giac: %s, %s, %s"
                +"\nDien tich: %.1f"
                +"\nChu vi: %.1f", this.a, this.b, this.c,
                this.tinhDTTG(), this.tinhCVTG() ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
