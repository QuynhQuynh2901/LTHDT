/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoapp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class test {

    private static boolean t2;
    public static void main(String[] args) throws Exception {
        Ellipse e1 = new Ellipse(3, 5);
        Ellipse e2 = new Ellipse(4, 6);
        Ellipse e3 = new Circle(7);
        TamGiac t1 = new TamGiac(3,4,5);
        TamGiac t2 = new TamGiacCan(6,7);
        TamGiac t3 = new TamGiacDeu(8);
        HinhChuNhat h1 = new HinhChuNhat(2,7);
        HinhChuNhat h2 = new HinhVuong(7);
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(h1);
        System.out.println(h2);
    }
}
