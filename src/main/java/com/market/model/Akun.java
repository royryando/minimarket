package com.market.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "akun")
public class Akun {

	@Id
	@Column(name = "username", length = 100)
	private String username;
	
	@NotNull
	@Transient
	@Column(name = "password", length = 100)
	private String password;
	/*
	@NotNull
	@Column(name = "level_id", length = 11)
	private Integer level_id;
	*/
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "level_id", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "level_id"))
	private Set<Level> level;
	
	@NotNull
	@Column(name = "nama", length = 150)
	private String nama;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Level> getLevel() {
		return level;
	}

	public void setLevel(Set<Level> level) {
		this.level = level;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
}
