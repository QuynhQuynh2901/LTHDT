/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoapp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DemoApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TamGiac t1 = new TamGiac(3, 4, 5);
            TamGiac t2 = new TamGiacCan(5, 4);
            TamGiac t3 = new TamGiacDeu(4.5);
            TamGiac t4 = new TamGiac(5, 3.5, 4);

            List<TamGiac> ds = new ArrayList<>();
            ds.add(t1);
            ds.add(t2);
            ds.add(t3);
            ds.add(t4);

            ds.forEach(p -> p.xuat());
            ds.sort((tg1, tg2) -> {
               double dt1 = tg1.tinhDienTich();
               double dt2 = tg2.tinhDienTich();
               
               return -(dt1 > dt2 ?1:(dt1 < dt2 ?-1:0));
            });
            System.out.println("KẾT QUẢ SẮP XẾP");
            ds.forEach(p -> p.xuat());
        } catch (Exception ex) {
            Logger.getLogger(DemoApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
