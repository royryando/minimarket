package com.market.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Roy on 28/06/2017.
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
    @Column(name = "total_harga", length = 9)
    private Long total_harga;

    @NotNull
    @Column(name = "bayar", length = 9)
    private Long bayar;

    @NotNull
    @Column(name = "kembali", length = 9)
    private Long kembali;

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public Time getJam() {
        return jam;
    }

    public void setJam(Time jam) {
        this.jam = jam;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Long getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Long total_harga) {
        this.total_harga = total_harga;
    }

    public Long getBayar() {
        return bayar;
    }

    public void setBayar(Long bayar) {
        this.bayar = bayar;
    }

    public Long getKembali() {
        return kembali;
    }

    public void setKembali(Long kembali) {
        this.kembali = kembali;
    }
}
