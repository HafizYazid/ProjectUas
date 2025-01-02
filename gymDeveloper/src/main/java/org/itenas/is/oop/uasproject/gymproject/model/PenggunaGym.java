/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.model;

import java.time.LocalDate;
import org.itenas.is.oop.uasproject.gymproject.service.Pembayaran;
/**
 *
 * @author Hafiz Yazid
 */
public class PenggunaGym implements Pembayaran{
    private int idPengguna;
    private String namaPengguna;
    private String nomorPengguna;
    private MenuGym idKategori;
    private LocalDate tanggalMasuk;
    private int durasi;
    private String status;
    private double totalBiaya;
    private LocalDate tanggalBerakhir;

    public PenggunaGym() {
    }

    public PenggunaGym(int idPengguna, String namaPengguna, String nomorPengguna, MenuGym idKategori, LocalDate tanggalMasuk, int durasi, String status, double totalBiaya, LocalDate tanggalBerakhir) {
        this.idPengguna = idPengguna;
        this.namaPengguna = namaPengguna;
        this.nomorPengguna = nomorPengguna;
        this.idKategori = idKategori;
        this.tanggalMasuk = tanggalMasuk;
        this.durasi = durasi;
        this.status = status;
        this.totalBiaya = totalBiaya;
        this.tanggalBerakhir = tanggalBerakhir;
    }

   

    public int getIdPengguna() {
        return idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getNomorPengguna() {
        return nomorPengguna;
    }

    public MenuGym getIdKategori() {
        return idKategori;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public int getDurasi() {
        return durasi;
    }

    public String getStatus() {
        return status;
    }

    

    public double getTotalBiaya() {
        return totalBiaya;
    }

    public LocalDate getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public void setNomorPengguna(String nomorPengguna) {
        this.nomorPengguna = nomorPengguna;
    }

    public void setIdKategori(MenuGym idKategori) {
        this.idKategori = idKategori;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    public void setTotalBiaya(double totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public void setTanggalBerakhir(LocalDate tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }


    @Override
    public int totalBiaya() {
       if (getIdKategori().getIdKategori() == 1) {
        return getIdKategori().getHarga() * getDurasi();
    } else if (getIdKategori().getIdKategori() == 2) {
        return getIdKategori().getHarga() * (getDurasi() / 30 );
    }else{
        throw new IllegalArgumentException("Jenis pengunjung harus dipilih");
        }
    }

    @Override
    public boolean status() {
        LocalDate hariBerakhir = tanggalMasuk.plusDays(durasi);
        LocalDate tanggalSekarang = LocalDate.now();
        return !tanggalSekarang.isAfter(hariBerakhir);
    }

    @Override
    public String tenggatStatus() {
        LocalDate hariBerakhir = tanggalMasuk.plusDays(durasi);
        return hariBerakhir.toString();
    }

    public Object getMenuGym() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
        
    
}
