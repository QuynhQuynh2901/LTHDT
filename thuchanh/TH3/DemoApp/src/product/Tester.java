/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

/**
 *
 * @author Admin
 */
public class Tester {
    public static void main(String[] args) {
        SanPham s1 = new Sach("Java", 20, 120);
        SanPham s2 = new BangDia("OU", 25, 60);
        SanPham s3 = new Sach("C++", 18, 100);
        
        QuanLySanPham ql = new QuanLySanPham();
        ql.themSp(s1);
        ql.themSp(s2);
        ql.themSp(s3);
        
        System.out.println("== DANH SÁCH SẢN PHẨM");
        ql.xuat();
        
        System.out.println("== DANH SÁCH SẢN PHẨM SAU XÓA");
        ql.xoaSp(2);
        ql.xuat();
    }
}
