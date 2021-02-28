/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuanLySanPham {
    private List<SanPham> ds = new ArrayList<>();
    
    public void themSp(SanPham s) {
        this.ds.add(s);
    }
    
    public void xoaSp(SanPham s) {
        this.ds.remove(s);
    }
    
    public void xoaSp(int maSp) {
        for (SanPham s: this.ds)
            if (s.getId() == maSp) {
                this.ds.remove(s);
                break;
            }
    }
    
    public void xuat() {
        this.ds.forEach(s -> s.xuat());
    }
}
