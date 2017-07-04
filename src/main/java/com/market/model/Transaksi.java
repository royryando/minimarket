package com.market.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Roy on 03/07/2017.
 */
@Entity
@Table(name = "transaksi")
public class Transaksi {

    @Id
    @Column(name = "kode_transaksi", length = 100)
    private String kode_transaksi;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal")
    private Date tanggal;

    @NotNull
    @DateTimeFormat
    @Column(name = "jam")
    private Time jam;

    @NotNull
    @Column(name = "total_harga", length = 20)
    private Integer total_harga;

    @NotNull
    @Column(name = "bayar", length = 20)
    private Integer bayar;

    @NotNull
    @Column(name = "kembali", length = 20)
    private Integer kembali;

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Time getJam() {
        return jam;
    }

    public void setJam(Time jam) {
        this.jam = jam;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Integer getBayar() {
        return bayar;
    }

    public void setBayar(Integer bayar) {
        this.bayar = bayar;
    }

    public Integer getKembali() {
        return kembali;
    }

    public void setKembali(Integer kembali) {
        this.kembali = kembali;
    }
}
