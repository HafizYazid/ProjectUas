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
    private String namaMenu;
    private String jenisPengunjung;
    private Double harga;
    private int durasi;
    private String deskripsi;

    public MenuGym() {
    }

    public MenuGym(int idKategori, String namaMenu, String jenisPengunjung, Double harga, int durasi, String deskripsi) {
        this.idKategori = idKategori;
        this.namaMenu = namaMenu;
        this.jenisPengunjung = jenisPengunjung;
        this.harga = harga;
        this.durasi = durasi;
        this.deskripsi = deskripsi;
    }

    
    public int getIdKategori() {
        return idKategori;
    }

    public String getJenisPengunjung() {
        return jenisPengunjung;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public Double getHarga() {
        return harga;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public void setJenisPengunjung(String jenisPengunjung) {
        this.jenisPengunjung = jenisPengunjung;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    
}
