/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.model;

import java.time.LocalDate;
/**
 *
 * @author Hafiz Yazid
 */
public class TransaksiGym {
    private int idTransaksi;
    private DataPengguna dataPengguna;
    private MenuGym dataMenu;
    private LocalDate tanggalPendaftaran;
    private String status;
    private double totalBiaya;
    private LocalDate tanggalBerakhir;
    private int diskon;

    public TransaksiGym() {
    }

    public TransaksiGym(int idTransaksi, DataPengguna dataPengguna, MenuGym dataMenu, LocalDate tanggalPendaftaran, String status, double totalBiaya, LocalDate tanggalBerakhir, int diskon) {
        this.idTransaksi = idTransaksi;
        this.dataPengguna = dataPengguna;
        this.dataMenu = dataMenu;
        this.tanggalPendaftaran = tanggalPendaftaran;
        this.status = status;
        this.totalBiaya = totalBiaya;
        this.tanggalBerakhir = tanggalBerakhir;
        this.diskon = diskon;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public DataPengguna getDataPengguna() {
        return dataPengguna;
    }

    public MenuGym getDataMenu() {
        return dataMenu;
    }

    public LocalDate getTanggalPendaftaran() {
        return tanggalPendaftaran;
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

    public int getDiskon() {
        return diskon;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setDataPengguna(DataPengguna dataPengguna) {
        this.dataPengguna = dataPengguna;
    }

    public void setDataMenu(MenuGym dataMenu) {
        this.dataMenu = dataMenu;
    }

    public void setTanggalPendaftaran(LocalDate tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
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

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }   
    
}


   
    
     
    
