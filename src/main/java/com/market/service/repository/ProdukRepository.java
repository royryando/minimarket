package com.market.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.model.Produk;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, String> {
	
	
}
