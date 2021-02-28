/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bth01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class BTh01 {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/data/test1.txt");
        File out = new File("src/data/out.txt");
        try(Scanner s = new Scanner(f);
                PrintWriter w = new PrintWriter(out)){
                for(int i = 0; i < f.length(); i++)
                {
                    String a = s.nextLine();
                    String kq = a.replaceAll("Quynh", "quynh xinh dep");  
                    w.print(kq);
                }
            }
        }
        /*String a = "Phan thi nhu quynh";
        int ind = a.indexOf("n");
        String name = a.substring(0, ind);
        System.out.println(name);
        int count = 0;
        for(int i = 0; i < a.length(); i++)
        {
        if(Character.isUpperCase(a.charAt(i)) == true)
        count++;
        }
        System.out.println("so ki tu hoa: " + count);*/
        /*int n = (int)(Math.random()*100) +1;
        Scanner s = new Scanner(System.in);
        int predict = 0;
        do {
        System.out.print("Nhap so ban doan: ");
        predict = s.nextInt();
        if(predict > n)
        System.out.println("so ban doan lon hon!");
        else
        if(predict < n)
        System.out.println("So ban doan nho lon!");
        else
        System.out.println("Ban da doan trung!!");
        } while (n != predict);*/
        
           
    
    
}
