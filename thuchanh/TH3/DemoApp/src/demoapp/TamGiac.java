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

    public TamGiac(double a1, double b1, double c1) throws Exception {
        if (a1 + b1 > c1 && a1 + c1 > b1 && b1 + c1 > a1) {
            this.a = a1;
            this.b = b1;
            this.c = c1;
        } else {
            throw new Exception("Invalid data!!!");
        }
        
    }
    
    public double tinhChuVi() {
        return this.a + this.b + this.c;
    }
    
    public double tinhDienTich() {
        double p = this.tinhChuVi() / 2;
        
        return Math.sqrt(p*(p-this.a)*(p - this.b)*(p - this.c));
    }
    
    public void xuat() {
        System.out.printf("(%.1f, %.1f, %.1f): %.1f\n", 
                this.a, this.b, this.c, this.tinhDienTich());
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
    
}
