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
	/*
	@NotNull
	@Column(name = "kode_tipe", length = 11)
	private Integer kode_tipe;
	*/
	@ManyToOne
	@JoinColumn(name="kode_tipe")
	private Tipe tipe;
	
	public Tipe getTipe() {
		return tipe;
	}

	public void setTipe(Tipe tipe) {
		this.tipe = tipe;
	}

	/*
	@ManyToOne(cascade = CascadeType.ALL)
	private List<Tipe> tipe;
	
	public List<Tipe> getTipe() {
		return tipe;
	}

	@SuppressWarnings("unused")
	private Produk(){}
	
	public Produk(String kode_produk){
		this.setKode_produk(kode_produk);
	}
	
	public Produk(String kode_produk, Integer kode_tipe, String nama, Integer stok, Long harga_jual, Long harga_beli, Date tanggal_masuk, String deskripsi){
		this.setKode_produk(kode_produk);
		this.setKode_tipe(kode_tipe);
		this.setNama(nama);
		this.setStok(stok);
		this.setHarga_jual(harga_jual);
		this.setHarga_beli(harga_beli);
		this.setTanggal_masuk(tanggal_masuk);
		this.setDeskripsi(deskripsi);
		
	}
	
	public void setTipe(List<Tipe> tipe) {
		this.tipe = tipe;
	}
	*/
	@NotNull
	@Column(name = "nama", length = 150)
	private String nama;
	
	@NotNull
	@Column(name = "stok", length = 11)
	private Integer stok;
	
	@NotNull
	@Column(name = "harga_jual", length = 20)
	private Long harga_jual;
	
	@NotNull
	@Column(name = "harga_beli", length = 20)
	private Long harga_beli;
	
	@NotNull
	@Column(name = "tanggal_masuk")
	//@DateTimeFormat
	@Temporal(TemporalType.DATE)
	private Date tanggal_masuk;
	
	@NotNull
	@Column(name = "deskripsi", length = 255)
	private String deskripsi;
	/*
	public Integer getKode_tipe() {
		return kode_tipe;
	}

	public void setKode_tipe(Integer kode_tipe) {
		this.kode_tipe = kode_tipe;
	}
	*/
	public String getKode_produk() {
		return kode_produk;
	}

	public void setKode_produk(String kode_produk) {
		this.kode_produk = kode_produk;
	}
	/*
	public Integer getKode_tipe() {
		return kode_tipe;
	}
	
	public void setKode_tipe(Integer kode_tipe) {
		this.kode_tipe = kode_tipe;
	}
	*/
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

	public Long getHarga_jual() {
		return harga_jual;
	}

	public void setHarga_jual(Long harga_jual) {
		this.harga_jual = harga_jual;
	}

	public Long getHarga_beli() {
		return harga_beli;
	}

	public void setHarga_beli(Long harga_beli) {
		this.harga_beli = harga_beli;
	}

	public Date getTanggal_masuk() {
		return tanggal_masuk;
	}

	public void setTanggal_masuk(Date tanggal_masuk) {
		this.tanggal_masuk = tanggal_masuk;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}
}
