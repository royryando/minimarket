package com.market.model;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name="username")
	@NotEmpty(message="Masukkan Username")
	private String username;
	
	@Column(name="password")
	@Length(min = 5, message="Masukkan password setidaknya 5 karakter")
	@NotEmpty(message="Masukkan Password")
	@Transient
	private String password;
	
	@Column(name="name")
	@NotEmpty(message="Masukkan Nama")
	private String name;
	
	@Column(name="active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
