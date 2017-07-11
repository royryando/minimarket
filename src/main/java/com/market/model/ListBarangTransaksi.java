package com.market.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Roy on 28/06/2017.
 */
@Entity
@Table(name = "transaksi_barang")
public class ListBarangTransaksi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 11)
    private Integer id;

    @NotNull
    @Column(name = "kode_transaksi", length = 100)
    private String kode_transaksi;

    @NotNull
    @Column(name = "kode_produk", length = 100)
    private String kode_produk;

    @NotNull
    @Column(name = "nama_produk", length = 150)
    private String nama_produk;

    @NotNull
    @Column(name = "harga_produk", length = 11)
    private Integer harga_produk;

    @NotNull
    @Column(name = "jumlah_beli", length = 11)
    private Integer jumlah_beli;

    @NotNull
    @Column(name = "total_harga", length = 11)
    private Integer total_harga;

    @NotNull
    @Column(name = "laba", length = 11)
    private Integer laba;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getKode_produk() {
        return kode_produk;
    }

    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public Integer getHarga_produk() {
        return harga_produk;
    }

    public void setHarga_produk(Integer harga_produk) {
        this.harga_produk = harga_produk;
    }

    public Integer getJumlah_beli() {
        return jumlah_beli;
    }

    public void setJumlah_beli(Integer jumlah_beli) {
        this.jumlah_beli = jumlah_beli;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Integer getLaba() {
        return laba;
    }

    public void setLaba(Integer laba) {
        this.laba = laba;
    }
}
