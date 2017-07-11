package com.market.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipe")
public class Tipe {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kode_tipe", length = 11)
	private Integer kode_tipe;

	@NotNull
	@Column(name = "tipe", length = 150)
	private String tipe;
	
	public Tipe(){}
	
	@SuppressWarnings("unused")
	private Tipe(Integer kode_tipe){
		this.setKode_tipe(kode_tipe);
	}
	
	@SuppressWarnings("unused")
	private Tipe(Integer kode_tipe, String tipe){
		this.setKode_tipe(kode_tipe);
		this.setTipe(tipe);
	}

	public Integer getKode_tipe() {
		return kode_tipe;
	}
	public void setKode_tipe(Integer kode_tipe) {
		this.kode_tipe = kode_tipe;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
}
