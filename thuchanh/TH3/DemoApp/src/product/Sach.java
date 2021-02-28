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
public class Sach extends SanPham {
    private int soTrang;
    
    public Sach(String tenSp, double gia, int st) {
        super(tenSp, gia);
        
        this.soTrang = st;
    }

    @Override
    public void xuat() {
        super.xuat(); //To change body of generated methods, choose Tools | Templates.
        
        System.out.printf("Số trang: %d\n", this.soTrang);
    }
    
    

    /**
     * @return the soTrang
     */
    public int getSoTrang() {
        return soTrang;
    }

    /**
     * @param soTrang the soTrang to set
     */
    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
}
