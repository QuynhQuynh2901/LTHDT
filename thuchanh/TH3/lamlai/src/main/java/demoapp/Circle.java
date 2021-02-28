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
public class Circle extends Ellipse{
   public Circle(double bk){
       super(bk,bk);
   }

    @Override
    public String toString() {
        return String.format("hinh circle: %.1f" + 
                "\nDien tich: %.1f"+
                "\nChu vi: %.1f", this.getBkBe(), this.tinhDT(),this.tinhCV()); //To change body of generated methods, choose Tools | Templates.
    }
    
}
