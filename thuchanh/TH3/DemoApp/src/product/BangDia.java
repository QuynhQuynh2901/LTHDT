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
public class BangDia extends SanPham {
    private double doDai;

    public BangDia(String tenSp, double gia, double doDai) {
        super(tenSp, gia);
        this.doDai = doDai;
    }

    @Override
    public void xuat() {
        super.xuat(); //To change body of generated methods, choose Tools | Templates.
        
        System.out.printf("Độ dài: %.1f phút\n", this.doDai);
    }
    
    

    /**
     * @return the doDai
     */
    public double getDoDai() {
        return doDai;
    }

    /**
     * @param doDai the doDai to set
     */
    public void setDoDai(double doDai) {
        this.doDai = doDai;
    }
}
