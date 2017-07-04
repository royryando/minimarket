package com.market.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produk")
public class Produk {

	@Id
	@NotNull
	@Column(name = "kode_produk", length = 150)
	private String kode_produk;

	@ManyToOne
	@JoinColumn(name="kode_tipe")
	private Tipe tipe;
	
	public Tipe getTipe() {
		return tipe;
	}

	public void setTipe(Tipe tipe) {
		this.tipe = tipe;
	}

	@NotNull
	@Column(name = "nama", length = 150)
	private String nama;
	
	@NotNull
	@Column(name = "stok", length = 11)
	private Integer stok;
	
	@NotNull
	@Column(name = "harga_jual", length = 20)
	private Integer harga_jual;
	
	@NotNull
	@Column(name = "harga_beli", length = 20)
	private Integer harga_beli;
	
	@NotNull
	@Column(name = "tanggal_masuk")
	//@DateTimeFormat
	@Temporal(TemporalType.DATE)
	private Date tanggal_masuk;
	
	@NotNull
	@Column(name = "deskripsi", length = 255)
	private String deskripsi;

	public String getKode_produk() {
		return kode_produk;
	}

	public void setKode_produk(String kode_produk) {
		this.kode_produk = kode_produk;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getStok() {
		return stok;
	}

	public void setStok(Integer stok) {
		this.stok = stok;
	}

	public Integer getHarga_jual() {
		return harga_jual;
	}

	public void setHarga_jual(Integer harga_jual) {
		this.harga_jual = harga_jual;
	}

	public Integer getHarga_beli() {
		return harga_beli;
	}

	public void setHarga_beli(Integer harga_beli) {
		this.harga_beli = harga_beli;
	}

	public Date getTanggal_masuk() {
		return tanggal_masuk;
	}

	public void setTanggal_masuk(Date tanggal_masuk) {
		Date dt = new Date();
		this.tanggal_masuk = dt;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}
