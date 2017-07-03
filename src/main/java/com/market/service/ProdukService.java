package com.market.service;

import java.util.List;

import com.market.model.Produk;

public interface ProdukService {

	List<Produk> listProduk();
	List<Produk> listProdukFilterNama(String nama);
	Produk listProdukFilterKode(String kode);
}
