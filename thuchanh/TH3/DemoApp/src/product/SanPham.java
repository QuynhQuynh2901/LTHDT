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
public class SanPham {
    private static int dem = 0;
    private int id = ++dem;
    private String tenSp;
    private double gia;
    
    public SanPham(int id) {
        this.id = id;
    }

    public SanPham(String tenSp, double gia) {
        this.tenSp = tenSp;
        this.gia = gia;
    }
    
    public void xuat() {
        System.out.printf("Id: %d\n", this.id);
        System.out.printf("Tên: %s\n", this.tenSp);
        System.out.printf("Gía: %.1f\n", this.gia);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tenSp
     */
    public String getTenSp() {
        return tenSp;
    }

    /**
     * @param tenSp the tenSp to set
     */
    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    /**
     * @return the gia
     */
    public double getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(double gia) {
        this.gia = gia;
    }
}
