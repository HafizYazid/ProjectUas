/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.model;

/**
 *
 * @author Hafiz Yazid
 */
public class MenuGym {
    private int idKategori;
    private String jenisPengunjung;
    private int harga;

    public MenuGym() {
    }

    public MenuGym(int idKategori, String jenisPengunjung, int harga) {
        this.idKategori = idKategori;
        this.jenisPengunjung = jenisPengunjung;
        this.harga = harga;
    }
    
    public int getIdKategori() {
        return idKategori;
    }

    public String getJenisPengunjung() {
        return jenisPengunjung;
    }

    

    public int getHarga() {
        return harga;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public void setJenisPengunjung(String jenisPengunjung) {
        this.jenisPengunjung = jenisPengunjung;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    

    
    
    
}
